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
		int[] dp = new int[n + 1];

		dp[0] = dp[1] = 1;
		for (int i = 2; i <= n; i++)
			dp[i] = dp[i - 1] + (dp[i - 2] << 1);
		if (n % 2 == 0)
			System.out.println((dp[n] + dp[n >> 1] + 2 * dp[(n - 2) >> 1]) >> 1);
		else
			System.out.println((dp[n] + dp[n >> 1]) >> 1);
	}
}