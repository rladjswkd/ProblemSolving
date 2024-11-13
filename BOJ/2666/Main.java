import java.io.IOException;

public class Main {
	private static int closetCount, open1, open2, length;
	private static int[] sequence;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int solve(int idx, int l, int r) {
		int closet;

		if (idx == length)
			return 0;
		closet = sequence[idx];
		// closet <= l
		if (closet <= l)
			return l - closet + solve(idx + 1, closet, r);
		// l < closet && closet < r
		else if (l < closet && closet < r)
			return Math.min(closet - l + solve(idx + 1, closet, r), r - closet + solve(idx + 1, l, closet));
		// r <= closet
		else
			return closet - r + solve(idx + 1, l, closet);
	}

	public static void main(String[] args) throws IOException {
		closetCount = read();
		open1 = read();
		open2 = read();
		sequence = new int[length = read()];
		for (int i = 0; i < length; i++)
			sequence[i] = read();
		System.out.println(solve(0, Math.min(open1, open2), Math.max(open1, open2)));
	}
}