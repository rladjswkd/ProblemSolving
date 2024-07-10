import java.io.IOException;

public class Main {
	private static int n, k;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static long countSmaller(long val) {
		int start, end, mid;
		long res = 0;

		for (int i = 1; i <= n; i++) {
			start = 1;
			end = n;
			// lower bound
			while (start < end) {
				mid = (start + end) >>> 1;
				if ((long) i * mid < val)
					start = mid + 1;
				else
					end = mid;
			}
			if (start == 0)
				break;
			// 여기서의 start는 0-base가 아니므로 start를 그대로 반환하면 start를 포함한 개수를 반환하는 것과 마찬가지다.
			// 따라서 start를 뺀 start - 1을 반환해야 한다.
			res += (long) i * start < val ? n : start - 1;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		long start = 1, end, mid;

		n = readInt();
		k = readInt();
		end = (long) n * n;
		while (start <= end) {
			mid = (start + end) >>> 1;
			if (countSmaller(mid) < k)
				start = mid + 1;
			else
				end = mid - 1;
		}
		System.out.println(end);
	}
}