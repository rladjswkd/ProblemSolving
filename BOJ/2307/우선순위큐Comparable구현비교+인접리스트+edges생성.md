### 444ms

```java
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	static class Edge implements Comparable<Edge> {
		int to, cost;

		Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}

	private static int vertexCount, edgeCount, time;
	private static List<List<Edge>> graph;
	private static PriorityQueue<Edge> q;
	private static int[] costs, prevs;
	private static final int INFINITY = 5000 * 10000 + 1;
	private static List<int[]> edges;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		Edge cur;
		boolean[] visited = new boolean[vertexCount];
		int newCost;

		for (int i = 1; i < vertexCount; i++)
			costs[i] = INFINITY;
		q.add(new Edge(0, 0));
		while (!q.isEmpty()) {
			cur = q.poll();
			if (visited[cur.to])
				continue;
			for (Edge e : graph.get(cur.to)) {
				if ((newCost = costs[cur.to] + e.cost) < costs[e.to]) {
					costs[e.to] = newCost;
					prevs[e.to] = cur.to;
					q.add(new Edge(e.to, newCost));
				}
			}
			visited[cur.to] = true;
		}
		time = costs[vertexCount - 1];
	}

	private static void solve(int from, int to) {
		Edge cur;
		boolean[] visited = new boolean[vertexCount];
		int newCost;

		for (int i = 1; i < vertexCount; i++)
			costs[i] = INFINITY;
		q.add(new Edge(0, 0));
		while (!q.isEmpty()) {
			cur = q.poll();
			if (visited[cur.to])
				continue;
			for (Edge e : graph.get(cur.to)) {
				if ((cur.to != from || e.to != to) && (newCost = costs[cur.to] + e.cost) < costs[e.to]) {
					costs[e.to] = newCost;
					q.add(new Edge(e.to, newCost));
				}
			}
			visited[cur.to] = true;
		}
		time = costs[vertexCount - 1];
	}

	public static void main(String[] args) throws IOException {
		int u, v, c, before, res = 0;

		vertexCount = readInt();
		edgeCount = readInt();
		graph = new ArrayList<>();
		for (int i = 0; i < vertexCount; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < edgeCount; i++) {
			graph.get(u = readInt() - 1).add(new Edge(v = readInt() - 1, c = readInt()));
			graph.get(v).add(new Edge(u, c));
		}
		q = new PriorityQueue<>();
		costs = new int[vertexCount];
		prevs = new int[vertexCount];
		solve();
		before = time;
		edges = new ArrayList<>();
		u = vertexCount - 1;
		while (u != 0)
			edges.add(new int[] { u, u = prevs[u] });
		// 인접 리스트이므로, O(1)에 대상 간선의 가중치를 변경할 수 없다.
		// 다익스트라를 실행하면서 edge를 식별해 해당 edge에 대해선 costs를 업데이트하지 말자.
		for (int[] edge : edges) {
			// 지나가는 방향이 prevs[i] 다음에 i이므로 edge[1], edge[0] 순서로 전달한다.
			solve(edge[1], edge[0]);
			if (time == INFINITY) {
				res = -1;
				break;
			}
			res = Math.max(res, time - before);
		}
		System.out.println(res);
	}
}
```
