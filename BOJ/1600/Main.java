import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int k, w, h, res = 0;
	private static int[][] board;
	private static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 },
			dx_horse = { -1, -2, -2, -1, 1, 2, 2, 1 }, dy_horse = { -2, -1, 1, 2, 2, 1, -1, -2 };
	private static Deque<int[]> q;
	private static boolean[][][] visited;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		int[] cur;
		int nx, ny, size, count = 0;
		boolean reached = false;

		q = new ArrayDeque<>();
		// 말의 방식으로 이동하는 횟수는 0부터 k까지가 가능하다.
		visited = new boolean[h][w][k + 1];
		q.addLast(new int[] { 0, 0, 0 });
		visited[0][0][0] = true;
		while (!q.isEmpty() && !reached) {
			size = q.size();
			count++;
			while (size-- > 0 && !reached) {
				cur = q.removeFirst();
				// 원숭이 방식으로 이동 -> 이동할 칸에서의 말의 방식으로 이동한 횟수는 현재 칸에 도달하기 위해 말의 방식으로 이동한 횟수와 같다.
				for (int i = 0; i < 4 && !reached; i++) {
					nx = cur[0] + dx[i];
					ny = cur[1] + dy[i];
					if (0 <= nx && nx < h && 0 <= ny && ny < w && !visited[nx][ny][cur[2]] && board[nx][ny] == 0) {
						q.addLast(new int[] { nx, ny, cur[2] });
						visited[nx][ny][cur[2]] = true;
						if (nx == h - 1 && ny == w - 1)
							reached = true;
					}
				}
				// 말의 방식으로 이동할 수 없다면 continue
				if (cur[2] == k)
					continue;
				// 말의 방식으로 이동
				for (int i = 0; i < 8 && !reached; i++) {
					nx = cur[0] + dx_horse[i];
					ny = cur[1] + dy_horse[i];
					if (0 <= nx && nx < h && 0 <= ny && ny < w && !visited[nx][ny][cur[2] + 1] && board[nx][ny] == 0) {
						q.addLast(new int[] { nx, ny, cur[2] + 1 });
						visited[nx][ny][cur[2] + 1] = true;
						if (nx == h - 1 && ny == w - 1)
							reached = true;
					}
				}
			}
		}
		res = reached ? count : -1;
	}

	public static void main(String[] args) throws IOException {
		k = readInt();
		w = readInt();
		h = readInt();
		board = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (System.in.read() == '1')
					board[i][j] = 1;
				System.in.read();
			}
		}
		// 행, 열의 크기가 1일 땐 답이 그냥 0.
		if (h != 1 || w != 1)
			solve();
		System.out.println(res);
	}
}