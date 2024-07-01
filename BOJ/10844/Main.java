
/*
 * 		0		1		2		3		4		5		6		7		8		9
 * 1	0		1		1		1		1		1		1		1		1		1
 * 2	1		1		2		2		2		2		2		2		2		1
 * 3	1		3		3		4		4		4		4		4		4		2
 * 4	3		4		7		7		8		8		8		8		8		4
 * ...
 */
import java.io.IOException;

public class Main {
	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), res = 0;
		int[][] dp = new int[n][10];

		dp[0][0] = 0;
		for (int j = 1; j < 10; j++)
			dp[0][j] = 1;
		for (int i = 1; i < n; i++) {
			dp[i][0] = dp[i - 1][1];
			for (int j = 1; j < 9; j++)
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1_000_000_000;
			dp[i][9] = dp[i - 1][8];
		}
		for (int val : dp[n - 1])
			res = (res + val) % 1_000_000_000;
		System.out.println(res);
	}
}