import java.io.IOException;

public class Main {
	private static int n, res;
	private static int[] s, w;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int idx, int count) {
		if (idx == n) {
			res = Math.max(res, count);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (i == idx)
				continue;
			if (s[idx] > 0 && s[i] > 0) {
				s[i] -= w[idx];
				s[idx] -= w[i];
				solve(idx + 1, count + (s[i] <= 0 ? 1 : 0) + (s[idx] <= 0 ? 1 : 0));
				s[i] += w[idx];
				s[idx] += w[i];
			} else
				solve(idx + 1, count);
		}
	}

	public static void main(String[] args) throws IOException {
		n = read();
		s = new int[n];
		w = new int[n];
		for (int i = 0; i < n; i++) {
			s[i] = read();
			w[i] = read();
		}
		solve(0, 0);
		System.out.println(res);
	}
}