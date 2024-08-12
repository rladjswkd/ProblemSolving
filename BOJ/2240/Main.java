import java.io.IOException;

public class Main {
	private static int t, w;
	private static int[] tree;
	private static int[][][] dp;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int newPlum, res = 0;

		t = readInt();
		w = readInt();
		tree = new int[t + 1];
		for (int i = 1; i <= t; i++) {
			tree[i] = System.in.read() & 15;
			System.in.read();
		}
		dp = new int[2][t + 1][w + 1];
		for (int time = 1; time <= t; time++) {
			dp[0][time][0] = (tree[time] == 1 ? 1 : 0) + dp[0][time - 1][0];
			// 두 번쨰 나무(인덱스 1)에 이동 횟수 0으로 위치할 수 없으므로 0으로 둔다.
			// dp[1][0][time] = (tree[time] == 2 ? 1 : 0) + dp[1][0][time - 1];
			newPlum = tree[time] == 2 ? 1 : 0;
			for (int move = 1; move <= w; move += 2)
				dp[1][time][move] = newPlum + Math.max(dp[1][time - 1][move], dp[0][time - 1][move - 1]);
			newPlum = tree[time] == 1 ? 1 : 0;
			for (int move = 2; move <= w; move += 2)
				dp[0][time][move] = newPlum + Math.max(dp[0][time - 1][move], dp[1][time - 1][move - 1]);
		}
		for (int move = 0; move <= w; move++)
			res = Math.max(res, Math.max(dp[0][t][move], dp[1][t][move]));
		System.out.println(res);
	}
}