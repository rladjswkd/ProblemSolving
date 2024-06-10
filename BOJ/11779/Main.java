import java.io.*;
import java.util.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static final int INFINITY = 1000 * 100000 + 1;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(List<List<int[]>> graph, int cityCount, int startCity, int targetCity) {
		int[] costs = new int[cityCount], prev = new int[cityCount], cur;
		boolean[] processed = new boolean[cityCount];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		Deque<Integer> stack = new ArrayDeque<>();

		for (int i = 0; i < cityCount; i++) {
			costs[i] = INFINITY;
			prev[i] = i;
		}
		costs[startCity] = 0;
		pq.add(new int[] { startCity, 0 });
		while (!pq.isEmpty()) {
			cur = pq.poll();
			if (costs[cur[0]] != cur[1])
				continue;
			for (int[] neighbor : graph.get(cur[0])) {
				if (!processed[neighbor[0]] && costs[cur[0]] + neighbor[1] < costs[neighbor[0]]) {
					costs[neighbor[0]] = costs[cur[0]] + neighbor[1];
					pq.add(new int[] { neighbor[0], costs[neighbor[0]] });
					prev[neighbor[0]] = cur[0];
				}
			}
		}
		stack.addLast(targetCity);
		while (stack.getLast() != startCity)
			stack.addLast(prev[stack.getLast()]);
		sb.append(costs[targetCity]).append('\n').append(stack.size()).append('\n');
		while (!stack.isEmpty())
			sb.append(stack.removeLast() + 1).append(' ');
	}

	public static void main(String[] args) throws IOException {
		int cityCount = readInt(), busCount = readInt();
		List<List<int[]>> graph = new ArrayList<>();

		for (int i = 0; i < cityCount; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < busCount; i++)
			graph.get(readInt() - 1).add(new int[] { readInt() - 1, readInt() });
		solve(graph, cityCount, readInt() - 1, readInt() - 1);
		System.out.println(sb.toString());
	}
}