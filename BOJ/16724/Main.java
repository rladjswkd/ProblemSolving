import java.io.IOException;

public class Main {
	private static int h, w, res = 0;
	private static int[][] map;
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

		if (uRoot == vRoot) {
			res++;
			return;
		} else if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		int ni, nj;

		map = new int[h = readInt()][w = readInt()];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++)
				map[i][j] = System.in.read();
			System.in.read();
		}
		ds = new int[h * w];
		for (int i = 0; i < ds.length; i++)
			ds[i] = -1;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				ni = i;
				nj = j;
				switch (map[i][j]) {
					case 'U':
						ni--;
						break;
					case 'D':
						ni++;
						break;
					case 'L':
						nj--;
						break;
					case 'R':
						nj++;
						break;
					default:
						break;
				}
				union(i * w + j, ni * w + nj);
			}
		}
		System.out.println(res);
	}
}