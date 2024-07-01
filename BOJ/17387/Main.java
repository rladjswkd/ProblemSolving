import java.io.IOException;

public class Main {
	private static final double eps = 1e-9;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			n = ~n + 1;
		return n;
	}

	public static void main(String[] args) throws IOException {
		int x1 = readInt(), y1 = readInt(), x2 = readInt(), y2 = readInt(),
				x3 = readInt(), y3 = readInt(), x4 = readInt(), y4 = readInt(),
				swap, cx1, cx2, res = 0;
		double s1, s2, i1, i2, d1, d2, yAt;

		if (x2 < x1) {
			swap = x1;
			x1 = x2;
			x2 = swap;
			swap = y1;
			y1 = y2;
			y2 = swap;
		}
		if (x4 < x3) {
			swap = x3;
			x3 = x4;
			x4 = swap;
			swap = y3;
			y3 = y4;
			y4 = swap;
		}
		if (!(x2 < x3 || x4 < x1)) {
			if (x1 == x2 && x3 == x4) {
				if (y1 <= y3 && y3 <= y2 || y1 <= y4 && y4 <= y2)
					res = 1;
			}
			else if (x1 == x2) {
				s2 = (double) (y4 - y3) / (x4 - x3);
				i2 = y3 - s2 * x3;
				yAt = s2 * x1 + i2;
				if (y1 <= yAt && yAt <= y2)
					res = 1;
			}
			else if (x3 == x4) {
				s1 = (double) (y2 - y1) / (x2 - x1);
				i1 = y1 - s1 * x1;
				yAt = s1 * x3 + i1;
				if (y3 <= yAt && yAt <= y4)
					res = 1;
			}
			else {
				s1 = (double) (y2 - y1) / (x2 - x1);
				i1 = y1 - s1 * x1;
				s2 = (double) (y4 - y3) / (x4 - x3);
				i2 = y3 - s2 * x3;
				cx1 = Math.max(x1, x3);
				cx2 = Math.min(x2, x4);
				d1 = (s1 * cx1 + i1) - (s2 * cx1 + i2);
				d2 = (s1 * cx2 + i1) - (s2 * cx2 + i2);
				if (d1 < 0 && d2 > 0 || d1 > 0 && d2 < 0 || Math.abs(d1) < eps || Math.abs(d2) < eps)
					res = 1;
			}
		}
		System.out.println(res);
	}
}