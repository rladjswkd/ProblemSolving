import java.io.IOException;

public class Main {
	private static int n, k;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int length = 0, count = 0;
		boolean[] nonPrimes;
		int[] primes = new int[78498];
		StringBuilder sb = new StringBuilder();

		nonPrimes = new boolean[1000001];
		n = read();
		k = read();
		for (int i = 2; i <= 1000; i++) {
			if (nonPrimes[i])
				continue;
			primes[length++] = i;
			for (int j = i + i; j <= 1000000; j += i)
				nonPrimes[j] = true;
		}
		for (int i = 1001; i <= 1000000; i++)
			if (!nonPrimes[i])
				primes[length++] = i;
		for (int i = 0; i < length && count < n; i++) {
			if (primes[i] % k == 1) {
				sb.append(primes[i]).append(' ');
				count++;
			}
		}
		System.out.println(sb.toString());
	}
}