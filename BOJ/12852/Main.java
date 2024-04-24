import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine()), cur = n;
		int[] dp = new int[n + 2];
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < n; i++) {
			if ((i << 1) + i < dp.length && (dp[(i << 1) + i] == 0 || dp[(i << 1) + i] > dp[i] + 1))
				dp[(i << 1) + i] = dp[i] + 1;
			if ((i << 1) < dp.length && (dp[(i << 1)] == 0 || dp[(i << 1)] > dp[i] + 1))
				dp[(i << 1)] = dp[i] + 1;
			if (dp[i + 1] == 0 || dp[i + 1] > dp[i] + 1)
				dp[i + 1] = dp[i] + 1;
		}
		sb.append(dp[n]).append('\n').append(n);
		while (cur > 1) {
			if (cur % 3 == 0 && dp[cur / 3] + 1 == dp[cur]) {
				cur /= 3;
				sb.append(' ').append(cur);
			} else if (cur % 2 == 0 && dp[cur / 2] + 1 == dp[cur]) {
				cur /= 2;
				sb.append(' ').append(cur);
			} else {
				cur--;
				sb.append(' ').append(cur);
			}
		}
		bw.write(sb.append('\n').toString());
		bw.flush();
		br.close();
		bw.close();
	}
}