import java.io.IOException;

public class Main {
	private static final int D = 100000;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int w = read(), h = read();
		int[][][] dp = new int[h][w][4];
		int[] res = dp[h - 1][w - 1];

		dp[0][0][0] = dp[0][0][2] = 1;
		for (int j = 1; j < w; j++)
			dp[0][j][0] = 1;
		for (int i = 1; i < h; i++) {
			dp[i][0][2] = 1;
			for (int j = 1; j < w; j++) {
				dp[i][j][0] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % D;
				dp[i][j][1] = dp[i][j - 1][2];
				dp[i][j][2] = (dp[i - 1][j][2] + dp[i - 1][j][3]) % D;
				dp[i][j][3] = dp[i - 1][j][0];
			}
		}
		System.out.println((res[0] + res[1] + res[2] + res[3]) % D);
	}
}