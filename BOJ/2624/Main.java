import java.io.IOException;

public class Main {
	private static int t, k;
	private static int[] values, counts;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] dp, prev, swap;
		int value, current;

		t = read();
		k = read();
		values = new int[k];
		counts = new int[k];
		for (int i = 0; i < k; i++) {
			values[i] = read();
			counts[i] = read();
		}
		dp = new int[t + 1];
		prev = new int[t + 1];
		dp[0] = prev[0] = 1;
		for (int coin = 0; coin < k; coin++) {
			swap = prev;
			prev = dp;
			dp = swap;
			value = values[coin];
			for (int idx = 1; idx <= t; idx++) {
				dp[idx] = prev[idx];
				for (int cnt = 1; cnt <= counts[coin]; cnt++)
					if ((current = value * cnt) <= idx)
						dp[idx] += prev[idx - current];
			}
		}
		System.out.println(dp[t]);
	}
}