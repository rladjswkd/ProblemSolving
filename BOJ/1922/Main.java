import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] ds;
	private static PriorityQueue<int[]> pq;
	private static int res = 0, linkCount = 0;

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

	private static void union(int u, int v, int weight) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return;
		res += weight;
		linkCount++;
		if (ds[uRoot] < ds[vRoot]) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		int nodeCount	= readInt(), edgeCount = readInt();
		int[] edge;

		ds = new int[nodeCount];
		for (int i = 0; i < nodeCount; i++)
			ds[i] = -1;
		pq = new PriorityQueue<>(edgeCount, (a1, a2) -> a1[2] - a2[2]);
		while (edgeCount-- > 0)
			pq.add(new int[]{readInt() - 1, readInt() - 1, readInt()});
		while (linkCount < nodeCount - 1) {
			edge = pq.poll();
			union(edge[0], edge[1], edge[2]);
		}
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}