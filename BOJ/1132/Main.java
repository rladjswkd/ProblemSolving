
/*
 * 마스킹
 * 
 * 0 후보 : 수의 가장 앞에 나오지 않으면서 값이 가장 작은 알파벳
 */
import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int n;
	private static int[][] inputs;
	private static int[] lengths;
	private static long[] mask;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int cur, idx, length, firstMask = 0;
		long res = 0, minMask = Long.MAX_VALUE;

		n = readInt();
		mask = new long[10];
		inputs = new int[n][12];
		lengths = new int[n];
		for (int i = 0; i < n; i++) {
			idx = 0;
			while ('A' <= (cur = System.in.read()) && cur <= 'J')
				inputs[i][idx++] = cur;
			lengths[i] = idx;
			firstMask |= 1 << inputs[i][0] - 'A';
		}
		for (int i = 0; i < n; i++) {
			length = lengths[i];
			for (int j = 0; j < length; j++)
				mask[inputs[i][j] - 'A'] += 1 * Math.pow(10, length - 1 - j);
		}
		for (int i = 0; i < 10; i++) {
			if ((firstMask & 1 << i) == 0)
				minMask = Math.min(minMask, mask[i]);
		}
		// // idx 재활용
		// idx = 0;
		// while (idx < 10) {
		// if (mask[idx] == minMask) {
		// mask[idx] = 0;
		// break;
		// }
		// idx++;
		// }
		Arrays.sort(mask);
		for (int i = 9; i >= 0; i--)
			res += mask[i] * (long) i;
		System.out.println(res);
	}
}