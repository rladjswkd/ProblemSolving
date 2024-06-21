
/*
 * 트리 구조 -> 상위 노드가 우수 마을이면 하위 노드는 우수 마을일 수 없다. 반대도 성립한다.
 * 1-2-3-4 같은 구조의 트리가 있고, 각각의 주민 수가 1000, 10, 100, 1000 이라 하면, 1, 4 마을이 선택되어야
 * 한다.
 * 
 * 브루트포스? -> 최대 마을 수 N이 10000이다. 우수 마을로 선택한 마을과 인접한 마을 5000개는 무조건 우수마을로 선택할 수
 * 없으므로 2^5000 -> 시간초과
 * 
 * 그리디? 큰 마을 먼저 선택
 * 7
 * 1000 3000 4000 1000 2000 2000 7000
 * 1 2
 * 2 3
 * 4 3
 * 4 5
 * 6 2
 * 6 7
 * 
 * 7 선택 -> 선택 불가: {6}, 총합 7000
 * 3 선택 -> 선택 불가: {2, 4, 6}, 총합 11000
 * 5 선택 -> 선택 불가: {2, 4, 6}, 총합 13000
 * 1 선택 -> 선택 불가: {2, 4, 6}, 총합 14000
 * 
 * 그리디가 맞을까?
 * 
 * 7000 3000 1000 1000 1000 1000 1000
 * 1 3
 * 3 2
 * 2 4
 * 2 5
 * 2 6
 * 2 7
 * 
 * 그리디로 선택하면 다음과 같다.
 * 1 선택 -> 선택 불가: {3}, 총합 7000
 * 2 선택 -> 선택 불가: {3, 4, 5, 6, 7}, 총합 10000
 * 
 * 하지만 정답은 다음과 같다.
 * 1, 4, 5, 6, 7 총합: 11000
 * 
 * 그리디 아니네
 * 
 * DP?
 * 각 노드별 우수 마을로 선택했을 때와 안했을 때를 추적 -> 선택은 인접 마을을 선택하지 않았을 때만 가능
 * 
 * bottom-up 방식
 * 
 * 어느 노드에서 시작하든 상관없다. 어차피 모든 노드의 우수 마을 선정 여부를 따질 것이기 때문이다.
 * 현재 마을을 우수 마을로 지정할 때 -> 하위 마을들은 우수 마을로 지정되지 않아야 함
 * 현재 마을을 우수 마을로 지정하지 않을 때 -> 하위 마을들의 선정 여부는 중요하지 않음. 따라서 각 서브 트리의 최댓값을 더하여 활용
 * 
 * "선정되지 못한 마을에 경각심을 불러일으키기 위해서, '우수 마을'로 선정되지 못한 마을은 적어도 하나의 '우수 마을'과는 인접해 있어야 한다."
 * -> 우수 마을로 선정되지 않은 이웃 마을만 가진 마을이 있다면, 이 마을을 선택하는 것이 최적해가 된다.
 * 따라서 우수 마을과 인접하게 하기 위한 별도의 처리가 필요하진 않다.
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int n;
	private static int[] populations;
	private static List<List<Integer>> graph = new ArrayList<>();
	private static int[][] dp;
	private static boolean[] visited;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int node) {
		// max: 이웃 마을들의 개별적인 우수 마을 선정 여부와 관계 없이 선택할 수 있는 가장 큰 우수 마을 주민 수
		// notSelected: 현재 마을이 우수 마을로 선정되기 위해 이웃 마을들이 모두 우수 마을로 선정되지 않았을 때 우수 마을 주민의 수
		int max = 0, notSelected = 0;

		visited[node] = true;
		for (int neighbor : graph.get(node)) {
			if (!visited[neighbor]) {
				solve(neighbor);
				max += Math.max(dp[neighbor][0], dp[neighbor][1]);
				notSelected += dp[neighbor][1];
			}
		}
		dp[node] = new int[] { notSelected + populations[node], max };
	}

	public static void main(String[] args) throws IOException {
		int u, v;

		populations = new int[n = readInt()];
		dp = new int[n][];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			populations[i] = readInt();
			graph.add(new ArrayList<>());
		}
		for (int i = 1; i < n; i++) {
			u = readInt() - 1;
			v = readInt() - 1;
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		// 아무 노드에서나 시작
		solve(0);
		// 시작한 노드의 dp값 중 큰 값을 출력
		System.out.println(Math.max(dp[0][0], dp[0][1]));
	}
}