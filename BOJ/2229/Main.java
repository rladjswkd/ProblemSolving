import java.io.IOException;

public class Main {
	private static int n;
	private static int[] s, d;

	private static int r() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int s(int start) {
		int l, g, c = 0;

		if (start == n)
			return 0;
		if (d[start] >= 0)
			return d[start];
		l = g = s[start];
		for (int i = start; i < n; i++) {
			l = Math.min(l, s[i]);
			g = Math.max(g, s[i]);
			c = Math.max(c, g - l + s(i + 1));
		}
		return d[start] = c;
	}

	public static void main(String[] args) throws IOException {
		s = new int[n = r()];
		d = new int[n];
		for (int i = 0; i < n; i++) {
			s[i] = r();
			d[i] = -1;
		}
		System.out.println(s(0));
	}
}