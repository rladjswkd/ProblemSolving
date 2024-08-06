import java.io.IOException;

public class Main {
	private static int nodeCount, edgeCount, turnCount;
	private static int[][] edges;
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
		int setCount, score, k;
		StringBuilder sb = new StringBuilder();
		// 자기 자신을 잇는 간신이 없다고 문제에 주어졌고, 인덱스가 작을수록 가중치가 적으므로, 제거되는 간선은 무조건 0, 1, 2, 3, 4,
		// ...와 같이 인덱스 순서대로일 것이다.
		// boolean[] removed;

		nodeCount = readInt();
		edgeCount = readInt();
		turnCount = readInt();
		edges = new int[edgeCount][];
		for (int i = 0; i < edgeCount; i++)
			edges[i] = new int[] { readInt() - 1, readInt() - 1 };
		ds = new int[nodeCount];
		// removed = new boolean[edgeCount];
		for (k = 0; k < turnCount; k++) {
			for (int i = 0; i < nodeCount; i++)
				ds[i] = -1;
			setCount = nodeCount;
			score = 0;
			// for (int i = 0; i < edgeCount && setCount > 1; i++) {
			// if (!removed[i] && union(edges[i][0], edges[i][1])) {
			for (int i = k; i < edgeCount && setCount > 1; i++) {
				if (union(edges[i][0], edges[i][1])) {
					score += i + 1;
					// if (setCount-- == nodeCount)
					// removed[i] = true;
					setCount--;
				}
			}
			if (setCount == 1)
				sb.append(score).append(' ');
			else
				break;
		}
		for (; k < turnCount; k++)
			sb.append(0).append(' ');
		System.out.println(sb.toString());
	}
}