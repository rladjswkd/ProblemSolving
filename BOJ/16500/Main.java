import java.io.IOException;

public class Main {
	private static int[] s;
	private static int lengthS, sizeA;
	private static int[][] a;
	private static boolean[] dp;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static boolean compare(int start, int idx) {
		int l;

		if (lengthS < start + (l = a[idx][0]))
			return false;
		for (int i = 0; i < l; i++)
			if (s[start + i] != a[idx][1 + i])
				return false;
		return true;
	}

	private static void solve(int start) {
		if (start == lengthS)
			return;
		for (int i = 0; i < sizeA; i++) {
			if (compare(start, i)) {
				if (!dp[start + a[i][0]]) {
					dp[start + a[i][0]] = true;
					solve(start + a[i][0]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int c, l;

		s = new int[100];
		while ('a' <= (c = System.in.read()) && c <= 'z')
			s[lengthS++] = c;
		a = new int[sizeA = read()][101];
		for (int i = 0; i < sizeA; i++) {
			l = 1;
			while ('a' <= (c = System.in.read()) && c <= 'z')
				a[i][l++] = c;
			a[i][0] = l - 1;
		}
		dp = new boolean[lengthS + 1];
		solve(0);
		System.out.println(dp[lengthS] ? 1 : 0);
	}
}