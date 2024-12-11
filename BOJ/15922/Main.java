import java.io.IOException;

public class Main {
	private static int n, res;

	private static int read() throws IOException {
		int n = 0, c, s = System.in.read();

		if (s != 45)
			n = s & 15;
		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return s != 45 ? n : ~n + 1;
	}

	public static void main(String[] args) throws IOException {
		int x, y, py;

		n = read();
		x = read();
		y = read();
		res += y - x;
		py = y;
		for (int i = 1; i < n; i++) {
			x = read();
			y = read();
			if (py <= x) {
				res += y - x;
				py = y;
			} else if (py < y) {
				res += y - py;
				py = y;
			}
		}
		System.out.println(res);
	}
}