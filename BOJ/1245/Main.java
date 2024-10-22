import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int height, width, res;
	private static int[][] heights;
	private static boolean[][] visited;
	// 상, 하, 좌, 우, 좌상, 좌하, 우상, 우하
	private static final int[] dx = { -1, 1, 0, 0, -1, 1, -1, 1 }, dy = { 0, 0, -1, 1, -1, -1, 1, 1 };

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void check(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur;
		int nx, ny;
		boolean flag = true;

		q.addLast(new int[] { x, y });
		visited[x][y] = true;
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			for (int i = 0; i < 8; i++) {
				nx = cur[0] + dx[i];
				ny = cur[1] + dy[i];
				if (nx < 0 || height <= nx || ny < 0 || width <= ny)
					continue;
				if (!visited[nx][ny] && heights[cur[0]][cur[1]] == heights[nx][ny]) {
					visited[nx][ny] = true;
					q.addLast(new int[] { nx, ny });
				}
				if (heights[cur[0]][cur[1]] < heights[nx][ny])
					flag = false;
			}
		}
		if (flag)
			res++;
	}

	public static void main(String[] args) throws IOException {
		heights = new int[height = read()][width = read()];
		visited = new boolean[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				heights[i][j] = read();
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if (!visited[i][j])
					check(i, j);
		System.out.println(res);
	}
}