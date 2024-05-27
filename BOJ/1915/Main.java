import java.io.*;
import java.util.*;

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
		int n = readInt(), m = readInt(), length = 0;
		char[][] board = new char[n][];
		int[][] dp = new int[n + 1][m + 1];

		for (int i = 0; i < n; i++)
			board[i] = br.readLine().toCharArray();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (board[i - 1][j - 1] == '0')
					continue;
				dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
				if (length < dp[i][j])
					length = dp[i][j];
			}
		}
		bw.append(String.valueOf(length * length)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}