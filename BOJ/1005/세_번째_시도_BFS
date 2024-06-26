
/*
 * BFS
 * 
 * 1 -> (2, 3) -> 4 와 같이 주어졌을 때, 2와 3의 건설을 동시에 시작할 수 있다 하더라도 어차피 더 오래 걸리는 건물을 짓지 못하면 4를 짓지 못한다.
 * 무조건 최댓값을 선택하는 것이 맞다.
 * 
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n;
	private static int[] times = new int[1000], costs = new int[1000];
	private static List<List<Integer>> orders;
	private static Deque<Integer> q = new ArrayDeque<>();

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int solve(int target) {
		int cur, res, newCost;

		// 건설 시간이 0일 수 있으므로, costs의 초깃값을 -1로 설정해 도달하지 못한 건물을 표현한다.
		for (int i = 0; i < n; i++)
			costs[i] = -1;
		q.addLast(target);
		res = costs[target] = times[target];
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			for (int neighbor : orders.get(cur)) {
				// 업데이트되어야 할 때만 큐에 넣어줘야 중복으로 들어가지 않는다.
				// 이를 처리해주지 않아서 메모리 초과가 발생헀던 듯하다.
				if (costs[neighbor] < (newCost = costs[cur] + times[neighbor])) {
					costs[neighbor] = newCost;
					res = Math.max(res, newCost);
					q.addLast(neighbor);
				}
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), k, pre;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			n = readInt();
			k = readInt();
			orders = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				times[i] = readInt();
				orders.add(new ArrayList<>());
			}
			for (int i = 0; i < k; i++) {
				pre = readInt() - 1;
				orders.get(readInt() - 1).add(pre);
			}
			sb.append(solve(readInt() - 1)).append('\n');
		}
		System.out.print(sb.toString());
	}
}