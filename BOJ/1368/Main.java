import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}
	}

	private static int setCount;
	private static int[] ds;
	private static PriorityQueue<Edge> pq;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
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
		int res = 0;
		Edge e;

		pq = new PriorityQueue<>();
		// setCount는 논의 개수에 가상의 노드를 더한 값으로, 분리 집합의 개수를 추적한다.
		ds = new int[setCount = read() + 1];
		// ds[0]을 유일한 수원인 가상의 노드로 활용
		ds[0] = -1;
		for (int i = 1; i < setCount; i++) {
			ds[i] = -1;
			pq.add(new Edge(0, i, read()));
		}
		for (int i = 1; i < setCount; i++) {
			for (int j = 1; j < i; j++)
				pq.add(new Edge(i, j, read()));
			read();
			for (int j = i + 1; j < setCount; j++)
				pq.add(new Edge(i, j, read()));
		}
		while (setCount > 1) {
			e = pq.poll();
			if (union(e.from, e.to)) {
				res += e.weight;
				setCount--;
			}
		}
		System.out.println(res);
	}
}