import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	private static int hutCount, roadCount, res;
	private static List<List<int[]>> graph;
	private static final int INFINITY = 50000 * 1000 + 1;
	private static int[] costs;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int start) {
		int[] cur;
		boolean[] visited = new boolean[hutCount];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		int newCost;

		costs = new int[hutCount];
		for (int i = 0; i < hutCount; i++)
			costs[i] = INFINITY;
		pq.add(new int[] { start, 0 });
		costs[start] = 0;
		while (!pq.isEmpty()) {
			cur = pq.poll();
			if (visited[cur[0]])
				continue;
			for (int[] neighbor : graph.get(cur[0])) {
				if ((newCost = costs[cur[0]] + neighbor[1]) < costs[neighbor[0]]) {
					costs[neighbor[0]] = newCost;
					pq.add(new int[] { neighbor[0], newCost });
				}
			}
			visited[cur[0]] = true;
		}
	}

	public static void main(String[] args) throws IOException {
		int a, b, c;

		hutCount = readInt();
		roadCount = readInt();
		graph = new ArrayList<>();
		for (int i = 0; i < hutCount; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < roadCount; i++) {
			a = readInt() - 1;
			b = readInt() - 1;
			c = readInt();
			graph.get(a).add(new int[] { b, c });
			graph.get(b).add(new int[] { a, c });
		}
		solve(0);
		System.out.println(costs[hutCount - 1]);
	}
}