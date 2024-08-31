import java.io.IOException;

public class Main {

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), cur, max = 0;
		int[] dp;

		dp = new int[n + 1];
		for (int i = 0; i < n; i++)
			if (max < (dp[cur = readInt()] = dp[cur - 1] + 1))
				max = dp[cur];
		System.out.println(n - max);
	}
}