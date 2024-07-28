import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	private static int isleCount, isle1, isle2, res;
	private static List<List<int[]>> graph;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		int[] cur;
		boolean[] visited = new boolean[isleCount];

		q.add(new int[] { isle1, 1_000_000_001 });
		while (!q.isEmpty()) {
			cur = q.poll();
			if (cur[0] == isle2) {
				res = cur[1];
				break;
			}
			if (visited[cur[0]])
				continue;
			for (int[] neighbor : graph.get(cur[0])) {
				if (!visited[neighbor[0]])
					// cur 다리(경로 상 neighbor 다리 직전의 다리)의 중량과 neighbor 다리의 중량 중
					// 더 작은 중량만이 해당 경로에서 허용된다.
					q.add(new int[] { neighbor[0], Math.min(cur[1], neighbor[1]) });
			}
			visited[cur[0]] = true;
		}
	}

	public static void main(String[] args) throws IOException {
		int bridgeCount, a, b, c;

		isleCount = readInt();
		bridgeCount = readInt();
		graph = new ArrayList<>();
		for (int i = 0; i < isleCount; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < bridgeCount; i++) {
			a = readInt() - 1;
			b = readInt() - 1;
			c = readInt();
			graph.get(a).add(new int[] { b, c });
			graph.get(b).add(new int[] { a, c });
		}
		isle1 = readInt() - 1;
		isle2 = readInt() - 1;
		solve();
		System.out.println(res);
	}
}