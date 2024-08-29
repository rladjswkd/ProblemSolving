import java.io.IOException;

public class Main {
	private static final long MAX = 153L; // 9 * 17;

	private static long readInt() throws IOException {
		long n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static long calculate(long v) {
		long res = v;

		while (v > 0) {
			res += v % 10;
			v /= 10;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		long n = readInt(), res = 0;

		for (long i = n < MAX ? 1 : n - MAX; i < n; i++) {
			if (calculate(i) == n) {
				res = i;
				break;
			}
		}
		System.out.println(res);
	}
}