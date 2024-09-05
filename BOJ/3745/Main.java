import java.io.IOException;

public class Main {
	private static int n, x;
	private static int[] l;

	private static int r() throws IOException {
		int n = 0, c;

		while ((c = System.in.read()) == 32 || c == 10)
			continue;
		if (c == -1)
			return -1;
		n = c & 15;
		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void u(int v) {
		int a, b, c;

		if (x == 0)
			l[x++] = v;
		else {
			a = 0;
			b = x - 1;
			while (a < b) {
				c = (a + b) >>> 1;
				if (l[c] < v)
					a = c + 1;
				else
					b = c;
			}
			if (l[a] < v)
				l[x++] = v;
			else
				l[a] = v;
		}
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		l = new int[100000];
		while ((n = r()) != -1) {
			x = 0;
			for (int i = 0; i < n; i++)
				u(r());
			sb.append(x).append('\n');
		}
		System.out.print(sb.toString());
	}
}