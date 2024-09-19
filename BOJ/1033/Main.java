import java.io.IOException;

public class Main {
	private static int n;
	private static int[] ratio, ds;

	private static int read() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int calculateGCD(int a, int b) {
		int c;

		while (b > 0) {
			c = a % b;
			a = b;
			b = c;
		}
		return a;
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
		if (ds[uRoot] < ds[vRoot]) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		int a, b, p, q, root, gcd, product, ra, rb;
		StringBuilder sb = new StringBuilder();

		ratio = new int[n = read()];
		ds = new int[n];
		for (int i = 0; i < n; i++) {
			ratio[i] = 1;
			ds[i] = -1;
		}
		for (int i = 1; i < n; i++) {
			ra = ratio[a = read()];
			rb = ratio[b = read()];
			p = read();
			q = read();
			product = rb * p;
			root = find(a);
			for (int idx = 0; idx < n; idx++)
				if (find(idx) == root)
					ratio[idx] *= product;
			product = ra * q;
			root = find(b);
			for (int idx = 0; idx < n; idx++)
				if (find(idx) == root)
					ratio[idx] *= product;
			union(a, b);
		}
		gcd = ratio[0];
		for (int i = 1; i < n; i++)
			gcd = calculateGCD(gcd, ratio[i]);
		if (gcd > 1)
			for (long r : ratio)
				sb.append(r /= gcd).append(' ');
		else
			for (long r : ratio)
				sb.append(r).append(' ');
		System.out.println(sb.toString());
	}
}