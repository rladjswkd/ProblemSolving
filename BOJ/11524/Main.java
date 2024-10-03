import java.io.IOException;

public class Main {
	private static int readInt() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static long readLong() throws IOException {
		long n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int[][] multiply(int[][] l, int[][] r) {
		int[][] res = new int[2][2];

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				res[i][j] = (int) (((long) l[i][0] * r[0][j] + (long) l[i][1] * r[1][j]) % 1_000_000_000L);
		return res;
	}

	public static void main(String[] args) throws IOException {
		int p = readInt();
		long y;
		int[][] a = new int[2][2], res = new int[2][2];
		StringBuilder sb = new StringBuilder();

		while (p-- > 0) {
			sb.append(readInt()).append(' ');
			y = readLong() - 1;
			a[0][0] = a[0][1] = a[1][0] = res[0][0] = res[1][1] = 1;
			a[1][1] = res[0][1] = res[1][0] = 0;
			while (y > 0) {
				if ((y & 1) == 1)
					res = multiply(res, a);
				y >>>= 1;
				a = multiply(a, a);
			}
			sb.append(res[0][0]).append('\n');
		}
		System.out.print(sb.toString());
	}
}