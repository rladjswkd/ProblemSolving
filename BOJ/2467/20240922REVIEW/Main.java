import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int n;
	private static int[] values;

	private static int read() throws IOException {
		int n = 0, c, s = System.in.read();

		if (s != 45)
			n = s & 15;
		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return s != 45 ? n : ~n + 1;
	}

	public static void main(String[] args) throws IOException {
		// l1과 l2의 합은 절댓값이 최댓값으로 초기화되어야 하므로 둘 다 -10억 또는 +10억으로 초기화해야 한다.
		int left = 0, right, l1 = 1000000000, l2 = 1000000000, blended;

		values = new int[n = read()];
		for (int i = 0; i < n; i++)
			values[i] = read();
		Arrays.sort(values);
		right = n - 1;
		while (left < right) {
			if (Math.abs(blended = values[left] + values[right]) < Math.abs(l1 + l2)) {
				l1 = values[left];
				l2 = values[right];
			}
			if (blended < 0)
				left++;
			else
				right--;
		}
		System.out.printf("%d %d\n", l1, l2);
	}
}