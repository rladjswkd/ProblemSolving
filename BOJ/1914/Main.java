import java.io.IOException;
import java.math.BigInteger;

public class Main {
	private static int n;
	private static StringBuilder sb;

	private static int read() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int from, int to, int count) {
		if (count == 1) {
			sb.append(from).append(' ').append(to).append('\n');
			return;
		}
		solve(from, 5 - from - to + 1, count - 1);
		solve(from, to, 1);
		solve(5 - from - to + 1, to, count - 1);
	}

	public static void main(String[] args) throws IOException {
		sb = new StringBuilder().append(new BigInteger("2").pow(n = read()).subtract(BigInteger.ONE).toString(10))
				.append('\n');
		if (n <= 20)
			solve(1, 3, n);
		System.out.print(sb.toString());
	}
}