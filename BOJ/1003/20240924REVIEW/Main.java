import java.io.IOException;

public class Main {
	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int t = read(), n;
		int[] dp = new int[42];
		StringBuilder sb = new StringBuilder();

		dp[0] = 1;
		dp[2] = 1;
		for (int i = 3; i < 42; i++)
			dp[i] = dp[i - 1] + dp[i - 2];
		while (t-- > 0)
			sb.append(dp[n = read()]).append(' ').append(dp[n + 1]).append('\n');
		System.out.print(sb.toString());
	}
}