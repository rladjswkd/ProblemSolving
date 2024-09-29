import java.io.IOException;

public class Main {
	private static int coinCount, target;
	private static int[] coins;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int t = read(), coin;
		int[] dp;
		StringBuilder sb = new StringBuilder();

		coins = new int[20];
		while (t-- > 0) {
			coinCount = read();
			for (int i = 0; i < coinCount; i++)
				coins[i] = read();
			dp = new int[(target = read()) + 1];
			dp[0] = 1;
			for (int i = 0; i < coinCount; i++) {
				coin = coins[i];
				for (int x = coin; x <= target; x++)
					dp[x] += dp[x - coin];
			}
			sb.append(dp[target]).append('\n');
		}
		System.out.print(sb.toString());
	}
}