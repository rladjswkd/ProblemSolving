import java.io.IOException;

public class Main {
	private static int v1, v2;
	private static boolean[] nonPrimes;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt();
		StringBuilder sb = new StringBuilder();

		nonPrimes = new boolean[n + 1];
		nonPrimes[0] = nonPrimes[1] = true;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (nonPrimes[i])
				continue;
			for (int j = i + i; j <= n; j += i)
				if (!nonPrimes[j])
					nonPrimes[j] = true;
		}
		if (n % 2 == 0 && n - 4 >= 4) {
			n -= 4;
			sb.append(2).append(' ').append(2).append(' ');
		} else if (n % 2 == 1 && n - 5 >= 4) {
			n -= 5;
			sb.append(2).append(' ').append(3).append(' ');
		} else {
			System.out.println(-1);
			return;
		}
		for (int i = 2; i <= n; i++) {
			if (!nonPrimes[i] && !nonPrimes[n - i]) {
				sb.append(i).append(' ').append(n - i);
				break;
			}
		}
		System.out.println(sb.toString());
	}
}