
/*
 * disjoint set
 * 
 * 모든 집을 하나로 연결한 후 그 중 가장 유지비가 가장 큰 도로를 제거(가장 마지막으로 union된 길 제거)
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int find(int[] ds, int node) {
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

	private static boolean union(int[] ds, int u, int v) {
		int uRoot = find(ds, u), vRoot = find(ds, v);

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

	private static int solve(int houseCount, int roadCount, PriorityQueue<int[]> pq) {
		int totalCost = 0, highestCost = 0;
		int[] cur, ds = new int[houseCount];

		for (int i = 0; i < houseCount; i++)
			ds[i] = -1;
		while (!pq.isEmpty()) {
			cur = pq.poll();
			if (union(ds, cur[0], cur[1])) {
				totalCost += cur[2];
				highestCost = cur[2];
			}
		}
		return totalCost - highestCost;
	}

	public static void main(String[] args) throws IOException {
		int houseCount = readInt(), roadCount = readInt();
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

		for (int i = 0; i < roadCount; i++)
			pq.add(new int[] { readInt() - 1, readInt() - 1, readInt() });
		bw.write(new StringBuilder().append(solve(houseCount, roadCount, pq)).append('\n').toString());
		bw.flush();
		br.close();
		bw.close();
	}
}