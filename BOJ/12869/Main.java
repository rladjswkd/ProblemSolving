import java.io.IOException;

public class Main {
	private static int count, hp1, hp2, hp3;
	private static int[][][] dp;
	private static int[] dhp1 = { 9, 9, 3, 3, 1, 1 }, dhp2 = { 3, 1, 9, 1, 9, 3 }, dhp3 = { 1, 3, 1, 9, 3, 9 };

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int solve(int p1, int p2, int p3) {
		int np1, np2, np3, v, res = Integer.MAX_VALUE;

		// base case
		if (p1 == 0 && p2 == 0 && p3 == 0)
			return 0;
		// top down
		if (dp[p1][p2][p3] > 0)
			return dp[p1][p2][p3];
		for (int i = 0; i < 6; i++) {
			np1 = (v = p1 - dhp1[i]) < 0 ? 0 : v;
			np2 = (v = p2 - dhp2[i]) < 0 ? 0 : v;
			np3 = (v = p3 - dhp3[i]) < 0 ? 0 : v;
			res = Math.min(res, solve(np1, np2, np3) + 1);
		}
		return dp[p1][p2][p3] = res;
	}

	public static void main(String[] args) throws IOException {
		count = read();
		hp1 = read();
		if (count > 1)
			hp2 = read();
		if (count > 2)
			hp3 = read();
		dp = new int[hp1 + 1][hp2 + 1][hp3 + 1];
		System.out.println(solve(hp1, hp2, hp3));
	}
}