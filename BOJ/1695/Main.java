import java.io.IOException;

public class Main {
	private static int length;
	private static int[] seq;
	private static int[][] dp;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return sign == 45 ? ~n + 1 : n;
	}

	private static int solve(int start, int end) {
		// seq[start]부터 seq[end]까지의 부분 수열에 대해 이미 연산을 했다면 추가해야 하는 숫자의 개수를 반환
		if (dp[start][end] != -1)
			return dp[start][end];
		// end == start(부분 수열이 하나의 숫자로 이루어진 경우)
		// end < start(직전에 부분 수열이 두 개의 숫자로 이루어졌고 그 두 숫자가 같아서 start - 1, end - 1이 인자로 전달돼
		// 현재 부분 수열에 남은 숫자가 없는 경우)
		if (end <= start)
			return dp[start][end] = 0;
		// seq[start]와 seq[end]가 같을 때: 추가하지 않아도 된다.
		if (seq[start] == seq[end])
			dp[start][end] = solve(start + 1, end - 1);
		// seq[start]와 seq[end]가 다를 때: 둘 중 하나를 선택
		else
			// seq[start]를 선택하는 방법과 seq[end]를 선택하는 방법 중 추가할 숫자가 더 적은 방법을 선택하고 1을 더한다
			dp[start][end] = 1 + Math.min(solve(start + 1, end), solve(start, end - 1));
		return dp[start][end];
	}

	public static void main(String[] args) throws IOException {
		seq = new int[length = readInt()];
		dp = new int[length][length];
		for (int i = 0; i < length; i++) {
			seq[i] = readInt();
			// "아직 처리되지 않음 상태"로 dp를 초기화.
			for (int j = 0; j < length; j++)
				dp[i][j] = -1;
		}
		System.out.println(solve(0, length - 1));
	}
}