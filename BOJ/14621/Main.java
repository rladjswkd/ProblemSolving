import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static int collegeCount, roadCount;
	private static int[] types, ds;

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
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		int[] cur;
		int setCount, res = 0;

		setCount = collegeCount = readInt();
		roadCount = readInt();
		types = new int[collegeCount];
		ds = new int[collegeCount];
		for (int i = 0; i < collegeCount; i++) {
			ds[i] = -1;
			types[i] = System.in.read();
			System.in.read();
		}
		for (int i = 0; i < roadCount; i++)
			pq.add(new int[] { readInt() - 1, readInt() - 1, readInt() });
		while (!pq.isEmpty() && setCount > 1) {
			cur = pq.poll();
			if (types[cur[0]] != types[cur[1]] && union(cur[0], cur[1])) {
				setCount--;
				res += cur[2];
			}
		}
		System.out.println(setCount == 1 ? res : -1);
	}
}