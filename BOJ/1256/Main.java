import java.io.IOException;

public class Main {
	private static int aCount, zCount, targetPosition;
	private static final long MAX = 1_000_000_000;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		long[][] dp = new long[(aCount = readInt()) + 1][(zCount = readInt()) + 1];
		long val;
		StringBuilder sb = new StringBuilder();

		targetPosition = readInt();
		for (int i = 0; i <= aCount; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= zCount; j++)
				dp[i][j] = (val = dp[i][j - 1] * (i + j) / j) > MAX ? MAX : val;
		}
		if (dp[aCount][zCount] < targetPosition)
			System.out.println(-1);
		else {
			while (aCount > 0 && zCount > 0) {
				if (dp[aCount - 1][zCount] >= targetPosition) {
					aCount--;
					sb.append('a');
				} else {
					targetPosition -= dp[aCount - 1][zCount--];
					sb.append('z');
				}
			}
			while (aCount-- > 0)
				sb.append('a');
			while (zCount-- > 0)
				sb.append('z');
			System.out.println(sb.toString());
		}
	}
}