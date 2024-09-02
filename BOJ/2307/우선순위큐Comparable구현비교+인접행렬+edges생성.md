### 632 ms

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
	private static int[][] graph;
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
		int[] neighbor;
		boolean[] visited = new boolean[vertexCount];
		int newCost;

		for (int i = 1; i < vertexCount; i++)
			costs[i] = INFINITY;
		q.add(new Edge(0, 0));
		while (!q.isEmpty()) {
			cur = q.poll();
			if (visited[cur.to])
				continue;
			neighbor = graph[cur.to];
			for (int i = 0; i < vertexCount; i++) {
				if ((newCost = costs[cur.to] + neighbor[i]) < costs[i]) {
					costs[i] = newCost;
					prevs[i] = cur.to;
					q.add(new Edge(i, newCost));
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
		graph = new int[vertexCount][vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			for (int j = 0; j < i; j++)
				graph[i][j] = INFINITY;
			for (int j = i + 1; j < vertexCount; j++)
				graph[i][j] = INFINITY;
		}
		for (int i = 0; i < edgeCount; i++)
			graph[u = readInt() - 1][v = readInt() - 1] = graph[v][u] = readInt();
		q = new PriorityQueue<>();
		costs = new int[vertexCount];
		prevs = new int[vertexCount];
		solve();
		before = time;
		edges = new ArrayList<>();
		u = vertexCount - 1;
		while (u != 0)
			edges.add(new int[] { u, u = prevs[u] });
		for (int[] edge : edges) {
			c = graph[edge[1]][edge[0]];
			graph[edge[1]][edge[0]] = graph[edge[0]][edge[1]] = INFINITY;
			solve();
			if (time == INFINITY) {
				res = -1;
				break;
			}
			res = Math.max(res, time - before);
			graph[edge[1]][edge[0]] = graph[edge[0]][edge[1]] = c;
		}
		System.out.println(res);
	}
}
```
