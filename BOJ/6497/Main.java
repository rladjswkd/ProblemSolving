
/*
 * 모든 집을 왕래할 수 있게 길을 연결하되 최소 비용의 길만 연결하고 나머지는 제거 -> 최소 신장 트리
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

	private static int solve(int[] ds, PriorityQueue<int[]> roads, int houseCount) {
		int[] cur;
		int res = 0, connectionCount = 0;

		for (int i = 0; i < houseCount; i++)
			ds[i] = -1;
		while (connectionCount < houseCount - 1) {
			cur = roads.poll();
			if (union(ds, cur[0], cur[1]))
				connectionCount++;
			else
				res += cur[2];
		}
		while (!roads.isEmpty())
			res += roads.poll()[2];
		return res;
	}

	public static void main(String[] args) throws IOException {
		int houseCount, roadCount;
		PriorityQueue<int[]> roads = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		StringBuilder sb = new StringBuilder();

		while ((houseCount = readInt()) + (roadCount = readInt()) > 0) {
			for (int i = 0; i < roadCount; i++)
				roads.add(new int[] { readInt(), readInt(), readInt() });
			sb.append(solve(new int[houseCount], roads, houseCount)).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}