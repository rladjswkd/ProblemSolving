import java.io.IOException;

public class Main {
	private static int[] calories, prices, dp;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int readPrice() throws IOException {
		return read() * 100 + read();
	}

	public static void main(String[] args) throws IOException {
		int candyCount, coin = 0, cal, pri;
		StringBuilder sb = new StringBuilder();

		calories = new int[5000];
		prices = new int[5000];
		while ((candyCount = read()) + (coin = readPrice()) != 0) {
			for (int i = 0; i < candyCount; i++) {
				calories[i] = read();
				prices[i] = readPrice();
			}
			dp = new int[coin + 1];
			for (int i = 0; i < candyCount; i++) {
				cal = calories[i];
				pri = prices[i];
				for (int price = pri; price <= coin; price++)
					dp[price] = Math.max(dp[price], dp[price - pri] + cal);
			}
			sb.append(dp[coin]).append('\n');
		}
		System.out.print(sb.toString());
	}
}