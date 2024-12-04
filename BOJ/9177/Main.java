import java.io.IOException;

public class Main {
	private static int n, l1, l2, l3;
	private static char[] w1, w2, w3;
	private static StringBuilder sb;
	private static final String DATA_SET = "Data set ", CENTER = ": ", YES = "yes\n", NO = "no\n";
	private static int[][] dp;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int solve(int i1, int i2, int i3) {
		if (i3 == l3)
			return 1;
		if (i1 == l1) {
			while (i2 < l2 && w2[i2] == w3[i3]) {
				i2++;
				i3++;
			}
			return i3 == l3 ? 1 : -1;
		}
		if (i2 == l2) {
			while (i1 < l1 && w1[i1] == w3[i3]) {
				i1++;
				i3++;
			}
			return i3 == l3 ? 1 : -1;
		}
		if (dp[i1][i2] != 0)
			return dp[i1][i2];
		if (i1 < l1 && w1[i1] == w3[i3])
			if (solve(i1 + 1, i2, i3 + 1) == 1)
				return dp[i1][i2] = 1;
		if (i2 < l2 && w2[i2] == w3[i3])
			if (solve(i1, i2 + 1, i3 + 1) == 1)
				return dp[i1][i2] = 1;
		return dp[i1][i2] = -1;
	}

	public static void main(String[] args) throws IOException {
		int c;

		n = read();
		w1 = new char[200];
		w2 = new char[200];
		w3 = new char[400];
		sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			l1 = l2 = l3 = 0;
			while ((c = System.in.read()) > 32)
				w1[l1++] = (char) c;
			while ((c = System.in.read()) > 32)
				w2[l2++] = (char) c;
			while ((c = System.in.read()) > 32)
				w3[l3++] = (char) c;
			dp = new int[l1][l2];
			sb.append(DATA_SET).append(i).append(CENTER).append(solve(0, 0, 0) == 1 ? YES : NO);
		}
		System.out.print(sb.toString());
	}
}