import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Main {
	private static long p, q;
	private static Map<Long, Long> dp;

	private static long read() throws IOException {
		long n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static long solve(long n) {
		if (n == 0)
			return 1L;
		if (!dp.containsKey(n))
			dp.put(n, solve(n / p) + solve(n / q));
		return dp.get(n);
	}

	public static void main(String[] args) throws IOException {
		long n = read();

		p = read();
		q = read();
		dp = new HashMap<>();
		System.out.println(solve(n));
	}
}