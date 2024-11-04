import java.io.IOException;

public class Main {
	private static int n;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		boolean[] nonPrimes;
		long count, res, cnt;

		nonPrimes = new boolean[(n = read()) + 1];
		res = (int) ((long) (n - 1) * n / 2);
		for (int i = 2; i <= n; i++) {
			if (nonPrimes[i])
				continue;
			count = cnt = 0;
			for (int j = i + i; j <= n; j += i) {
				count++;
				if (nonPrimes[j])
					cnt++;
				nonPrimes[j] = true;
			}
			res -= (int) ((long) count * (count + 1) / 2);
			res += (int) ((long) (cnt - 1) * cnt / 2);
		}
		System.out.println(res);
	}
}