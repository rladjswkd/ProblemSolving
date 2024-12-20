import java.io.IOException;

public class Main {
	private static int[] seq;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int solve(int n, int d, int k, int c) {
		int start = 0, end = k - 1, res, acc;
		int[] counter = new int[d + 1];

		// 애초에 쿠폰으로 먹을 수 있는 초밥을 고려하고 시작
		counter[c] = 1;
		res = 1;
		for (int i = 0; i < k; i++)
			if (counter[seq[i]]++ == 0)
				res++;
		if (k == n)
			return res;
		start = 1;
		end = (end + 1) % n;
		acc = res;
		while (start < n) {
			if (--counter[seq[start - 1]] == 0)
				acc--;
			if (counter[seq[end]]++ == 0)
				acc++;
			res = Math.max(res, acc);
			start++;
			end = (end + 1) % n;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), d = readInt(), k = readInt(), c = readInt();

		seq = new int[n];
		for (int i = 0; i < n; i++)
			seq[i] = readInt();
		System.out.println(solve(n, d, k, c));
	}
}