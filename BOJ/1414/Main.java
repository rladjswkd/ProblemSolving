import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static int n, usedLength;
	private static int[] ds;
	private static PriorityQueue<int[]> pq;

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
		int cableLength, totalLength = 0, setCount;
		int[] cur;

		setCount = n = readInt();
		ds = new int[n];
		pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		for (int i = 0; i < n; i++) {
			ds[i] = -1;
			for (int j = 0; j < n; j++) {
				if ((cableLength = System.in.read()) == '0')
					continue;
				if ('a' <= cableLength && cableLength <= 'z')
					cableLength = cableLength - 'a' + 1;
				else if ('A' <= cableLength && cableLength <= 'Z')
					cableLength = cableLength - 'A' + 27;
				totalLength += cableLength;
				pq.add(new int[] { i, j, cableLength });
			}
			System.in.read();
		}
		usedLength = 0;
		while (!pq.isEmpty() && setCount > 1) {
			cur = pq.poll();
			if (union(cur[0], cur[1])) {
				usedLength += cur[2];
				setCount--;
			}
		}
		System.out.println(setCount == 1 ? totalLength - usedLength : -1);
	}
}