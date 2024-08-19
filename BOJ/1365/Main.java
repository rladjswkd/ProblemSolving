import java.io.IOException;

public class Main {
	private static int n, length;
	private static int[] lis;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int findLowerBound(int val) {
		int start, end, mid;

		if (length == 0)
			return 0;
		start = 0;
		end = length - 1;
		while (start < end) {
			mid = (start + end) >>> 1;
			if (lis[mid] < val)
				start = mid + 1;
			else
				end = mid;
		}
		return lis[start] < val ? length : start;
	}

	public static void main(String[] args) throws IOException {
		int opposite, insertIndex;

		lis = new int[n = readInt()];
		length = 0;
		for (int i = 0; i < n; i++) {
			lis[insertIndex = findLowerBound(opposite = readInt())] = opposite;
			if (insertIndex == length)
				length++;
		}
		System.out.println(n - length);
	}
}