import java.io.IOException;

public class Main {
	private static long readInt() throws IOException {
		long n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static long mul_inv(long a, long b) {
		long b0 = b, t, q;
		long x0 = 0, x1 = 1;

		if (b == 1)
			return 1;
		while (a > 1) {
			if (b == 0)
				return -1;
			q = a / b;
			t = b;
			b = a % b;
			a = t;
			t = x0;
			x0 = x1 - q * x0;
			x1 = t;
		}
		if (x1 < 0)
			x1 += b0;
		return x1;
	}

	public static void main(String[] args) throws IOException {
		long n = readInt(), a = readInt();

		System.out.println(new StringBuilder().append(n - a).append(' ').append(mul_inv(a, n)));
	}
}