import java.io.IOException;

public class Main {
	private static int la, lb, lr;
	private static int[] a, b, r;

	private static int r() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void s(int ia, int ib) {
		int[] ad, bd;

		if (ia == la + 1 || ib == lb + 1)
			return;
		ad = new int[101];
		bd = new int[101];
		while (ia <= la && ib <= lb) {
			if (ad[a[ia - 1]] == 0)
				ad[a[ia - 1]] = ia;
			if (bd[b[ib - 1]] == 0)
				bd[b[ib - 1]] = ib;
			ia++;
			ib++;
		}
		while (ia <= la) {
			if (ad[a[ia - 1]] == 0)
				ad[a[ia - 1]] = ia;
			ia++;
		}
		while (ib <= lb) {
			if (bd[b[ib - 1]] == 0)
				bd[b[ib - 1]] = ib;
			ib++;
		}
		for (int i = 100; i >= 0; i--) {
			if (ad[i] > 0 && bd[i] > 0) {
				r[lr++] = i;
				s(ad[i] + 1, bd[i] + 1);
				break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int i;
		StringBuilder sb = new StringBuilder();

		a = new int[la = r()];
		for (i = 0; i < la; i++)
			a[i] = r();
		b = new int[lb = r()];
		for (i = 0; i < lb; i++)
			b[i] = r();
		r = new int[Math.max(la, lb)];
		s(1, 1);
		sb.append(lr).append('\n');
		for (i = 0; i < lr; i++)
			sb.append(r[i]).append(' ');
		System.out.println(sb.toString());
	}
}