import java.io.IOException;

public class Main {
	private static int[] dp;
	private static int res;
	private static final int SIZE = 708;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int count, int n) {
		if (count == res - 1 && n > 0)
			return;
		if (n == 0) {
			res = count;
			return;
		}
		for (int i = 0; dp[i] <= n; i++)
			solve(count + 1, n - dp[i]);
	}

	public static void main(String[] args) throws IOException {
		int n = read();

		dp = new int[SIZE];
		dp[0] = 1;
		dp[1] = 6;
		for (int i = 2; i < SIZE; i++)
			dp[i] = dp[i - 1] + 5 + 4 * (i - 1);
		if (n == 11 || n == 26)
			res = 6;
		else {
			res = n <= 1791 ? 5 : 4;
			solve(0, n);
		}
		System.out.println(res);
	}
}