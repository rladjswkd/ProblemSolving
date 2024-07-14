import java.io.IOException;

public class Main {
	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int length = readInt(), divisor = readInt();
		int[] remainders = new int[length];
		int[] counter = new int[divisor];
		long res = 0;

		remainders[0] = readInt() % divisor;
		if (remainders[0] == 0)
			res++;
		counter[remainders[0]]++;
		for (int i = 1; i < length; i++) {
			remainders[i] = (int) ((readInt() + (long) remainders[i - 1]) % divisor);
			if (remainders[i] == 0)
				res++;
			res += counter[remainders[i]]++;
		}
		System.out.println(res);
	}
}