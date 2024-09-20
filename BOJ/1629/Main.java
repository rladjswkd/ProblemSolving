import java.io.IOException;

public class Main {
	private static int read() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}
	public static void main(String[] args) throws IOException {
		long a = read();
		int b = read(), c = read();
		int res = 1;

		while (b > 0) {
			if ((b & 1) == 1)
				res = (int)(res * a % c);
			b >>>= 1;
			a = a * a % c;
		}
		System.out.println(res);
	}
}