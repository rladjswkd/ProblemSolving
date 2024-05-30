
/*
 * 1: 1
 * 2: 2, 1 + 1
 * 3: 3, 1 + 2, 2 + 1, 1 + 1 + 1
 * 4: 2 + 2, 3 + 1, 1 + 1 + 2, 1 + 2 + 1, 2 + 1 + 1, 1 + 1 + 1 + 1
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

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
		int t = readInt();
		// dp[x - 1][y - 1] : x를 y개의 1, 2, 또는 3으로 표현하는 방법의 수
		long[][] dp = new long[1000][1000];
		StringBuilder sb = new StringBuilder();

		dp[0][0] = dp[1][0] = dp[2][0] = 1;
		dp[1][1] = dp[2][2] = 1;
		dp[2][1] = 2;
		// dp를 int[][] 타입으로 선언한 후 ((dp[i - 1][j - 1] + dp[i - 2][j - 1]) % 1_000_000_009
		// + dp[i - 3][j - 1]) % 1_000_000_009
		// 를 연산하는 것보다 빠르다.
		for (int i = 3; i < 1000; i++)
			for (int j = 1; j < 1000; j++)
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 2][j - 1] + dp[i - 3][j - 1]) % 1_000_000_009;
		while (t-- > 0)
			sb.append(dp[readInt() - 1][readInt() - 1]).append('\n');
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}