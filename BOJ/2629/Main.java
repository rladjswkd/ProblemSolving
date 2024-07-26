import java.io.IOException;

public class Main {

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int weightCount = readInt(), marbleCount;
		int[] weights = new int[weightCount];
		boolean[][] dp = new boolean[weightCount + 1][40001];
		StringBuilder sb = new StringBuilder();

		weights = new int[weightCount];
		for (int i = 0; i < weightCount; i++)
			weights[i] = readInt();
		dp[0][0] = true;
		for (int i = 1; i <= weightCount; i++) {
			dp[i][0] = true;
			for (int j = 1; j <= 40000; j++) {
				dp[i][j] = dp[i - 1][j] || dp[i - 1][Math.abs(weights[i - 1] - j)];
				if (weights[i - 1] + j <= 40000)
					dp[i][j] = dp[i][j] || dp[i - 1][weights[i - 1] + j];
			}
		}
		marbleCount = readInt();
		sb.append(dp[weightCount][readInt()] ? 'Y' : 'N');
		for (int i = 1; i < marbleCount; i++)
			sb.append(' ').append(dp[weightCount][readInt()] ? 'Y' : 'N');
		System.out.println(sb.toString());
	}
}