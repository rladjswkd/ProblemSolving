import java.io.IOException;

public class Main {
	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = read(), first, last, next;
		StringBuilder sb = new StringBuilder();

		while (n-- > 0) {
			last = -1;
			sb.append((char) (first = System.in.read()));
			while ((next = System.in.read()) != 10)
				last = next;
			if (last != -1)
				sb.append((char) last).append('\n');
			else
				sb.append((char) first).append('\n');
		}
		System.out.print(sb.toString());
	}
}