import java.io.IOException;

public class Main {
	private static int n;
	private static boolean[][] blank;
	private static long[][][] dp;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int i, int j, int dir) {
		long count = 0;

		if (i == j && j == n - 1) {
			dp[i][j][dir] = 1;
			return;
		}
		if (j + 1 < n && blank[i][j + 1] && dir != 1) {
			if (dp[i][j + 1][0] == -1)
				solve(i, j + 1, 0);
			count += dp[i][j + 1][0];
		}
		if (i + 1 < n && blank[i + 1][j] && dir >= 1) {
			if (dp[i + 1][j][1] == -1)
				solve(i + 1, j, 1);
			count += dp[i + 1][j][1];
		}
		if (i + 1 < n && j + 1 < n && blank[i + 1][j + 1] && blank[i + 1][j] && blank[i][j + 1]) {
			if (dp[i + 1][j + 1][2] == -1)
				solve(i + 1, j + 1, 2);
			count += dp[i + 1][j + 1][2];
		}
		dp[i][j][dir] = count;
	}

	public static void main(String[] args) throws IOException {
		long[] dir;

		blank = new boolean[n = read()][n];
		dp = new long[n][n][3];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				(dir = dp[i][j])[0] = dir[1] = dir[2] = -1;
				blank[i][j] = System.in.read() == '0';
				System.in.read();
			}
		}
		dp[0][1][0] = 1;
		solve(0, 1, 0);
		System.out.println(dp[0][1][0]);
	}
}