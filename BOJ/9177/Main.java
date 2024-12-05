import java.io.IOException;

public class Main {

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n, l1, l2, l3, c;
		char[] w1, w2, w3;
		boolean[][] dp;
		StringBuilder sb;
		final String DATA_SET = "Data set ", CENTER = ": ", YES = "yes\n", NO = "no\n";

		n = read();
		w1 = new char[200];
		w2 = new char[200];
		w3 = new char[400];
		dp = new boolean[201][201];
		sb = new StringBuilder();
		for (int round = 1; round <= n; round++) {
			l1 = l2 = l3 = 0;
			while ((c = System.in.read()) > 32)
				w1[l1++] = (char) c;
			while ((c = System.in.read()) > 32)
				w2[l2++] = (char) c;
			while ((c = System.in.read()) > 32)
				w3[l3++] = (char) c;
			dp[0][0] = true;
			for (int j = 1; j <= l2; j++)
				dp[0][j] = dp[0][j - 1] && w2[j - 1] == w3[j - 1];
			for (int i = 1; i <= l1; i++) {
				dp[i][0] = dp[i - 1][0] && w1[i - 1] == w3[i - 1];
				for (int j = 1; j <= l2; j++)
					dp[i][j] = (dp[i - 1][j] && w1[i - 1] == w3[i + j - 1]) || (dp[i][j - 1] && w2[j - 1] == w3[i + j - 1]);
			}
			sb.append(DATA_SET).append(round).append(CENTER).append(dp[l1][l2] ? YES : NO);
		}
		System.out.print(sb.toString());
	}
}