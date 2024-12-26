import java.io.IOException;

public class Main {
	private static byte[] s1, s2;
	private static int l1, l2;
	private static int[][] dp;
	private static final int C = 0b1111111000000000000000000, I = 0b111111000000000000, J = 0b111111000000, L = 0b111111;
	private static StringBuilder sb;

	private static void stackChar(int i, int j) {
		if ((dp[i][j] & L) > 1)
			stackChar((dp[i][j] & I) >> 12, (dp[i][j] & J) >> 6);
		sb.append((char) ((dp[i][j] & C) >> 18));
	}

	public static void main(String[] args) throws IOException {
		int c;

		s1 = new byte[40];
		while (10 < (c = System.in.read()))
			s1[l1++] = (byte) c;
		s2 = new byte[40];
		while (10 < (c = System.in.read()))
			s2[l2++] = (byte) c;
		dp = new int[l1][l2];
		dp[0][0] = s1[0] == s2[0] ? (1 | s1[0] << 18) : 0;
		for (int j = 1; j < l2; j++)
			dp[0][j] = s1[0] == s2[j] ? (1 | s1[0] << 18) : dp[0][j - 1];
		for (int i = 1; i < l1; i++) {
			dp[i][0] = s1[i] == s2[0] ? (1 | s1[i] << 18) : dp[i - 1][0];
			for (int j = 1; j < l2; j++) {
				if (s1[i] == s2[j])
					dp[i][j] = (dp[i - 1][j - 1] & L) + 1 | s1[i] << 18 | i - 1 << 12 | j - 1 << 6;
				else if ((dp[i][j - 1] & L) < (dp[i - 1][j] & L))
					dp[i][j] = (dp[i - 1][j] & L) | dp[i - 1][j] & (C | I | J);
				else
					dp[i][j] = (dp[i][j - 1] & L) | dp[i][j - 1] & (C | I | J);
			}
		}
		sb = new StringBuilder();
		stackChar(l1 - 1, l2 - 1);
		System.out.println(sb.toString());
	}
}