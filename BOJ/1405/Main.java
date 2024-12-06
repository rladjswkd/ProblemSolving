import java.io.IOException;

public class Main {
	private static int n, length;
	private static double np, ep, wp, sp, res;
	private static boolean[][] field;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int count, int x, int y, double p) {
		int nx, ny;

		if (count == n) {
			res += p;
			return;
		}
		if (!field[x][ny = (y + 1) % length] && ep > 0) {
			field[x][ny] = true;
			solve(count + 1, x, ny, p * ep);
			field[x][ny] = false;
		}
		if (!field[x][ny = ((y - 1) + length) % length] && wp > 0) {
			field[x][ny] = true;
			solve(count + 1, x, ny, p * wp);
			field[x][ny] = false;
		}
		if (!field[nx = (x + 1) % length][y] && sp > 0) {
			field[nx][y] = true;
			solve(count + 1, nx, y, p * sp);
			field[nx][y] = false;
		}
		if (!field[nx = ((x - 1) + length) % length][y] && np > 0) {
			field[nx][y] = true;
			solve(count + 1, nx, y, p * np);
			field[nx][y] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		length = (n = read()) + 1;
		ep = read() / 100.0;
		wp = read() / 100.0;
		sp = read() / 100.0;
		np = read() / 100.0;
		field = new boolean[length][length];
		field[0][0] = true;
		solve(0, 0, 0, 1);
		System.out.println(res);
	}
}