import java.io.IOException;

public class Main {
	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = read(), length = 0;
		boolean[] nonPrimes = new boolean[n + 1];
		int[] primes = new int[4203], dp;

		for (int i = 2; i <= n; i++) {
			if (nonPrimes[i])
				continue;
			primes[length++] = i;
			for (int j = i + i; j <= n; j += i)
				nonPrimes[j] = true;
		}
		dp = new int[n + 1];
		for (int i = 0; i < length; i++) {
			dp[primes[i]] = (dp[primes[i]] + 1) % 123456789;
			for (int x = primes[i] + 2; x <= n; x++)
				dp[x] = (dp[x] + dp[x - primes[i]]) % 123456789;
		}
		System.out.println(dp[n]);
	}
}