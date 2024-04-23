import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine()), half;
		int[] dp = new int[11];

		dp[2] = 1;
		for (int i = 3; i <= n; i++) {
			half = i / 2;
			dp[i] = half * (i - half) + dp[half] + dp[i - half];
		}
		bw.append(String.valueOf(dp[n])).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}
