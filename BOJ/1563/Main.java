import java.io.IOException;

public class Main {
	private static final int D = 1000000;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && (c <= 57))
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = read();
		int[] dp = new int[6], prev = new int[6], swap = new int[6];

		dp[0] = dp[2] = dp[5] = 1;
		for (int d = 1; d < n; d++) {
			swap = dp;
			dp = prev;
			prev = swap;
			dp[0] = prev[2] % D;
			dp[1] = prev[0] % D;
			dp[2] = (prev[0] + prev[1] + prev[2]) % D;
			dp[3] = prev[5] % D;
			dp[4] = prev[3] % D;
			dp[5] = (prev[0] + prev[1] + prev[2] + prev[3] + prev[4] + prev[5]) % D;
		}
		System.out.println((dp[0] + dp[1] + dp[2] + dp[3] + dp[4] + dp[5]) % D);
	}
}