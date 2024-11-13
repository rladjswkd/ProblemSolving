import java.io.IOException;

public class Main {
	private static int n;
	private static int[] l, r;
	private static int[][] dp;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int solve(int li, int ri) {
		if (li == n || ri == n)
			return 0;
		if (dp[li][ri] > 0)
			return dp[li][ri];
		if (l[li] > r[ri])
			return dp[li][ri] = r[ri] + solve(li, ri + 1);
		else
			return dp[li][ri] = Math.max(solve(li + 1, ri), solve(li + 1, ri + 1));
	}

	public static void main(String[] args) throws IOException {
		l = new int[n = read()];
		r = new int[n];
		for (int i = 0; i < n; i++)
			l[i] = read();
		for (int i = 0; i < n; i++)
			r[i] = read();
		dp = new int[n][n];
		for (int[] each : dp)
			for (int i = 0; i < n; i++)
				each[i] = -1;
		System.out.println(solve(0, 0));
	}
}