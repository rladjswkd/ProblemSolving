import java.io.IOException;

public class Main {
	private static int t, n, target;
	private static int[] coins;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] dp;
		StringBuilder sb = new StringBuilder();

		t = read();
		coins = new int[20];
		while (t-- > 0) {
			n = read();
			for (int i = 0; i < n; i++)
				coins[i] = read();
			target = read();
			dp = new int[target + 1];
			for (int i = 0; i < n; i++) {
				if (target < coins[i])
					break;
				dp[coins[i]]++;
				for (int cur = coins[i] + 1; cur <= target; cur++)
					dp[cur] += dp[cur - coins[i]];
			}
			sb.append(dp[target]).append('\n');
		}
		System.out.print(sb.toString());
	}
}