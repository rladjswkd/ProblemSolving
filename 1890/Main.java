import java.io.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int[][] board = new int[n][n];
		long[][] dp = new long[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				board[i][j] = readInt();
		dp[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dp[i][j] == 0 || board[i][j] == 0)
					continue;
				if (j + board[i][j] < n)
					dp[i][j + board[i][j]] += dp[i][j];
				if (i + board[i][j] < n)
					dp[i + board[i][j]][j] += dp[i][j];
			}
		}
		bw.append(String.valueOf(dp[n - 1][n - 1])).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}