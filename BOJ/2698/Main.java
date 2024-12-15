import java.io.IOException;

public class Main {
	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int t = read(), n, k;
		int[][] dp0 = new int[101][101], dp1 = new int[101][101];
		StringBuilder sb = new StringBuilder();

		dp0[2][0] = 2;
		dp1[2][0] = dp1[2][1] = 1;
		for (int i = 3; i <= 100; i++) {
			dp0[i][0] = dp0[i - 1][0] + dp1[i - 1][0];
			dp1[i][0] = dp0[i - 1][0];
			for (int j = 1; j < i; j++) {
				dp0[i][j] = dp0[i - 1][j] + dp1[i - 1][j];
				dp1[i][j] = dp0[i - 1][j] + dp1[i - 1][j - 1];
			}
		}
		while (t-- > 0)
			sb.append(dp0[n = read()][k = read()] + dp1[n][k]).append('\n');
		System.out.print(sb.toString());
	}
}