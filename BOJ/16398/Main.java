import java.util.*;
import java.io.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] ds;
	private static long res = 0;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
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

	private static void union(int u, int v, int w) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return;
		res += w;
		if (ds[uRoot] < ds[vRoot]) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		int n = readInt();
		PriorityQueue<int[]> pq = new PriorityQueue<>(n * (n - 1) / 2 + 1, (a, b) -> a[2] - b[2]);
		int[] cur;

		ds = new int[n];
		for (int i = 0; i < n; i++)
			ds[i] = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i < j)
					pq.add(new int[] { i, j, readInt() });
				else
					readInt();
			}
		}
		while (!pq.isEmpty()) {
			cur = pq.poll();
			union(cur[0], cur[1], cur[2]);
		}
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}
