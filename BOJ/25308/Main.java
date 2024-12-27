import java.io.IOException;

public class Main {
	private static int[] points, order;
	private static int res;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void check() {
		int p;
		double ly, rx, px, py;

		for (p = 0; p < 8; p++) {
			ly = order[(p - 1 + 8) % 8];
			rx = order[(p + 1) % 8];
			px = py = order[p] / Math.sqrt(2);
			if (px * (-ly) - (py - ly) * rx >= 0)
				break;
		}
		if (p == 8)
			res++;
	}

	private static void solve(int count, int used) {
		if (count == 8) {
			check();
			return;
		}
		for (int i = 0; i < 8; i++) {
			if ((used & 1 << i) == 0) {
				order[count] = points[i];
				solve(count + 1, used | 1 << i);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		points = new int[8];
		for (int i = 0; i < 8; i++)
			points[i] = read();
		order = new int[8];
		solve(0, 0);
		System.out.println(res);
	}
}