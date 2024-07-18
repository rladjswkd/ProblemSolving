import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int[] depth, times, res;
	private static List<List<Integer>> order = new ArrayList<>();
	private static Deque<Integer> q = new ArrayDeque<>();

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			n = ~n + 1;
		return n;
	}

	private static void solve() {
		int cur;

		while (!q.isEmpty()) {
			cur = q.removeFirst();
			// 이 시점에 cur는 depth가 0이고, cur 건물을 짓기 위해 먼저 지어야 하는 모든 건물들에 대한 고려가 끝난 상황이다
			res[cur] += times[cur];
			for (int post : order.get(cur)) {
				// post의 depth가 0이되어, 큐에 담긴 후 cur로 빠져나오기 전까진 res[post]는 먼저 지어야 하는 건물들을 짓는 최대 시간만
				// 반영한다.
				res[post] = Math.max(res[post], res[cur]);
				if (--depth[post] == 0)
					q.addLast(post);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), node;
		StringBuilder sb = new StringBuilder();

		depth = new int[n];
		times = new int[n];
		res = new int[n];
		for (int i = 0; i < n; i++)
			order.add(new ArrayList<>());
		for (int i = 0; i < n; i++) {
			times[i] = readInt();
			while ((node = readInt()) != -1) {
				order.get(node - 1).add(i);
				depth[i]++;
			}
		}
		for (int i = 0; i < n; i++)
			if (depth[i] == 0)
				q.addLast(i);
		solve();
		for (int i = 0; i < n; i++)
			sb.append(res[i]).append('\n');
		System.out.print(sb.toString());
	}
}