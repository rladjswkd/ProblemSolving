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

	private static void updateLIS(int val) {
		int start = 0, end = length - 1, mid;

		while (start < end) {
			mid = (start + end) >>> 1;
			if (lis[mid] < val)
				start = mid + 1;
			else
				end = mid;
		}
		if (lis[start] < val)
			lis[length++] = val;
		else
			lis[start] = val;
	}

	public static void main(String[] args) throws IOException {
		int t = readInt();
		StringBuilder sb = new StringBuilder();

		lis = new int[40000];
		while (t-- > 0) {
			n = readInt();
			length = 0;
			lis[length++] = readInt();
			for (int i = 1; i < n; i++)
				updateLIS(readInt());
			sb.append(length).append('\n');
		}
		System.out.print(sb.toString());
	}
}