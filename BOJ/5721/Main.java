import java.io.IOException;

public class Main {
	private static int height, width;
	private static int[][] boxes;
	private static long[][] dp;
	private static final int PADDING = 2;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		while ((height = read()) + (width = read()) != 0) {
			boxes = new int[height][width];
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
					boxes[i][j] = read();
			dp = new long[height + PADDING][width + PADDING];
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
					dp[i + PADDING][j + PADDING] = Math.max(dp[i + PADDING][j] + boxes[i][j], dp[i + PADDING][j + 1]);
			for (int i = PADDING; i < height + PADDING; i++)
				dp[i][width + 1] = Math.max(dp[i][width + 1] + dp[i - 2][width + 1], dp[i - 1][width + 1]);
			sb.append(dp[height + 1][width + 1]).append('\n');
		}
		System.out.print(sb.toString());
	}
}