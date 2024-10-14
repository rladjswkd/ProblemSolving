import java.io.IOException;

public class Main {
	private static int n, h;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] ceil, floor;
		int value, count, cur;

		n = read();
		h = read();
		ceil = new int[h + 1];
		floor = new int[h + 1];
		for (int i = 0; i < n; i += 2) {
			floor[read()]++;
			ceil[read()]++;
		}
		for (int i = h - 1; i >= 1; i--) {
			floor[i] += floor[i + 1];
			ceil[i] += ceil[i + 1];
		}
		value = floor[1] + ceil[h];
		count = 1;
		for (int i = 2; i <= h; i++) {
			if ((cur = floor[i] + ceil[h - i + 1]) == value)
				count++;
			else if (cur < value) {
				value = cur;
				count = 1;
			}
		}
		System.out.println(new StringBuilder().append(value).append(' ').append(count));
	}
}