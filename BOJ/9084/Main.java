import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		String[] line;
		StringBuilder sb = new StringBuilder();
		int[] coins, dp;
		while (t-- > 0) {
			coins = new int[Integer.parseInt(br.readLine())];
			line = br.readLine().split(" ");
			for (int i = 0; i < coins.length; i++)
				coins[i] = Integer.parseInt(line[i]);
			dp = new int[Integer.parseInt(br.readLine()) + 1];
			dp[0] = 1;
			for (int coin : coins)
				for (int i = coin; i < dp.length; i++)
					dp[i] += dp[i - coin];
			sb.append(dp[dp.length - 1]).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}