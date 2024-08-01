import java.io.IOException;

public class Main {
	private static int[] seq;
	private static int[][][] dp;
	private static int seqLength;

	private static int move(int current, int next) {
		int diff;

		if (current == 0)
			return 2;
		// current와 next가 같은 칸
		if ((diff = Math.abs(next - current)) == 0)
			return 1;
		// current와 next가 서로 마주보고 있는 반대편 칸
		if (diff == 2)
			return 4;
		// current와 next가 인접한 칸(diff == 1 || diff == 3)
		return 3;
	}

	private static int solve(int leftPos, int rightPos, int seqIdx) {
		int left, right;

		if (seqIdx == seqLength)
			return 0;
		if (dp[leftPos][rightPos][seqIdx] > 0)
			return dp[leftPos][rightPos][seqIdx];
		// 왼쪽 발을 옮길 때 드는 힘 + 왼쪽 발을 옮겼을 대 이후의 수열에 대해 드는 최소 힘
		left = move(leftPos, seq[seqIdx]) + solve(seq[seqIdx], rightPos, seqIdx + 1);
		// 오른쪽 발을 옮길 때 드는 힘 + 오른쪽 발을 옮겼을 때 이후의 수열에 대해 드는 최소 힘
		right = move(rightPos, seq[seqIdx]) + solve(leftPos, seq[seqIdx], seqIdx + 1);
		return (dp[leftPos][rightPos][seqIdx] = Math.min(left, right));
	}

	public static void main(String[] args) throws IOException {
		int n;

		seq = new int[100000];
		seqLength = 0;
		while ((n = System.in.read() & 15) != 0) {
			seq[seqLength++] = n;
			System.in.read();
		}
		dp = new int[5][5][seqLength];
		System.out.println(solve(0, 0, 0));
	}
}