import java.io.IOException;

public class Main {
	private static int n, length;
	private static int[] h;
	private static int read() throws IOException {
		int n = System.in.read() & 15, c;
		
		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args) throws IOException {
		int next;
		long res = 0;

		h = new int[n = read()];
		h[length++] = read();
		for (int i = 1; i < n; i++) {
			next = read();
			while (length > 0 && h[length - 1] <= next)
				length--;
			h[length++] = next;
			res += length - 1;
		}
		System.out.println(res);
	}
}