import java.io.IOException;

public class Main {
	private static int n, k, res;
	private static int[][] costs;
	private static int finish;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int planet, int visited, int cost) {
		if (visited == finish) {
			res = Math.min(res, cost);
			return;
		}
		for (int next = 0; next < n; next++)
			if ((visited & 1 << next) == 0)
				solve(next, visited | 1 << next, cost + costs[planet][next]);
	}

	public static void main(String[] args) throws IOException {
		int newCost;

		n = readInt();
		k = readInt();
		costs = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				costs[i][j] = readInt();
		for (int via = 0; via < n; via++)
			for (int start = 0; start < n; start++)
				for (int end = 0; end < n; end++)
					if ((newCost = costs[start][via] + costs[via][end]) < costs[start][end])
						costs[start][end] = newCost;
		res = 10 * 1000 + 1;
		finish = (int) Math.pow(2, n) - 1;
		solve(k, 1 << k, 0);
		System.out.println(res);
	}
}