import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int number, weight;

		Node(int number, int weight) {
			this.number = number;
			this.weight = weight;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int res = 0;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int solve(List<List<Node>> graph, int index) {
		int max = 0, second = 0, sub;

		if (graph.get(index).isEmpty())
			return 0;
		for (Node lower : graph.get(index)) {
			sub = solve(graph, lower.number);
			if (max < lower.weight + sub) {
				if (second < max)
					second = max;
				max = lower.weight + sub;
			} else if (second < lower.weight + sub)
				second = lower.weight + sub;
		}
		res = Math.max(res, max + second);
		return max;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt();
		List<List<Node>> graph = new ArrayList<>();

		for (int i = 0; i < n; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < n - 1; i++)
			graph.get(readInt() - 1).add(new Node(readInt() - 1, readInt()));
		// 루트 노드는 항상 1
		solve(graph, 0);
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}