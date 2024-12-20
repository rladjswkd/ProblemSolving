
/*
 * 위상 정렬 
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n;
	private static int[] times, costs, degree;
	private static List<List<Integer>> orders;
	private static Deque<Integer> q;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int solve(int target) {
		int cur;

		while (!q.isEmpty()) {
			cur = q.removeFirst();
			for (int neighbor : orders.get(cur)) {
				// 이전 테스트 케이스에서 활용한 costs의 값이 남아있으면 안된다. -> 새로 인스턴스화 하거나 0으로 초기화하거나.
				costs[neighbor] = Math.max(costs[neighbor], costs[cur] + times[neighbor]);
				// neighbor 건물을 건설하기 위해 먼저 건설해야 하는 건물들을 모두 고려하면 degree[neighbor]가 0이 된다.
				// 그러면 neighbor 건물을 건설해야 건설할 수 있는 건물들을 고려할 수 있다.
				if (--degree[neighbor] == 0)
					q.addLast(neighbor);
			}
			// 모든 노드를 방문하고 끝내는 것이 아니므로 degree는 매번 새로 인스턴스화해줘야 한다.
			// 그렇지 않으면 매번 모든 값을 0으로 초기화하고 시작하거나.
			if (cur == target)
				break;
		}
		return costs[target];
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), k, post;
		StringBuilder sb = new StringBuilder();

		// times는 어차피 매번 0 ~ n - 1 범위가 입력으로 초기화되고, 그 범위만을 사용하므로 매번 새로 인스턴스화할 필요가 없다.
		times = new int[1000];
		while (t-- > 0) {
			n = readInt();
			k = readInt();
			orders = new ArrayList<>();
			q = new ArrayDeque<>();
			degree = new int[n];
			costs = new int[n];
			for (int i = 0; i < n; i++) {
				times[i] = readInt();
				orders.add(new ArrayList<>());
			}
			for (int i = 0; i < k; i++) {
				orders.get(readInt() - 1).add(post = readInt() - 1);
				degree[post]++;
			}
			for (int i = 0; i < n; i++) {
				if (degree[i] == 0) {
					q.addLast(i);
					costs[i] = times[i];
				}
			}
			sb.append(solve(readInt() - 1)).append('\n');
		}
		System.out.print(sb.toString());
	}
}