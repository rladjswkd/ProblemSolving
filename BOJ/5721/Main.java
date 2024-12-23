import java.io.IOException;

public class Main {
	private static int height, width;
	private static int[][] dp;
	private static final int PADDING = 2;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int[] a;

		while ((height = read()) + (width = read()) != 0) {
			dp = new int[height + PADDING][width + PADDING];
			for (int i = 0; i < height; i++) {
				a = dp[i + PADDING];
				for (int j = 0; j < width - 1; j++)
					a[j + PADDING] = Math.max(a[j] + read(), a[j + 1]);
				a[width + 1] = Math.max(Math.max(a[width - 1] + read(), a[width]) + dp[i][width + 1],
						dp[i + 1][width + 1]);
			}
			sb.append(dp[height + 1][width + 1]).append('\n');
		}
		System.out.print(sb.toString());
	}
}