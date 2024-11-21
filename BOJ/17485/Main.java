import java.io.IOException;

public class Main {
	private static int h, w;
	private static int[][] space;
	private static final int INFINITY = 100 * 1000 + 1;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && (c <= 57))
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[][] dp, prev, swap;
		int res = INFINITY;

		space = new int[h = read()][w = read()];
		dp = new int[w][3];
		prev = new int[w][3];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				space[i][j] = read();
		for (int i = 0; i < w; i++)
			for (int k = 0; k < 3; k++)
				dp[i][k] = space[0][i];
		for (int i = 1; i < h; i++) {
			swap = dp;
			dp = prev;
			prev = swap;
			dp[0][0] = space[i][0] + Math.min(prev[1][1], prev[1][2]);
			dp[0][1] = space[i][0] + prev[0][0];
			dp[0][2] = INFINITY;
			for (int j = 1; j < w - 1; j++) {
				dp[j][0] = space[i][j] + Math.min(prev[j + 1][1], prev[j + 1][2]);
				dp[j][1] = space[i][j] + Math.min(prev[j][0], prev[j][2]);
				dp[j][2] = space[i][j] + Math.min(prev[j - 1][0], prev[j - 1][1]);
			}
			dp[w - 1][0] = INFINITY;
			dp[w - 1][1] = space[i][w - 1] + prev[w - 1][2];
			dp[w - 1][2] = space[i][w - 1] + Math.min(prev[w - 2][0], prev[w - 2][1]);
		}
		for (int i = 0; i < w; i++)
			for (int k = 0; k < 3; k++)
				res = Math.min(res, dp[i][k]);
		System.out.println(res);
	}
}