import java.io.IOException;

public class Main {
	private static int n, res = 0;
	private static int[][] forest, dp;
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int solve(int x, int y) {
		int nx, ny, acc, cur = 1;

		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			// forest[nx][ny]가 유효하지 않은 칸이거나, 유효한 칸이어도 forest[x][y]보다 대나무 수가 적거나 같을 땐 넘기기
			if (nx < 0 || n <= nx || ny < 0 || n <= ny || forest[nx][ny] <= forest[x][y])
				continue;
			acc = 1;
			// forest[nx][ny]가 방문한 칸일 때
			if (dp[nx][ny] > 0)
				acc += dp[nx][ny];
			// forest[nx][ny]가 아직 방문하지 않은 칸일 때
			else
				acc += solve(nx, ny);
			cur = Math.max(cur, acc);
		}
		dp[x][y] = cur;
		res = Math.max(res, cur);
		return cur;
	}

	public static void main(String[] args) throws IOException {
		forest = new int[n = readInt()][n];
		dp = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				forest[i][j] = readInt();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (dp[i][j] == 0)
					solve(i, j);
		System.out.println(res);
	}
}