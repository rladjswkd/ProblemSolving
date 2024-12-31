import java.io.IOException;

public class Main {
	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = read();
		double[] dp = new double[n + 7];

		for (int i = 1; i <= n; i++) {
			dp[i] = 1 + (dp[Math.max(i - 1, 0)] + dp[Math.max(i - 2, 0)] + dp[Math.max(i - 3, 0)] +
					dp[Math.max(i - 4, 0)] + dp[Math.max(i - 5, 0)] + dp[Math.max(i - 6, 0)]) / 6;
		}
		System.out.println(dp[n]);
	}
}