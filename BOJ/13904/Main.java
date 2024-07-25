import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), maxD = 0, curD, d, w;
		int[] assignments = new int[n];
		int[][] dp;

		for (int i = 0; i < n; i++) {
			assignments[i] = (curD = readInt()) * 1000 + readInt();
			if (maxD < curD)
				maxD = curD;
		}
		dp = new int[n + 1][maxD + 1];
		Arrays.sort(assignments);
		for (int i = 1; i <= n; i++) {
			d = assignments[i - 1] / 1000;
			w = assignments[i - 1] % 1000;
			for (int j = 1; j <= maxD; j++) {
				if (d < j)
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				else
					dp[i][j] = Math.max(dp[i - 1][j - 1] + w, dp[i - 1][j]);
			}
		}
		System.out.println(dp[n][maxD]);
	}
}