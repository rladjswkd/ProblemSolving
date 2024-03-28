import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int h, w;
	private static char[][] board;

	private static int bfs(Coin c1, Coin c2) {
		int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
		Deque<Coin> q = new ArrayDeque<>();
		int cnt = 0, size;
		Coin n1, n2;
		boolean[][][][] vis = new boolean[h][w][h][w];

		q.addLast(c1);
		q.addLast(c2);
		while (cnt < 10) {
			size = q.size();
			for (int s = 0; s < size; s += 2) {
				c1 = q.removeFirst();
				c2 = q.removeFirst();

				for (int i = 0; i < 4; i++) {
					n1 = new Coin(c1.x + dx[i], c1.y + dy[i]);
					n2 = new Coin(c2.x + dx[i], c2.y + dy[i]);

					if (!n1.isInPosition(h, w) && !n2.isInPosition(h, w))
						continue;
					if ((n1.isInPosition(h, w) && !n2.isInPosition(h, w)) || (!n1.isInPosition(h, w) && n2.isInPosition(h, w)))
						return cnt + 1;

					if (board[n1.x][n1.y] == '#' && board[n2.x][n2.y] == '#')
						continue;
					else if (board[n1.x][n1.y] == '#')
						n1.move(-dx[i], -dy[i]);
					else if (board[n2.x][n2.y] == '#')
						n2.move(-dx[i], -dy[i]);
					if (!vis[n1.x][n1.y][n2.x][n2.y] && !n1.isOverlapped(n2)) {
						vis[n1.x][n1.y][n2.x][n2.y] = true;
						q.addLast(n1);
						q.addLast(n2);
					}
				}
			}
			cnt++;
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Coin c1 = null, c2 = null;
		String[] line;

		line = br.readLine().split(" ");
		h = Integer.parseInt(line[0]);
		w = Integer.parseInt(line[1]);
		board = new char[h][];
		for (int i = 0; i < h; i++) {
			board[i] = br.readLine().toCharArray();
			for (int j = 0; j < w; j++) {
				if (board[i][j] == 'o') {
					if (c1 == null)
						c1 = new Coin(i, j);
					else
						c2 = new Coin(i, j);
				}
			}
		}
		bw.write(bfs(c1, c2) + "\n");
		br.close();
		bw.flush();
		bw.close();
	}

	static class Coin {
		int x, y;

		Coin(int x, int y) {
			this.x = x;
			this.y = y;
		}

		boolean isInPosition(int h, int w) {
			return (0 <= x && x < h) && (0 <= y && y < w);
		}

		boolean isOverlapped(Coin other) {
			return x == other.x && y == other.y;
		}

		void move(int dx, int dy) {
			x += dx;
			y += dy;
		}
	}
}