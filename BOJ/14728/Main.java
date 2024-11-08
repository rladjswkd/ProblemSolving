import java.io.IOException;

public class Main {
	private static int n, t;
	private static int[] k, s;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] dp;

		n = read();
		t = read();
		k = new int[n];
		s = new int[n];
		for (int i = 0; i < n; i++) {
			k[i] = read();
			s[i] = read();
		}
		dp = new int[t + 1];
		for (int i = k[0]; i <= t; i++)
			dp[i] = s[0];
		for (int i = 1; i < n; i++) {
			for (int j = t; j >= k[i]; j--)
				dp[j] = Math.max(dp[j], dp[j - k[i]] + s[i]);
		}
		System.out.println(dp[t]);
	}
}