import java.io.IOException;

public class Main {
	private static int n;
	private static final int DIVISOR = 1000000000;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] dp = new int[(n = read() >>> 1) + 2];
		int idx = 1, count;
		dp[0] = 1;
		dp[1] = count = 2;
		for (int cur = 2; cur <= n; cur += 2) {
			dp[cur] = (count = (int) ((long) count + dp[idx]) % DIVISOR);
			dp[cur + 1] = (count = (int) ((long) count + dp[idx]) % DIVISOR);
			idx++;
		}
		System.out.println(dp[n]);
	}
}