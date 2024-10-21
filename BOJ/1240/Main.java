import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int nodeCount, pairCount, res;
	private static List<List<int[]>> tree;
	private static boolean[] visited;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int from, int to, int dist) {

	}

	public static void main(String[] args) throws IOException {
		int u, v, d;
		StringBuilder sb = new StringBuilder();

		nodeCount = read();
		pairCount = read();
		tree = new ArrayList<>();
		for (int i = 0; i < nodeCount; i++)
			tree.add(new ArrayList<>());
		for (int i = 1; i < nodeCount; i++) {
			tree.get(u = read() - 1).add(new int[] { v = read() - 1, d = read() });
			tree.get(v).add(new int[] { u, d });
		}
		for (int i = 0; i < pairCount; i++) {
			visited = new boolean[pairCount];
			solve(read() - 1, read() - 1, 0);
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}