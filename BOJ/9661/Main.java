import java.io.IOException;

public class Main {
	private static long read() throws IOException {
		long n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		long r;

		System.out.println((r = read() % 5) == 0 || r == 2 ? "CY" : "SK");
	}
}