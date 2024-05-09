import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		Node left, right, upper;
		int val;

		Node(int val) {
			this.val = val;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static List<Integer> order;
	private static Node graph;
	private static StringBuilder sb = new StringBuilder();

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void makeGraph() {
		Node node = (graph = new Node(order.get(0)));
		Deque<Node> stack = new ArrayDeque<>();

		stack.addLast(node);
		for (int i = 1; i < order.size(); i++) {
			if (order.get(i) < node.val) {
				node.left = new Node(order.get(i));
				node.left.upper = node;
				node = node.left;
			} else {
				while (node.upper != null && node.upper.val < order.get(i))
					node = node.upper;
				while (node.right != null && node.right.val < order.get(i))
					node = node.right;
				node.right = new Node(order.get(i));
				node.right.upper = node;
				node = node.right;
			}
		}
	}

	private static void printGraph(Node node) {
		if (node.left != null)
			printGraph(node.left);
		if (node.right != null)
			printGraph(node.right);
		sb.append(node.val).append('\n');
	}

	public static void main(String[] args) throws IOException {
		int val;

		order = new ArrayList<>();
		while ((val = readInt()) != 0)
			order.add(val);
		if (order.size() > 0)
			makeGraph();
		printGraph(graph);
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}