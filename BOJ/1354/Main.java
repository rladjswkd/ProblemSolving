import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Main {
	private static int p, q, x, y;
	private static Map<Long, Long> mapper;

	private static long read() throws IOException {
		long n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static long solve(long i) {
		if (i <= 0)
			return 1;
		if (!mapper.containsKey(i))
			mapper.put(i, solve(i / p - x) + solve(i / q - y));
		return mapper.get(i);
	}

	public static void main(String[] args) throws IOException {
		long n = read();
		int size;

		p = (int) read();
		q = (int) read();
		x = (int) read();
		y = (int) read();
		if (n == 0)
			System.out.println(1);
		else {
			size = (int) (Math.max((Math.log(n) / Math.log(p)), Math.log(n) / Math.log(q)) / 0.75);
			mapper = new HashMap<>(size);
			System.out.println(solve(n));
		}
	}
}