import java.io.IOException;

public class Main {
	private static int h, w;
	private static int[][] a;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int r1, r2, r3;
		long res = 0;

		a = new int[(h = read()) + 1][(w = read()) + 1];
		for (int i = 1; i <= h; i++) {
			for (int j = 1; j <= w; j++)
				a[i][j] = (System.in.read() & 15) + a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1];
			System.in.read();
		}
		for (int h1 = 1; h1 < h; h1++) {
			for (int h2 = h1 + 1; h2 < h; h2++) {
				for (int v1 = 1; v1 < w; v1++) {
					for (int v2 = v1 + 1; v2 < w; v2++) {
						
						res = Math.max(res, );
					}
				}
			}
		}
		System.out.println();
	}
}