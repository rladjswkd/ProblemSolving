import java.io.*;

/*
 * 1: 1
 * 2: 00, 11
 * 3: 001, 100, 111
 * 4: 0000, 1100, 0011, 1001, 1111
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];

		if (n < 3)
			bw.append(String.valueOf(n)).append('\n');
		else {
			dp[1] = 1;
			dp[2] = 2;
			for (int i = 3; i <= n; i++)
				dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
			bw.append(String.valueOf(dp[n])).append('\n');
		}
		bw.flush();
		br.close();
		bw.close();
	}
}