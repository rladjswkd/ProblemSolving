
/*
 * 두 번째 아이디어를 기반으로 코드를 약간 수정
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int solve(char[] crypto) {
		int[][] dp = new int[crypto.length][2];

		if (crypto[0] == '0')
			return 0;
		dp[0][0] = 1;
		for (int i = 1; i < crypto.length; i++) {
			if (crypto[i] == '0' && crypto[i - 1] != '1' && crypto[i - 1] != '2')
				return 0;
			else if (crypto[i] == '0')
				dp[i][1] = dp[i - 1][0];
			else {
				dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % 1000000;
				if (crypto[i - 1] == '1' || (crypto[i - 1] == '2' && crypto[i] <= '6'))
					dp[i][1] = dp[i - 1][0];
			}
		}
		return (dp[crypto.length - 1][0] + dp[crypto.length - 1][1]) % 1000000;
	}

	public static void main(String[] args) throws IOException {
		bw.append(String.valueOf(solve(br.readLine().toCharArray()))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}