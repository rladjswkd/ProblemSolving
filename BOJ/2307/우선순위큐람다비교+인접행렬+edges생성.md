### 732 ms

```java
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	private static int vertexCount, edgeCount, time;
	private static int[][] graph;
	private static PriorityQueue<int[]> q;
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
		int[] cur, neighbor;
		boolean[] visited = new boolean[vertexCount];
		int newCost;

		for (int i = 1; i < vertexCount; i++)
			costs[i] = INFINITY;
		q.add(new int[] { 0, 0 });
		while (!q.isEmpty()) {
			cur = q.poll();
			if (visited[cur[0]])
				continue;
			neighbor = graph[cur[0]];
			for (int i = 0; i < vertexCount; i++) {
				if ((newCost = costs[cur[0]] + neighbor[i]) < costs[i]) {
					costs[i] = newCost;
					prevs[i] = cur[0];
					q.add(new int[] { i, newCost });
				}
			}
			visited[cur[0]] = true;
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
		q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
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
