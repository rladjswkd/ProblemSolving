/*
 * 0: 1(실제로는 0이지만 dp 연산 과정에서 4의 2+2나 6의 3+3을 위해 1로 설정한다.)
 * 1: 1
 * 2: 1+1, 2
 * 3: 1+1+1, 3
 * 4: 1+1+1+1, 1+2+1, 2+2
 * 5: 1+1+1+1+1, 1+3+1, 2+1+2
 * 6: 1+1+1+1+1+1, 1+1+2+1+1, 1+2+2+1, 2+1+1+2, 2+2+2, 3+3
 * 7: 1+5의경우+1, 2+3의경우+2, 3+1의경우+3
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
		long[] dp = new long[100001];
		StringBuilder sb = new StringBuilder();

		dp[0] = dp[1] = 1;
		dp[2] = dp[3] = 2;
		dp[4] = dp[5] = 3;
		for (int i = 6; i < dp.length; i++)
			dp[i] = (dp[i - 2] + dp[i - 4] + dp[i - 6]) % 1_000_000_009;
		while (t-- > 0)
			sb.append(dp[readInt()]).append('\n');
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}