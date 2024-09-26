import java.io.IOException;

public class Main {
	private static int h, w;
	private static int[][] board;
	private static final int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 };
	private static boolean[][] visited;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static boolean findCycle(int x, int y, int dir) {
		int nx, ny, nd, except;

		visited[x][y] = true;
		except = (dir + 2) % 4;
		for (int i = 0; i < 4; i++) {
			nd = (dir + 1 + i) % 4;
			if (nd == except)
				continue;
			nx = x + dx[nd];
			ny = y + dy[nd];
			if (nx < 0 || h <= nx || ny < 0 || w <= ny || board[x][y] != board[nx][ny])
				continue;
			if (visited[nx][ny] || findCycle(nx, ny, nd))
				return true;
		}
		return false;
	}

	private static boolean solve() {
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				if (!visited[i][j] && findCycle(i, j, 0))
					return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		board = new int[h = read()][w = read()];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++)
				board[i][j] = System.in.read();
			System.in.read();
		}
		visited = new boolean[h][w];
		if (solve())
			System.out.println("Yes");
		else
			System.out.println("No");
	}
}