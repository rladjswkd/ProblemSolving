import java.io.IOException;

public class Main {
	private static int n;
	private static final int DIVISOR = 1000000007;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] dp = new int[15], prev;
		int nextR;

		n = read();
		dp[1] = dp[5] = 1;
		for (int length = 2; length <= n; length++) {
			prev = dp;
			dp = new int[15];
			for (int r = 0; r < 15; r++) {
				dp[nextR = ((r << 3) + (r << 1) + 1) % 15] = (dp[nextR] + prev[r]) % DIVISOR;
				dp[nextR = ((r << 3) + (r << 1) + 5) % 15] = (dp[nextR] + prev[r]) % DIVISOR;
			}
		}
		System.out.println(dp[0]);
	}
}