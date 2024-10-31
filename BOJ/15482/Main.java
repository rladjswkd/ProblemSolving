import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		char[] seq1 = br.readLine().toCharArray(), seq2 = br.readLine().toCharArray();
		int[][] dp = new int[seq1.length + 1][seq2.length + 1];
		int val;

		for (int i = 1; i <= seq1.length; i++) {
			for (int j = 1; j <= seq2.length; j++) {
				val = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (seq1[i - 1] == seq2[j - 1])
					val = Math.max(val, dp[i - 1][j - 1] + 1);
				dp[i][j] = val;
			}
		}
		System.out.println(dp[seq1.length][seq2.length]);
	}
}