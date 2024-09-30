import java.io.IOException;

public class Main {
	private static int length;
	private static int[] lis;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	// lower bound를 찾아서 value로 대체해야 한다..
	private static void updateLIS(int value) {
		int start = 0, end = length - 1, mid;

		while (start < end) {
			mid = (start + end) >>> 1;
			if (lis[mid] < value)
				start = mid + 1;
			else
				end = mid;
		}
		lis[lis[start] < value ? length++ : start] = value;
	}

	public static void main(String[] args) throws IOException {
		int t = read(), n, k;
		StringBuilder sb = new StringBuilder();

		lis = new int[10000];
		for (int c = 1; c <= t; c++) {
			n = read();
			k = read();
			lis[0] = read();
			length = 1;
			for (int i = 1; i < n; i++)
				updateLIS(read());
			sb.append('C').append('a').append('s').append('e').append(' ').append('#').append(c).append('\n')
					.append(k <= length ? 1 : 0).append('\n');
		}
		System.out.print(sb.toString());
	}
}