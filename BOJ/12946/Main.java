import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n, res;
	private static boolean[] checker;
	private static int[] board, dx = { -1, 0, 1, -1, 0, 1 }, dy = { 0, 1, -1, 1, -1, 0 };

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur;
		int nx, ny, idx;

		q.addLast(new int[] { x, y, board[x * n + y] = 1 });
		res = Math.max(res, 1);
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			for (int i = 0; i < 6; i++) {
				nx = cur[0] + dx[i];
				ny = cur[1] + dy[i];
				if (nx < 0 || n <= nx || ny < 0 || n <= ny || !checker[idx = nx * n + ny])
					continue;
				if (board[idx] == 0)
					q.addLast(new int[] { nx, ny, board[idx] = cur[2] % 2 + 1 });
				else if (board[idx] == cur[2]) {
					res = 3;
					return;
				} else if (board[idx] == cur[2] % 2 + 1)
					res = 2;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		board = new int[(n = read()) * n];
		checker = new boolean[n * n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (System.in.read() == 'X')
					checker[i * n + j] = true;
			System.in.read();
		}
		for (int i = 0; i < n && res < 3; i++)
			for (int j = 0; j < n && res < 3; j++)
				if (checker[i * n + j] && board[i * n + j] == 0)
					solve(i, j);
		System.out.println(res);
	}
}