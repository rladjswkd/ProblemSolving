import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int n, w, l;
	private static List<List<Integer>> tree;
	private static boolean[] visited;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void countLeaves(int node) {
		List<Integer> subTree = tree.get(node);

		visited[node] = true;
		if (subTree.size() == 1 && visited[subTree.get(0)]) {
			l++;
			return;
		}
		for (int sub : subTree)
			if (!visited[sub])
				countLeaves(sub);
	}

	public static void main(String[] args) throws IOException {
		int u, v;

		tree = new ArrayList<>(n = read());
		w = read();
		for (int i = 0; i < n; i++)
			tree.add(new ArrayList<>());
		for (int i = 1; i < n; i++) {
			u = read() - 1;
			v = read() - 1;
			tree.get(u).add(v);
			tree.get(v).add(u);
		}
		visited = new boolean[n];
		countLeaves(0);
		System.out.println((double) w / l);
	}
}