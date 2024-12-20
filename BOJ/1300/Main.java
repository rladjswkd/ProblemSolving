import java.io.IOException;

public class Main {
	private static int n, k;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	// val보다 작은 것이 아닌, val보다 작거나 그와 같은 값의 개수를 세서 반환한다.
	private static long countSmaller(long val) {
		long res = 0;

		for (int i = 1; i <= n; i++)
			res += Math.min(n, val / i);
		return res;
	}

	public static void main(String[] args) throws IOException {
		long start, end, mid;

		n = readInt();
		k = readInt();
		start = 1;
		end = k;
		while (start < end) {
			mid = (start + end) >>> 1;
			if (countSmaller(mid) < k)
				start = mid + 1;
			else
				end = mid;
		}
		System.out.println(end);
	}
}