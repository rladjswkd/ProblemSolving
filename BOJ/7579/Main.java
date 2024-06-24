
/*
 * 행은 앱, 열은 메모리가 아닌 행은 앱, 열은 비용으로!
 */
import java.io.IOException;

public class Main {
	private static int itemCount, targetMemory;
	private static int[] memories, costs;
	private static int[][] dp;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int totalCost = 0, res = Integer.MAX_VALUE;

		itemCount = readInt();
		targetMemory = readInt();
		memories = new int[itemCount];
		costs = new int[itemCount];
		for (int i = 0; i < itemCount; i++)
			memories[i] = readInt();
		for (int i = 0; i < itemCount; i++)
			totalCost += (costs[i] = readInt());
		dp = new int[itemCount + 1][totalCost + 1];
		for (int i = 1; i <= itemCount; i++) {
			// 목표 메모리를 확보하기 위한 비용이 0인 경우도 고려해줘야 한다.
			for (int j = 0; j <= totalCost; j++) {
				if (costs[i - 1] > j)
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = Math.max(dp[i - 1][j], memories[i - 1] + dp[i - 1][j - costs[i - 1]]);
				if (dp[i][j] >= targetMemory && j < res)
					res = j;
			}
		}
		System.out.println(res);
	}
}