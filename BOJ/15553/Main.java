import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int n, k;
	private static int[] t;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int res;
		int[] diff;

		t = new int[n = read()];
		res = k = read();
		diff = new int[n - 1];
		t[0] = read();
		for (int i = 0; i < n - 1; i++)
			diff[i] = (t[i + 1] = read()) - t[i];
		Arrays.sort(diff);
		for (int i = 0; i < n - 1 - k + 1; i++)
			res += diff[i];
		System.out.println(res);
	}
}