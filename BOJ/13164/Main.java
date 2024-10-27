import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int n, k;
	private static int[] diffs;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int l, r, res = 0;

		n = read();
		k = read();
		diffs = new int[n - 1];
		l = read();
		for (int i = 0; i < n - 1; i++) {
			r = read();
			diffs[i] = r - l;
			l = r;
		}
		Arrays.sort(diffs);
		for (int i = 0; i < n - k; i++)
			res += diffs[i];
		System.out.println(res);
	}
}