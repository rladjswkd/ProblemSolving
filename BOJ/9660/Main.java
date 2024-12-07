import java.io.IOException;

public class Main {
	private static long read() throws IOException {
		long n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int r = (int) (read() % 7L);

		System.out.println(r == 0 || r == 2 ? "CY" : "SK");
	}
}