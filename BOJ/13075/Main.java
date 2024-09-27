import java.io.IOException;

public class Main {
	private static long read() throws IOException {
		long n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static long[][] multiply(long[][] l, long[][] r) {
		long[][] res = new long[2][2];

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 2; k++)
					res[i][j] = (res[i][j] + l[i][k] * r[k][j]) % 1_000_000_000L;
		return res;
	}

	public static void main(String[] args) throws IOException {
		long t = read(), x;
		long[][] a = new long[2][2], res = new long[2][2];
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			a[0][0] = a[0][1] = a[1][0] = res[0][0] = res[1][1] = 1;
			a[1][1] = res[0][1] = res[1][0] = 0;
			x = read() - 1;
			while (x > 0) {
				if ((x & 1) == 1)
					res = multiply(res, a);
				x >>>= 1;
				a = multiply(a, a);
			}
			sb.append(res[0][0]).append('\n');
		}
		System.out.print(sb.toString());
	}
}