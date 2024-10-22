import java.io.IOException;

public class Main {
	private static long a, b;

	private static long read() throws IOException {
		long n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		boolean[] nonPrimes;
		int bound;
		long factorA, factorB, count = 0;

		a = read();
		b = read();
		nonPrimes = new boolean[10000001];
		bound = (int) Math.sqrt(10000000);
		for (int i = 2; i <= bound; i++) {
			if (nonPrimes[i])
				continue;
			for (int j = i + i; j <= 10000000; j += i)
				nonPrimes[j] = true;
			factorA = (long) (Math.log(a) / Math.log(i));
			if ((long) Math.pow(i, factorA) < a)
				factorA++;
			factorA = Math.max(factorA, 2);
			factorB = (long) (Math.log(b) / Math.log(i));
			if (factorA <= factorB)
				count += factorB - factorA + 1;
		}
		for (int i = bound + 1; i <= 10000000; i++) {
			if (nonPrimes[i])
				continue;
			factorA = (long) (Math.log(a) / Math.log(i));
			if ((long) Math.pow(i, factorA) < a)
				factorA++;
			factorA = Math.max(factorA, 2);
			factorB = (long) (Math.log(b) / Math.log(i));
			if (factorA <= factorB)
				count += factorB - factorA + 1;
		}
		System.out.println(count);
	}
}