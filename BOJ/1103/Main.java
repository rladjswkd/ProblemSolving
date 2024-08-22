import java.io.IOException;

public class Main {
	private static int h, w;
	private static int[][] board, dist;
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	// 사이클을 발견했을 때 false, 그렇지 않을 때 true 반환
	private static boolean solve(int x, int y) {
		int nx, ny, s = board[x][y] & 15, currentDist;
		boolean noCycle = true;

		dist[x][y] = currentDist = 1;
		for (int i = 0; i < 4 && noCycle; i++) {
			nx = x + dx[i] * s;
			ny = y + dy[i] * s;
			// 다음 턴에 동전이 보드를 벗어나거나 구멍에 빠질 때
			if (nx < 0 || h <= nx || ny < 0 || w <= ny || board[nx][ny] == 'H') {
				currentDist = Math.max(currentDist, 2);
				continue;
			}
			// 방문했지만 아직 최대 이동 횟수를 계산하지 못한 칸을 다시 방문할 때
			if (dist[nx][ny] == 1)
				return false;
			// 최대 이동 횟수를 계산한 칸을 다시 방문할 때
			if (dist[nx][ny] > 1) {
				currentDist = Math.max(currentDist, dist[nx][ny] + 1);
				continue;
			}
			// 다음 턴에 끝나지도 않고, 방문할 칸도 아직 방문하지 않은 칸일 때
			noCycle = noCycle && solve(nx, ny);
			currentDist = Math.max(currentDist, dist[nx][ny] + 1);
		}
		dist[x][y] = currentDist;
		return noCycle;
	}

	public static void main(String[] args) throws IOException {
		board = new int[h = readInt()][w = readInt()];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++)
				board[i][j] = System.in.read();
			System.in.read();
		}
		dist = new int[h][w];
		if (solve(0, 0))
			System.out.println(dist[0][0] - 1);
		else
			System.out.println(-1);
	}
}