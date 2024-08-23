import java.io.IOException;

public class Main {
	private static int cityCount, busCount;
	private static final int INFINITY = 100 * 100000 + 1;
	private static int[][] costs, prev;
	private static StringBuilder sb;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void findRoute(int from, int to, int count) {
		if (to == from) {
			sb.append(count + 1).append(' ').append(from + 1).append(' ');
			return;
		}
		findRoute(from, prev[from][to], count + 1);
		sb.append(to + 1).append(' ');
	}

	public static void main(String[] args) throws IOException {
		int u, v, newCost;

		cityCount = readInt();
		busCount = readInt();
		costs = new int[cityCount][cityCount];
		prev = new int[cityCount][cityCount];
		for (int i = 0; i < cityCount; i++) {
			for (int j = 0; j < i; j++)
				costs[i][j] = INFINITY;
			for (int j = i + 1; j < cityCount; j++)
				costs[i][j] = INFINITY;
			prev[i][i] = i;
		}
		for (int i = 0; i < busCount; i++) {
			costs[u = readInt() - 1][v = readInt() - 1] = Math.min(costs[u][v], readInt());
			prev[u][v] = u;
		}
		for (int via = 0; via < cityCount; via++) {
			for (int start = 0; start < cityCount; start++) {
				for (int end = 0; end < cityCount; end++) {
					if ((newCost = costs[start][via] + costs[via][end]) < costs[start][end]) {
						costs[start][end] = newCost;
						prev[start][end] = prev[via][end];
					}
				}
			}
		}
		sb = new StringBuilder();
		for (int i = 0; i < cityCount; i++) {
			for (int j = 0; j < cityCount; j++) {
				if (costs[i][j] == INFINITY)
					sb.append(0).append(' ');
				else
					sb.append(costs[i][j]).append(' ');
			}
			sb.append('\n');
		}
		for (int i = 0; i < cityCount; i++) {
			for (int j = 0; j < cityCount; j++) {
				if (i == j || costs[i][j] == INFINITY)
					sb.append(0);
				else
					findRoute(i, j, 0);
				sb.append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}