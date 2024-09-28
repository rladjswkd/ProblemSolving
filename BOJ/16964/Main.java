import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int nodeCount, orderIdx;
	private static List<List<Integer>> tree;
	private static int[] order, depth;
	private static boolean[] visited;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int solve(int node) {
		int size = 0, ndepth = depth[node] + 1, nnode;

		for (int neighbor : tree.get(node)) {
			if (!visited[neighbor]) {
				depth[neighbor] = ndepth;
				size++;
			}
		}
		while (size-- > 0) {
			if (depth[order[orderIdx]] != ndepth)
				return 0;
			visited[nnode = order[orderIdx++]] = true;
			if (solve(nnode) == 0)
				return 0;
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
		depth = new int[nodeCount];
		visited = new boolean[nodeCount];
		visited[0] = true;
		orderIdx = 1;
		System.out.println(solve(0));
	}
}