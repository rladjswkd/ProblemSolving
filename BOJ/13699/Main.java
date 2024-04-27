import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BigInteger[] dp = new BigInteger[Integer.parseInt(br.readLine()) + 1];

		dp[0] = BigInteger.ONE;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = BigInteger.valueOf(0);
			for (int j = 0; j < i; j++)
				dp[i] = dp[i].add(dp[j].multiply(dp[i - j - 1]));
		}
		bw.append(String.valueOf(dp[dp.length - 1])).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}