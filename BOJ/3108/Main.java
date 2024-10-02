import java.io.IOException;

public class Main {
	private static int setCount;
	private static int[] ds;

	private static int read() throws IOException {
		int n = 0, c, s = System.in.read();

		if (s != 45)
			n = s & 15;
		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return s == 45 ? ~n + 1 : n;
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
		setCount--;
	}

	public static void main(String[] args) throws IOException {
		// 시작 지점을 고려해야 하므로 1을 더한다.
		int n = read() + 1;
		int[][] rects = new int[n][];
		int[] u, v;

		ds = new int[n];
		rects[0] = new int[] { 0, 0, 0, 0 };
		ds[0] = -1;
		for (int i = 1; i < n; i++) {
			rects[i] = new int[] { read(), read(), read(), read() };
			ds[i] = -1;
		}
		setCount = n;
		for (int i = 0; i < n; i++) {
			u = rects[i];
			for (int j = i + 1; j < n; j++) {
				v = rects[j];
				if (!(v[2] < u[0] || v[0] > u[2] || v[1] > u[3] || v[3] < u[1] || (u[0] < v[0] && v[0] < u[2] && u[1] < v[1]
						&& v[1] < u[3] && u[0] < v[2] && v[2] < u[2] && u[1] < v[3] && v[3] < u[3])
						|| (v[0] < u[0] && u[0] < v[2] && v[1] < u[1] && u[1] < v[3] && v[0] < u[2] && u[2] < v[2] && v[1] < u[3]
								&& u[3] < v[3])))
					union(i, j);
			}
		}
		System.out.println(setCount - 1);
	}
}
