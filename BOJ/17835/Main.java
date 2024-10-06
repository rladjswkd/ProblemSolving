import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	static class Edge implements Comparable<Edge> {
		int to;
		long cost;

		Edge(int to, long cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost < o.cost ? -1 : this.cost == o.cost ? 0 : 1;
		}
	}

	private static int cityCount, roadCount, interviewCityCount;
	private static List<List<int[]>> graph;
	private static long[] costs;
	private static final long INFINITY = 100000L * 100000L + 1L;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[cityCount + 1];
		Edge cur;
		long newCost;

		// 출발 노드
		pq.add(new Edge(0, 0));
		// costs[0] = 0;
		while (!pq.isEmpty()) {
			cur = pq.poll();
			if (visited[cur.to])
				continue;
			for (int[] neighbor : graph.get(cur.to)) {
				if (!visited[neighbor[0]] && (newCost = costs[cur.to] + neighbor[1]) < costs[neighbor[0]]) {
					costs[neighbor[0]] = newCost;
					pq.add(new Edge(neighbor[0], newCost));
				}
			}
			visited[cur.to] = true;
		}
	}

	public static void main(String[] args) throws IOException {
		int[] interviewCities;
		int node, idx = 0;

		// 0번 인덱스를 출발 노드로 활용하고 cityCount + 1을 도착 노드로 활용하기 위해 도시의 개수에 2를 더한다.
		graph = new ArrayList<>((cityCount = read()) + 1);
		roadCount = read();
		interviewCities = new int[interviewCityCount = read()];
		for (int i = 0; i <= cityCount; i++)
			graph.add(new ArrayList<>());
		// 단방향 그래프
		for (int i = 0; i < roadCount; i++) {
			node = read();
			graph.get(read()).add(new int[] { node, read() });
		}
		// 출발 노드와 면접장이 위치한 도시들을 연결
		for (int i = 0; i < interviewCityCount; i++)
			graph.get(0).add(new int[] { interviewCities[i] = read(), 0 });
		// 다익스트라
		//// costs 배열 초기화
		costs = new long[cityCount + 1];
		for (int i = 1; i <= cityCount; i++)
			costs[i] = INFINITY;
		solve();
		for (int i = 1; i <= cityCount; i++)
			if (costs[idx] < costs[i])
				idx = i;
		System.out.println(new StringBuilder().append(idx).append('\n').append(costs[idx]).toString());
	}
}