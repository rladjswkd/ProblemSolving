import java.io.IOException;

public class Main {
	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = read(), i, res = 0;
		int[] b = new int[n];
		boolean f = true;

		for (i = 0; i < n; i++)
			b[i] = read();
		while (f) {
			f = false;
			for (i = 0; i < n; i++) {
				if (b[i] % 2 == 1) {
					res++;
					b[i]--;
				}
				if (b[i] > 0)
					f = true;
			}
			if (f) {
				for (i = 0; i < n; i++)
					b[i] /= 2;
				res++;
			}
		}
		System.out.println(res);
	}
}