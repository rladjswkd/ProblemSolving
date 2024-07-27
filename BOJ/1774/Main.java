import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static PriorityQueue<double[]> pq;
	private static int[][] coords;
	private static int[] ds;
	private static double res;

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

	private static boolean union(int u, int v) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return false;
		if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		int godCount = readInt(), connectedCount = readInt();
		double[] dist;
		long dx, dy;

		coords = new int[godCount][];
		pq = new PriorityQueue<>((a, b) -> a[0] > b[0] ? 1 : -1);
		ds = new int[godCount];
		for (int i = 0; i < godCount; i++) {
			ds[i] = -1;
			coords[i] = new int[] { readInt(), readInt() };
			for (int j = 0; j < i; j++)
				pq.add(new double[] {
						Math.sqrt((dx = coords[i][0] - coords[j][0]) * dx + (dy = coords[i][1] - coords[j][1]) * dy), i, j });
		}
		for (int i = 0; i < connectedCount; i++)
			if (union(readInt() - 1, readInt() - 1) == true)
				godCount--;
		res = 0;
		while (!pq.isEmpty() && godCount > 1) {
			dist = pq.poll();
			if (union((int) dist[1], (int) dist[2]) == true) {
				godCount--;
				res += dist[0];
			}
		}
		System.out.printf("%.2f\n", res);
	}
}