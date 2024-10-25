import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int length, diff, res;
	private static int[] seq;

	private static int read() throws IOException {
		int n = 0, c, s = System.in.read();

		if (s != 45)
			n = s & 15;
		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return s == 45 ? ~n + 1 : n;

	}

	public static void main(String[] args) throws IOException {
		int left = 0, right = 0, curDiff;

		seq = new int[length = read()];
		diff = read();
		for (int i = 0; i < length; i++)
			seq[i] = read();
		Arrays.sort(seq);
		res = Integer.MAX_VALUE;
		while (right < length) {
			curDiff = seq[right] - seq[left];
			if (curDiff < diff)
				right++;
			else if (curDiff > diff) {
				res = Math.min(res, curDiff);
				left++;
			} else {
				res = diff;
				break;
			}
		}
		System.out.println(res);
	}
}