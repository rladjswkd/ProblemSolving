import java.io.IOException;

public class Main {
	private static int n, rc, gc, bc;
	private static int[] factorial;
	private static long[][][][] dp;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	// li: level index
	private static long solve(int level, int r, int g, int b) {
		long count = 0;
		int d;

		if (r < 0 || g < 0 || b < 0)
			return 0;
		if (level > n)
			return 1;
		if (dp[level - 1][r][g][b] != 0)
			return dp[level - 1][r][g][b];
		d = level;
		count += solve(level + 1, r - d, g, b);
		count += solve(level + 1, r, g - d, b);
		count += solve(level + 1, r, g, b - d);
		if (level % 2 == 0) {
			d = level / 2;
			count += factorial[level - 1] / factorial[d - 1] / factorial[d - 1] * (solve(level + 1, r - d, g - d, b) +
					solve(level + 1, r - d, g, b - d) +
					solve(level + 1, r, g - d, b - d));
		}
		if (level % 3 == 0) {
			d = level / 3;
			count += factorial[level - 1] / factorial[d - 1] / factorial[d - 1] / factorial[d - 1]
					* solve(level + 1, r - d, g - d, b - d);
		}
		return dp[level - 1][r][g][b] = count;
	}

	public static void main(String[] args) throws IOException {
		factorial = new int[10];
		factorial[0] = 1;
		for (int i = 1; i < 10; i++)
			factorial[i] = factorial[i - 1] * (i + 1);
		dp = new long[n = read()][(rc = read()) + 1][(gc = read()) + 1][(bc = read()) + 1];
		System.out.println(solve(1, rc, gc, bc));
	}
}