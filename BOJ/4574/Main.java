import java.io.IOException;

public class Main {
	private static int[] board, pieces, wChecker, hChecker, sChecker;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void set(int valueIdx, int x, int y) {
		board[x * 9 + y] = valueIdx + 1;
		hChecker[x] |= 1 << valueIdx;
		wChecker[y] |= 1 << valueIdx;
		sChecker[x / 3 * 3 + y / 3] |= 1 << valueIdx;
	}

	private static void unset(int valueIdx, int x, int y) {
		board[x * 9 + y] = 0;
		hChecker[x] ^= 1 << valueIdx;
		wChecker[y] ^= 1 << valueIdx;
		sChecker[x / 3 * 3 + y / 3] ^= 1 << valueIdx;
	}

	private static boolean solve(int sx, int sy) {
		int x = -1, y = -1;
		boolean found = false;

		for (int j = sy; j < 9; j++) {
			if (board[sx * 9 + j] == 0) {
				x = sx;
				y = j;
				found = true;
				break;
			}
		}
		for (int i = sx + 1; i < 9 && !found; i++) {
			for (int j = 0; j < 9 && !found; j++) {
				if (board[i * 9 + j] == 0) {
					x = i;
					y = j;
					found = true;
				}
			}
		}
		if (x == -1 && y == -1)
			return true;
		for (int u = 0; u < 9; u++) {
			for (int v = 0; v < 9; v++) {
				if (u == v || (pieces[u] & 1 << v) != 0)
					continue;
				if ((hChecker[x] & 1 << u) != 0 || (wChecker[y] & 1 << u) != 0 || (sChecker[x / 3 * 3 + y / 3] & 1 << u) != 0)
					continue;
				if (y < 8 && board[x * 9 + y + 1] == 0 && (hChecker[x] & 1 << v) == 0 && (wChecker[y + 1] & 1 << v) == 0
						&& (sChecker[x / 3 * 3 + (y + 1) / 3] & 1 << v) == 0) {
					set(u, x, y);
					set(v, x, y + 1);
					pieces[u] |= 1 << v;
					pieces[v] |= 1 << u;
					if (solve(x, y + 1))
						return true;
					unset(u, x, y);
					unset(v, x, y + 1);
					pieces[u] ^= 1 << v;
					pieces[v] ^= 1 << u;
				}
				if (x < 8 && board[(x + 1) * 9 + y] == 0 && (hChecker[x + 1] & 1 << v) == 0 && (wChecker[y] & 1 << v) == 0
						&& (sChecker[(x + 1) / 3 * 3 + y / 3] & 1 << v) == 0) {
					set(u, x, y);
					set(v, x + 1, y);
					pieces[u] |= 1 << v;
					pieces[v] |= 1 << u;
					if (solve(x, y + 1))
						return true;
					unset(u, x, y);
					unset(v, x + 1, y);
					pieces[u] ^= 1 << v;
					pieces[v] ^= 1 << u;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		int n, c = 0, v1, v2;
		StringBuilder sb = new StringBuilder();
		String PUZZLE = "Puzzle ";

		while ((n = read()) != 0) {
			board = new int[81];
			pieces = new int[9];
			wChecker = new int[9];
			hChecker = new int[9];
			sChecker = new int[9];
			for (int i = 0; i < n; i++) {
				set(v1 = read() - 1, System.in.read() - 'A', System.in.read() - '1');
				System.in.read();
				set(v2 = read() - 1, System.in.read() - 'A', System.in.read() - '1');
				System.in.read();
				pieces[v1] |= 1 << v2;
				pieces[v2] |= 1 << v1;
			}
			for (int i = 0; i < 9; i++) {
				set(i, System.in.read() - 'A', System.in.read() - '1');
				System.in.read();
			}
			solve(0, 0);
			sb.append(PUZZLE).append(++c).append('\n');
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++)
					sb.append(board[i * 9 + j]);
				sb.append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}