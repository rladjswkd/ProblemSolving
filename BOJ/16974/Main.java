import java.io.IOException;

public class Main {
	private static int n;
	private static long[] counter, pattyCounter;

	private static long read() throws IOException {
		long n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static long solve(int level, long x) {
		long centerP;

		if (x == 0)
			return 0;
		if (x == counter[level])
			return pattyCounter[level];
		centerP = 1L + counter[level] >> 1;
		if (x < centerP)
			return solve(level - 1, x - 1);
		else if (x == centerP)
			return 1L + solve(level - 1, x - 2);
		return 1L + solve(level - 1, centerP - 2) + solve(level - 1, x - centerP);
	}

	public static void main(String[] args) throws IOException {
		n = (int) read();
		counter = new long[n + 1];
		pattyCounter = new long[n + 1];
		counter[0] = pattyCounter[0] = 1;
		for (int i = 1; i <= n; i++) {
			counter[i] = counter[i - 1] * 2 + 3;
			pattyCounter[i] = pattyCounter[i - 1] * 2 + 1;
		}
		System.out.println(solve(n, read()));
	}
}