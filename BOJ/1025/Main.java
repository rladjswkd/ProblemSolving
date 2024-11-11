import java.io.IOException;

public class Main {
	private static int h, w;
	private static int[][] table;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int res = -1, asc = 0, desc = 0, pow = 0, i, j, x, y;

		table = new int[h = read()][w = read()];
		for (i = 0; i < h; i++) {
			for (j = 0; j < w; j++)
				table[i][j] = System.in.read() & 15;
			System.in.read();
		}
		for (i = 0; i < h; i++) {
			for (j = 0; j < w; j++) {
				for (int dx = 0; dx < h; dx++) {
					for (int dy = 0; dy < w; dy++) {
						if (dx == dy && dy == 0) {
							if (Math.pow((int) Math.sqrt(table[i][j]), 2) == table[i][j])
								res = Math.max(res, table[i][j]);
							continue;
						}
						pow = 1;
						asc = desc = 0;
						x = i;
						y = j;
						while (x < h && y < w) {
							asc = (asc << 3) + (asc << 1) + table[x][y];
							desc += pow * table[x][y];
							pow = (pow << 3) + (pow << 1);
							x += dx;
							y += dy;
							if (Math.pow((int) Math.sqrt(asc), 2) == asc)
								res = Math.max(res, asc);
							if (Math.pow((int) Math.sqrt(desc), 2) == desc)
								res = Math.max(res, desc);
						}
						dx = ~dx + 1;
						pow = 1;
						asc = desc = 0;
						x = i;
						y = j;
						while (0 <= x && y < w) {
							asc = (asc << 3) + (asc << 1) + table[x][y];
							desc += pow * table[x][y];
							pow = (pow << 3) + (pow << 1);
							x += dx;
							y += dy;
							if (Math.pow((int) Math.sqrt(asc), 2) == asc)
								res = Math.max(res, asc);
							if (Math.pow((int) Math.sqrt(desc), 2) == desc)
								res = Math.max(res, desc);
						}
						dx = ~dx + 1;
					}
				}
			}
		}
		System.out.println(res);
	}
}