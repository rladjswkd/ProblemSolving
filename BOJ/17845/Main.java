import java.io.IOException;

public class Main {
	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), k = readInt(), i, t;
		// 메모리 최적화
		int[] dp = new int[n + 1];

		i = readInt();
		t = readInt();
		for (int idx = n; idx >= t; idx--)
			dp[idx] = i;
		for (int order = 1; order < k; order++) {
			i = readInt();
			t = readInt();
			for (int idx = n; idx >= t; idx--)
				dp[idx] = Math.max(dp[idx], i + dp[idx - t]);
		}
		System.out.println(dp[n]);
	}
}