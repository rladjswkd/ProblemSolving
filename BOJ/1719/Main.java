import java.io.IOException;

public class Main {
	private static int centerCount, routeCount;
	private static int[][] costs, res;
	private static final int INFINITY = 200 * 1000 + 1;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int u, v, newCost, idx;
		StringBuilder sb = new StringBuilder();

		centerCount = readInt();
		routeCount = readInt();
		costs = new int[centerCount][centerCount];
		res = new int[centerCount][centerCount];
		for (int i = 0; i < centerCount; i++) {
			for (int j = 0; j < i; j++)
				costs[i][j] = INFINITY;
			for (int j = i + 1; j < centerCount; j++)
				costs[i][j] = INFINITY;
			res[i][i] = i;
		}
		for (int i = 0; i < routeCount; i++) {
			u = readInt() - 1;
			v = readInt() - 1;
			costs[u][v] = costs[v][u] = readInt();
			// 원래는 prevs[from][to]면 from을 저장해야 한다.
			// 하지만 res에는 직전 노드를 저장하는 것이 아니다.
			// 다익스트라의 최적화를 생각해보자(다익스트라를 먼저 보자.)
			res[u][v] = v;
			res[v][u] = u;
		}
		for (int via = 0; via < centerCount; via++) {
			for (int start = 0; start < centerCount; start++) {
				for (int end = 0; end < centerCount; end++) {
					if ((newCost = costs[start][via] + costs[via][end]) < costs[start][end]) {
						costs[start][end] = newCost;
						// 원래대로 직전 노드를 저장하는 부분이라면, res[start][end] = res[via][end]가 와야한다.
						res[start][end] = res[start][via];
					}
				}
			}
		}
		for (int i = 0; i < centerCount; i++) {
			for (int j = 0; j < centerCount; j++) {
				if (i == j)
					sb.append('-').append(' ');
				else {
					sb.append(res[i][j] + 1).append(' ');
				}
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}
