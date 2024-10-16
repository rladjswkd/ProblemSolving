import java.io.IOException;

public class Main {
	private static int n, k, bits, operand1, res;
	private static int[] ns, ks;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void check(int operand, int length) {
		int value, valueLength;

		if (length == ns[1]) {
			value = operand1 * operand;
			valueLength = 0;
			while (value > 0) {
				if ((bits & 1 << (value % 10)) == 0)
					break;
				value /= 10;
				valueLength++;
			}
			if (value == 0 && valueLength == ns[n - 1])
				res++;
			return;
		}
		for (int i = 0; i < k; i++) {
			value = operand1 * ks[i];
			valueLength = 0;
			while (value > 0) {
				if ((bits & 1 << (value % 10)) == 0)
					break;
				value /= 10;
				valueLength++;
			}
			if (value == 0 && valueLength == ns[2 + length])
				check((operand << 3) + (operand << 1) + ks[i], length + 1);
		}
	}

	private static void solve(int operand, int length) {
		if (length == ns[0]) {
			operand1 = operand;
			check(0, 0);
			return;
		}
		for (int i = 0; i < k; i++)
			solve((operand << 3) + (operand << 1) + ks[i], length + 1);
	}

	public static void main(String[] args) throws IOException {
		ns = new int[n = read()];
		for (int i = 0; i < n; i++)
			ns[i] = read();
		ks = new int[k = read()];
		for (int i = 0; i < k; i++)
			bits |= 1 << (ks[i] = read());
		solve(0, 0);
		System.out.println(res);
	}
}