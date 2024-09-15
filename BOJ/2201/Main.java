import java.io.IOException;

public class Main {
	private static long[] fibo;

	private static long read() throws IOException {
		long n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	// private static int findLowerBound(long val, int bound) {
	// int start = 0, end = bound - 1, mid;

	// while (start < end) {
	// mid = (start + end) >>> 1;
	// if (rangeTotal[mid] < val)
	// start = mid + 1;
	// else
	// end = mid;
	// }
	// return rangeTotal[start] < val ? 86 : start;
	// }

	public static void main(String[] args) throws IOException {
		long k = read();
		int idx = 86, prevIdx;
		StringBuilder sb = new StringBuilder();

		fibo = new long[87];
		fibo[0] = 1;
		fibo[1] = 2;
		for (int i = 2; i < 87; i++)
			fibo[i] = fibo[i - 1] + fibo[i - 2];
		// sb의 첫 번째 값으로 바로 1을 추가하기 위한 코드
		while (fibo[idx] > k)
			idx--;
		while (k > 0) {
			prevIdx = idx;
			while (fibo[idx] > k)
				idx--;
			// prevIdx - idx - 1개만큼 0을 추가
			for (int i = idx + 1; i < prevIdx; i++)
				sb.append(0);
			sb.append(1);
			k -= fibo[idx];
		}
		for (int i = 0; i < idx; i++)
			sb.append(0);
		System.out.println(sb.toString());
	}
}