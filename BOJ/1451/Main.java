import java.io.IOException;

public class Main {
	private static int height, width;
	private static int[][] a;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		long res = 0, r1, r2, r3, r4;

		a = new int[(height = read()) + 1][(width = read()) + 1];
		for (int i = 1; i <= height; i++) {
			for (int j = 1; j <= width; j++)
				a[i][j] = (System.in.read() & 15) + a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1];
			System.in.read();
		}
		for (int v1 = 1; v1 < width - 1; v1++)
			for (int v2 = v1 + 1; v2 < width; v2++)
				res = Math.max(res,
						(long) a[height][v1] * (long) (a[height][v2] - a[height][v1]) * (a[height][width] - a[height][v2]));
		for (int h1 = 1; h1 < height - 1; h1++)
			for (int h2 = h1 + 1; h2 < height; h2++)
				res = Math.max(res,
						(long) a[h1][width] * (long) (a[h2][width] - a[h1][width]) * (a[height][width] - a[h2][width]));
		for (int h = 1; h < height; h++) {
			for (int v = 1; v < width; v++) {
				r1 = a[h][v];
				r2 = a[h][width] - r1;
				r4 = a[height][v] - r1;
				r3 = a[height][width] - r2 - r4 - r1;
				res = Math.max(res, (r1 + r2) * r3 * r4);
				res = Math.max(res, (r2 + r3) * r1 * r4);
				res = Math.max(res, (r3 + r4) * r1 * r2);
				res = Math.max(res, (r4 + r1) * r2 * r3);
			}
		}
		System.out.println(res);
	}
}