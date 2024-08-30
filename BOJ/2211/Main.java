import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	private static int computerCount, lineCount;
	private static List<List<int[]>> graph;
	private static int[] prevs;
	private static final int INFINITY = 1000 * 10 + 1;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		int[] cur, costs = new int[computerCount];
		boolean[] visited = new boolean[computerCount];
		int newCost;

		for (int i = 1; i < computerCount; i++) {
			costs[i] = INFINITY;
			prevs[i] = -1;
		}
		prevs[0] = -1;
		pq.add(new int[] { 0, 0 });
		while (!pq.isEmpty()) {
			cur = pq.poll();
			if (visited[cur[0]])
				continue;
			for (int[] neighbor : graph.get(cur[0])) {
				if ((newCost = costs[cur[0]] + neighbor[1]) < costs[neighbor[0]]) {
					costs[neighbor[0]] = newCost;
					pq.add(new int[] { neighbor[0], newCost });
					prevs[neighbor[0]] = cur[0];
				}
			}
			visited[cur[0]] = true;
		}
	}

	public static void main(String[] args) throws IOException {
		int a, b, c;
		StringBuilder sb = new StringBuilder();

		computerCount = readInt();
		lineCount = readInt();
		graph = new ArrayList<>();
		for (int i = 0; i < computerCount; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < lineCount; i++) {
			graph.get(a = readInt() - 1).add(new int[] { b = readInt() - 1, c = readInt() });
			graph.get(b).add(new int[] { a, c });
		}
		prevs = new int[computerCount];
		solve();
		sb.append(computerCount - 1).append('\n');
		for (int i = 1; i < computerCount; i++)
			sb.append(i + 1).append(' ').append(prevs[i] + 1).append('\n');
		System.out.print(sb.toString());
	}
}