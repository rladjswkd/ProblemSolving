import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int n, l1, l2, l3;
	private static int[] liquids;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			n = ~n + 1;
		return n;
	}

	private static void solve() {
		int l, r;
		long current, prev = Long.MAX_VALUE, abs;

		for (int i = 1; i < n - 1; i++) {
			l = i - 1;
			r = i + 1;
			while (true) {
				if ((abs = Math.abs(current = (long) liquids[l] + liquids[i] + liquids[r])) < prev) {
					prev = abs;
					l1 = liquids[l];
					l2 = liquids[i];
					l3 = liquids[r];
				}
				if (current < 0) {
					if (r < n - 1)
						r++;
					else
						break;
				} else if (current > 0) {
					if (0 < l)
						l--;
					else
						break;
				} else
					return;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		n = readInt();
		liquids = new int[n];
		for (int i = 0; i < n; i++)
			liquids[i] = readInt();
		Arrays.sort(liquids);
		solve();
		System.out.println(new StringBuilder().append(l1).append(' ').append(l2).append(' ').append(l3).toString());
	}
}