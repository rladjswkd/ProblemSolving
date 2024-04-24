
/*
 * 출력 결과가 long의 범위를 벗어남에 주의한다.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		BigInteger[] dp = new BigInteger[10001];

		dp[0] = BigInteger.valueOf(0);
		dp[1] = BigInteger.valueOf(1);
		dp[2] = BigInteger.valueOf(1);
		for (int i = 3; i <= n; i++)
			dp[i] = dp[i - 2].add(dp[i - 1]);
		bw.append(String.valueOf(dp[n])).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}