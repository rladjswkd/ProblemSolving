import java.io.IOException;

public class Main {
	private static int n, a, b, c, d, e, f;
	private static long s3, s2, s1;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int biggest = 0;

		n = read();
		if (n == 1) {
			biggest = Math.max(biggest, a = read());
			biggest = Math.max(biggest, b = read());
			biggest = Math.max(biggest, c = read());
			biggest = Math.max(biggest, d = read());
			biggest = Math.max(biggest, e = read());
			biggest = Math.max(biggest, f = read());
			System.out.println(a + b + c + d + e + f - biggest);
		} else {
			a = read();
			b = read();
			c = read();
			d = read();
			e = read();
			f = read();
			s3 = Math.min(a + b + c, Math.min(a + b + d, Math.min(a + c + e,
					Math.min(a + d + e, Math.min(b + c + f, Math.min(b + d + f, Math.min(c + e + f, d + e + f)))))));
			s2 = Math.min(a + b, Math.min(a + c, Math.min(a + e, Math.min(a + d, Math.min(b + c, Math.min(b + d,
					Math.min(b + f, Math.min(c + e, Math.min(c + f, Math.min(d + e, Math.min(d + f, e + f)))))))))));
			s1 = Math.min(a, Math.min(b, Math.min(c, Math.min(d, Math.min(e, f)))));
			System.out.println(
					s3 * 4 + s2 * (n - 2) * 4 + s2 * (n - 1) * 4 + s1 * (n - 2) * (n - 2) + s1 * (n - 2) * (n - 1) * 4);
		}
	}
}