import java.io.IOException;

public class Main {
	private static int n;
	private static int[] ds;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int find(int node) {
		int root = node, upper;

		while (ds[root] >= 0)
			root = ds[root];
		while (ds[node] >= 0) {
			upper = ds[node];
			ds[node] = root;
			node = upper;
		}
		return root;
	}

	private static void union(int u, int v) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return;
		if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		ds = new int[n = readInt()];
		for (int i = 0; i < n; i++)
			ds[i] = -1;
		for (int i = 2; i < n; i++)
			union(readInt() - 1, readInt() - 1);
		for (int i = 0; i < n; i++)
			if (ds[i] < 0)
				sb.append(i + 1).append(' ');
		System.out.println(sb.toString());
	}
}