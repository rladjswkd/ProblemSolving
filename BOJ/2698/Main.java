import java.io.IOException;

public class Main {
	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int solve(int n, int k, int l, int r) {
		int s;

		if (l == n) {
			s = 0;
			for (int i = 0; i < n - 1; i++)
				s += ((r & 1 << i) >> i) * ((r & 1 << i + 1) >> i + 1);
			return s == k ? 1 : 0;
		}
		return solve(n, k, l + 1, r << 1) + solve(n, k, l + 1, r << 1 | 1);
	}

	public static void main(String[] args) throws IOException {
		int n = read();

		for (int i = 2; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				System.out.printf("%d, %d: %d\n", i, j, solve(i, j, 0, 0));
			}
		}
	}
}