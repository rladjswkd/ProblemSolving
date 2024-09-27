import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int stationCount;
	private static List<List<Integer>> graph;
	private static boolean[] visited;
	private static int[] dist;
	private static Deque<Integer> q;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int findCycle(int station, int prev) {
		List<Integer> neighbors = graph.get(station);
		int head;

		// 현재 역을 방문 처리
		visited[station] = true;
		for (int neighbor : neighbors) {
			// neighbor 역이 prev와 같은 역이라면 넘기기
			if (neighbor == prev)
				continue;
			// 현재 역과 연결되어있는 neighbor 역에 이미 방문했고, 현재 역을 방문하기 직전 역인 prev와는 다른 역이라면 사이클이 존재하는 것
			// 이때는 neighbor를 반환해 사이클의 시작 지점으로 활용한다.
			if (visited[neighbor]) {
				dist[station] = 1;
				q.addLast(station);
				return neighbor;
			}
			if (0 <= (head = findCycle(neighbor, station)) && head < stationCount) {
				dist[station] = 1;
				q.addLast(station);
				// 사이클의 시작 지점인 head에 도달하면 존재할 수 없는 역 번호인 stationCount를 반환해
				// head보다 이전의 역들은 사이클에 포함시키지 않는다.
				return head == station ? stationCount : head;
			} else if (head == stationCount)
				// stationCount를 반환받은 역에선 그냥 stationCount를 반환한다.
				return stationCount;
		}
		return -1;
	}

	private static void calculateDist() {
		int cur, size, d;

		d = 2;
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				for (int neighbor : graph.get(cur)) {
					if (dist[neighbor] == 0) {
						dist[neighbor] = d;
						q.addLast(neighbor);
					}
				}
			}
			d++;
		}
	}

	public static void main(String[] args) throws IOException {
		int u, v;
		StringBuilder sb = new StringBuilder();

		graph = new ArrayList<>(stationCount = read());
		for (int i = 0; i < stationCount; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < stationCount; i++) {
			graph.get(u = read() - 1).add(v = read() - 1);
			graph.get(v).add(u);
		}
		visited = new boolean[stationCount];
		dist = new int[stationCount];
		q = new ArrayDeque<>();
		// 순환선에 포함되는 역들은 ringRoadStations에 true로 설정된다.
		findCycle(0, -1);
		// 순환선에 포함되는 역들을 찾아 BFS 실행
		calculateDist();
		for (int d : dist)
			sb.append(d - 1).append(' ');
		System.out.println(sb.toString());
	}
}