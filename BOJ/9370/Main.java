import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {
	private static int rotaryCount, roadCount, destinationCount, s, g, h, inter;
	private static List<List<int[]>> graph;
	private static int[] destinations, costsStart, costsInter;
	private static PriorityQueue<int[]> pq;
	private static final int INFINITY = 2000 * 1000 + 1;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void findShortestPath(int first, boolean fromStart) {
		int[] costs, cur;
		boolean[] visited = new boolean[rotaryCount];
		int newCost;

		costs = fromStart ? costsStart : costsInter;
		for (int i = 0; i < rotaryCount; i++)
			costs[i] = INFINITY;
		pq.add(new int[] { first, 0 });
		costs[first] = 0;
		while (!pq.isEmpty()) {
			cur = pq.poll();
			if (visited[cur[0]])
				continue;
			for (int[] neighbor : graph.get(cur[0])) {
				if (!visited[neighbor[0]] && ((newCost = costs[cur[0]] + neighbor[1]) < costs[neighbor[0]])) {
					costs[neighbor[0]] = newCost;
					pq.add(new int[] { neighbor[0], newCost });
				}
			}
			visited[cur[0]] = true;
		}
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), a, b, d;
		StringBuilder sb = new StringBuilder();

		pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
		costsStart = new int[2000];
		costsInter = new int[2000];
		sb = new StringBuilder();
		while (t-- > 0) {
			rotaryCount = readInt();
			roadCount = readInt();
			destinations = new int[destinationCount = readInt()];
			s = readInt() - 1;
			g = readInt() - 1;
			h = readInt() - 1;
			graph = new ArrayList<>();
			for (int i = 0; i < rotaryCount; i++)
				graph.add(new ArrayList<>());
			for (int i = 0; i < roadCount; i++) {
				a = readInt() - 1;
				b = readInt() - 1;
				d = readInt();
				graph.get(a).add(new int[] { b, d });
				graph.get(b).add(new int[] { a, d });
			}
			// s에서 시작하는 다익스트라 연산
			findShortestPath(s, true);
			inter = costsStart[g] < costsStart[h] ? h : g;
			findShortestPath(inter, false);
			for (int i = 0; i < destinationCount; i++)
				destinations[i] = readInt() - 1;
			Arrays.sort(destinations);
			// a, d 재활용
			d = costsStart[inter];
			for (int i = 0; i < destinationCount; i++)
				// a 재활용
				if (d + costsInter[a = destinations[i]] == costsStart[a])
					sb.append(a + 1).append(' ');
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}