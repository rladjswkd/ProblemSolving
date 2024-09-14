import java.io.IOException;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static int[] acc;
	private static int[][] dp, opt;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		// rest는 chapter[0] + ... + chatper[i - 1]을 담는데 사용한다.
		// acc[i]에서 rest를 빼주면 chapter[i] + ... + chapter[j]의 부분합을 구할 수 있다.
		int t = readInt(), chapterCount, cost, rest, value, kLast;

		// acc[i]는 첫 번째 장부터 i 번째 장까지를 더한 파일의 크기다.
		acc = new int[500];
		dp = new int[500][500];
		opt = new int[500][500];
		while (t-- > 0) {
			chapterCount = readInt();

			acc[0] = readInt();
			for (int i = 1; i < chapterCount; i++)
				acc[i] = acc[i - 1] + readInt();

			for (int i = 0; i < chapterCount; i++)
				opt[i][i] = i;

			for (int i = chapterCount - 2; i >= 0; i--) {
				rest = i == 0 ? 0 : acc[i - 1];
				for (int j = i + 1; j < chapterCount; j++) {
					value = Integer.MAX_VALUE;
					cost = acc[j] - rest;
					kLast = Math.min(j - 1, opt[i + 1][j]);
					// k는 (23)(45)의 3처럼 왼쪽에서 먼저 더해칠 챕터들 중 마지막 챕터를 나타낸다.
					for (int k = opt[i][j - 1]; k <= kLast; k++) {
						if (value >= dp[i][k] + dp[k + 1][j] + cost) {
							opt[i][j] = k;
							value = dp[i][k] + dp[k + 1][j] + cost;
						}
					}
					dp[i][j] = value;
				}
			}
			sb.append(dp[0][chapterCount - 1]).append('\n');
		}
		System.out.print(sb.toString());
	}
}