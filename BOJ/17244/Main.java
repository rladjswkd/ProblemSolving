import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int h, w, xCount, sx, sy, ex, ey, res, mask;
	private static int[][] house;
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur;
		boolean[][][] visited = new boolean[h][w][mask + 1];
		int size, nx, ny, nb;

		// 행인덱스, 열인덱스, 방문한 X 칸에 대한 비트마스킹
		q.addLast(new int[] { sx, sy, 0 });
		visited[sx][sy][0] = true;
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				for (int i = 0; i < 4; i++) {
					nx = cur[0] + dx[i];
					ny = cur[1] + dy[i];
					nb = cur[2];
					if (nx < 0 || h <= nx || ny < 0 || w <= ny || visited[nx][ny][nb] || house[nx][ny] == '#')
						continue;
					if (0 <= house[nx][ny] && house[nx][ny] < xCount)
						nb |= 1 << house[nx][ny];
					if (nx == ex && ny == ey && nb == mask) {
						res++;
						return;
					}
					visited[nx][ny][nb] = true;
					q.addLast(new int[] { nx, ny, nb });
				}
			}
			res++;
		}
	}

	public static void main(String[] args) throws IOException {
		int ch;

		w = readInt();
		house = new int[h = readInt()][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if ((ch = System.in.read()) == 'X') {
					ch = xCount;
					mask |= 1 << xCount;
					xCount++;
				} else if (ch == 'S') {
					sx = i;
					sy = j;
				} else if (ch == 'E') {
					ex = i;
					ey = j;
				}
				house[i][j] = ch;
			}
			System.in.read();
		}
		solve();
		System.out.println(res);
	}
}