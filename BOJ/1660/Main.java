import java.io.IOException;

public class Main {
	private static int n, res;
	private static int[] arr;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int start, int left, int count) {
		if (res <= count)
			return;
		if (left == 0) {
			res = Math.min(res, count);
			return;
		}
		for (int i = start; i >= 1; i--)
			if (arr[i] <= left)
				solve(i, left - arr[i], count + 1);
	}

	public static void main(String[] args) throws IOException {
		int idx;

		n = read();
		arr = new int[121];
		for (int i = 1, diff = 1; i <= 120; i++, diff++)
			arr[i] = arr[i - 1] + diff;
		for (int i = 1; i <= 120; i++)
			arr[i] += arr[i - 1];
		res = n;
		idx = 120;
		while (n < arr[idx])
			idx--;
		while (idx >= 1)
			solve(idx--, n, 0);
		System.out.println(res);
	}
}