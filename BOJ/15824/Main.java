import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int n;
	private static int[] scales;
	private static final long R = 1_000_000_007L;

	private static int read() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int res = 0;
		// coefficient는 사이에 k개의 메뉴를 갖는 모든 최대값의 합 - 모든 최소값의 합의 값을 나타낸다.
		// pow는 2의 k제곱을 나타낸다.
		long coef = 0, pow = 1;

		scales = new int[n = read()];
		for (int i = 0; i < n; i++)
			scales[i] = read();
		Arrays.sort(scales);
		// k는 최대값과 최소값을 포함하지 않는 그 사이의 메뉴 개수를 나타낸다.
		for (int k = 0; k <= n - 2; k++) {
			coef = (coef + scales[n - 1 - k] - (scales[k] % R) + R) % R;
			res = (int) ((res + coef * pow) % R);
			pow = (pow << 1) % R;
		}
		System.out.println(res);
	}
}