import java.io.IOException;

public class Main {
	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), k = readInt(), walkT, walkC, cycleT, cycleC;
		long[][] dp = new long[n + 1][k + 1];

		for (int i = 1; i <= n; i++) {
			walkT = readInt();
			walkC = readInt();
			cycleT = readInt();
			cycleC = readInt();
			for (int t = 0; t <= k; t++) {
				dp[i][t] = Long.MIN_VALUE;
				if (t >= walkT)
					dp[i][t] = Math.max(dp[i][t], dp[i - 1][t - walkT] + walkC);
				if (t >= cycleT)
					dp[i][t] = Math.max(dp[i][t], dp[i - 1][t - cycleT] + cycleC);
			}
		}
		System.out.println(dp[n][k]);
	}
}