import java.io.IOException;

public class Main {
	private static int h, w;
	private static int[][] mars, dp;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return sign == 45 ? ~n + 1 : n;
	}

	public static void main(String[] args) throws IOException {
		int[] above, left, right;

		mars = new int[h = readInt()][w = readInt()];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				mars[i][j] = readInt();
		dp = new int[h][w];
		dp[0][0] = mars[0][0];
		for (int j = 1; j < w; j++)
			dp[0][j] = dp[0][j - 1] + mars[0][j];
		above = new int[w];
		left = new int[w];
		right = new int[w];
		for (int i = 1; i < h; i++) {
			// 바로 위칸에서 내려오는 방법으로 values를 먼저 채운다.
			for (int j = 0; j < w; j++)
				above[j] = dp[i - 1][j] + mars[i][j];
			// 왼칸에서 이동하는 방법으로 가치가 더 클 때 그 가치를, 그렇지 않을 땐 above의 값을 선택
			// 첫번째 열은 왼칸에서 이동할 수 없으므로 위칸에서 내려오는 방법으로 초기화
			left[0] = above[0];
			for (int j = 1; j < w; j++)
				left[j] = Math.max(above[j], left[j - 1] + mars[i][j]);
			// 오른칸에서 이동하는 방법으로 가치가 더 클 때 그 가치를, 그렇지 않을 땐 above의 값을 선택
			// 마지막 열은 오른칸에서 이동할 수 없으므로 위칸에서 내려오는 방법으로 초기화
			right[w - 1] = above[w - 1];
			for (int j = w - 2; j >= 0; j--)
				right[j] = Math.max(above[j], right[j + 1] + mars[i][j]);
			for (int j = 0; j < w; j++)
				dp[i][j] = Math.max(left[j], right[j]);
		}
		System.out.println(dp[h - 1][w - 1]);
	}
}