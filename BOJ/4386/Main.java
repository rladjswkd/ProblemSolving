import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static int n;
	private static int[] ds;
	private static PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.distance < b.distance ? -1 : 1);
	private static double res = 0;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static double readDouble() throws IOException {
		int n = System.in.read() & 15, cur;
		double d, pow = 1;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		d = n;
		// 숫자가 x.y의 꼴로 주어진 경우
		if (cur == '.') {
			while (48 <= (cur = System.in.read()) && cur <= 57)
				d += (cur & 15) / (pow *= 10);
		}
		// 숫자가 x의 꼴로 주어진 경우
		return d;
	}

	private static double calculateDistance(double x1, double y1, double x2, double y2) {
		double dx = x2 - x1, dy = y2 - y1;

		return Math.sqrt(dx * dx + dy * dy);
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

	private static boolean union(int u, int v) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return false;
		n--;
		if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
		return true;
	}

	private static void solve() {
		Edge cur;

		while (!pq.isEmpty() && n > 1) {
			cur = pq.poll();
			if (union(cur.x, cur.y))
				res += cur.distance;
		}
	}

	public static void main(String[] args) throws IOException {
		double[][] stars = new double[n = readInt()][];

		ds = new int[n];
		for (int i = 0; i < n; i++) {
			ds[i] = -1;
			stars[i] = new double[] { readDouble(), readDouble() };
			for (int j = 0; j < i; j++)
				pq.add(new Edge(i, j, calculateDistance(stars[j][0], stars[j][1], stars[i][0], stars[i][1])));
		}
		solve();
		System.out.printf("%.2f\n", res);
	}

	static class Edge {
		int x, y;
		double distance;

		Edge(int x, int y, double distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
}