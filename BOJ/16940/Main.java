import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int nodeCount;
	private static List<List<Integer>> tree;
	private static int[] order;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int solve() {
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[nodeCount];
		int cur, size, base = 1;
		List<Integer> subtree;
		boolean[] s = new boolean[nodeCount];

		q.addLast(0);
		visited[0] = true;
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			subtree = tree.get(cur);
			size = 0;
			for (int neighbor : subtree) {
				if (!visited[neighbor]) {
					s[neighbor] = true;
					size++;
				}
			}
			for (int i = 0; i < size; i++) {
				if (!s[order[base + i]])
					return 0;
				visited[order[base + i]] = true;
				q.addLast(order[base + i]);
			}
			base += size;
		}
		return 1;
	}

	public static void main(String[] args) throws IOException {
		int u, v;

		tree = new ArrayList<>(nodeCount = read());
		for (int i = 0; i < nodeCount; i++)
			tree.add(new ArrayList<>());
		for (int i = 1; i < nodeCount; i++) {
			tree.get(u = read() - 1).add(v = read() - 1);
			tree.get(v).add(u);
		}
		order = new int[nodeCount];
		for (int i = 0; i < nodeCount; i++)
			order[i] = read() - 1;
		System.out.println(solve());
	}
}