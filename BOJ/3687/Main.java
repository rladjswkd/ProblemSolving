import java.io.IOException;

public class Main {
	private static int[] values;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), n;
		long[] dp = new long[101];
		long res, val;
		StringBuilder sb = new StringBuilder();

		// i개의 성냥으로 만들 수 있는 가장 작은 수
		values = new int[] { 0, 0, 1, 7, 4, 2, 0, 8 };
		// dp[0]과 dp[1]이 선택되지 않게 하기 위한 적당히 큰 수
		dp[0] = dp[1] = 100;
		dp[2] = 1;
		dp[3] = 7;
		dp[4] = 4;
		dp[5] = 2;
		dp[6] = 6;
		dp[7] = 8;
		for (int count = 8; count < 101; count++) {
			res = Long.MAX_VALUE;
			for (int x = 2; x <= 7; x++) {
				val = dp[count - x];
				res = Math.min(res, (val << 3) + (val << 1) + values[x]);
			}
			dp[count] = res;
		}
		while (t-- > 0) {
			n = readInt();
			// 가장 작은 수
			sb.append(dp[n]).append(' ');
			// 가장 큰 수
			if ((n & 1) == 1) {
				sb.append(7);
				n -= 3;
			}
			for (int i = 0; i < n; i += 2)
				sb.append(1);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}