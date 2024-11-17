import java.io.IOException;

public class Main {
	private static int[] input, dp;
	private static int length;

	private static int solve(int idx) {
		int res = 0;

		if (idx == length)
			return 1;
		if (dp[idx] > 0)
			return dp[idx];
		if (input[idx] == 0)
			return 0;
		if (idx >= length - 1 || input[idx + 1] != 0)
			res += solve(idx + 1);
		if (idx < length - 1 && input[idx] * 10 + input[idx + 1] <= 34)
			res += solve(idx + 2);
		return dp[idx] = res;
	}

	public static void main(String[] args) throws IOException {
		int c;

		input = new int[40];
		while (48 <= (c = System.in.read()) && c <= 57)
			input[length++] = c & 15;
		dp = new int[length];
		System.out.println(solve(0));
	}
}