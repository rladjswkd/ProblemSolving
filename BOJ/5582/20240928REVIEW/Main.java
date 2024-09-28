import java.io.IOException;

public class Main {
	private static int[] seq1, seq2;
	private static int l1, l2;

	public static void main(String[] args) throws IOException {
		int c, res = 0;
		int[] dp;

		seq1 = new int[4000];
		seq2 = new int[4000];
		while ((c = System.in.read()) != 10)
			seq1[l1++] = c;
		while ((c = System.in.read()) != 10)
			seq2[l2++] = c;
		dp = new int[l2];
		c = seq1[0];
		for (int i = 0; i < l2; i++)
			if (c == seq2[i])
				dp[i] = res = 1;
		for (int i = 1; i < l1; i++) {
			c = seq1[i];
			for (int j = l2 - 1; j > 0; j--) {
				if (c == seq2[j])
					res = Math.max(res, dp[j] = dp[j - 1] + 1);
				else
					dp[j] = 0;
			}
			dp[0] = c == seq2[0] ? 1 : 0;
			res = Math.max(res, c);
		}
		System.out.println(res);
	}
}