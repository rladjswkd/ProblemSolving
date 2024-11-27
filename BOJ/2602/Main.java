import java.io.IOException;

public class Main {
	private static int scrollLength, bridgeLength;
	private static int[] scroll, angel, devil;
	private static long[][] dpAngel, dpDevil;

	private static long solve(boolean angelFlag, int bridgeIdx, int scrollIdx) {
		int[] curr;
		long[][] dp;
		int idx = bridgeIdx, count = 0;

		if (scrollIdx == scrollLength)
			return 1;
		if (angelFlag) {
			curr = angel;
			dp = dpAngel;
		} else {
			curr = devil;
			dp = dpDevil;
		}
		if (dp[bridgeIdx][scrollIdx] >= 0)
			return dp[bridgeIdx][scrollIdx];
		while (scrollLength - scrollIdx <= bridgeLength - idx) {
			if (scroll[scrollIdx] == curr[idx])
				count += solve(!angelFlag, idx + 1, scrollIdx + 1);
			idx++;
		}
		return dp[bridgeIdx][scrollIdx] = count;
	}

	public static void main(String[] args) throws IOException {
		int c;

		scroll = new int[20];
		while ((c = System.in.read()) != 10)
			scroll[scrollLength++] = c;
		angel = new int[100];
		while ((c = System.in.read()) != 10)
			angel[bridgeLength++] = c;
		devil = new int[bridgeLength];
		for (int i = 0; i < bridgeLength; i++)
			devil[i] = System.in.read();
		dpAngel = new long[bridgeLength][scrollLength];
		dpDevil = new long[bridgeLength][scrollLength];
		for (int i = 0; i < bridgeLength; i++)
			for (int j = 0; j < scrollLength; j++)
				dpAngel[i][j] = dpDevil[i][j] = -1;
		System.out.println(solve(true, 0, 0) + solve(false, 0, 0));
	}
}