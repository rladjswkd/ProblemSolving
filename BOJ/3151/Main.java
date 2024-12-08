import java.io.IOException;

public class Main {
	private static final int BASE = 20000;

	private static int read() throws IOException {
		int n = 0, c, s = System.in.read();

		if (s != 45)
			n = s & 15;
		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return s != 45 ? n : ~n + 1;
	}

	public static void main(String[] args) throws IOException {
		int n = read();
		int[] a, dp;
		long res = 0;

		if (n >= 3) {
			a = new int[n];
			dp = new int[40001];
			dp[BASE + (a[0] = read()) + (a[1] = read())]++;
			for (int i = 2; i < n; i++) {
				res += dp[BASE + -(a[i] = read())];
				for (int j = 0; j < i; j++)
					dp[BASE + a[i] + a[j]]++;
			}
		}
		System.out.println(res);
	}
}