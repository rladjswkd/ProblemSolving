import java.io.IOException;

public class Main {
	private static boolean[] a, b;
	private static int la, lb;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c<= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		boolean[] d1, d2;
		int t, ch, id2, ld1, ld2, ia, ib, res;
		StringBuilder sb = new StringBuilder();

		t = read();
		a = new boolean[100000];
		b = new boolean[100000];
		d1 = new boolean[100000];
		d2 = new boolean[100000];
		while (t-- > 0) {
			res = ia = ib = id2 = ld1 = ld2 = la = lb = 0;
			while ((ch = System.in.read()) >= 97)
				a[la++] = ch == 97;
			while ((ch = System.in.read()) >= 97)
				b[lb++] = ch == 97;
			if (la != lb) {
				sb.append(-1).append('\n');
				continue;
			}
			while (ia < la) {
				if (ld2 - id2 > 0 && d2[id2] == b[ib]) {
					d1[ld1++] = d2[id2++];
					ib++;
				}
				else if (a[ia] == b[ib]) {
					d1[ld1++] = a[ia++];
					res += ld2 - id2;
					ib++;
				}
				else
					d2[ld2++] = a[ia++];
			}
			while (ib < lb && b[ib] == d2[id2]) {
				ib++;
				id2++;
			}
			if (ib == lb)
				sb.append(res).append('\n');
			else
				sb.append(-1).append('\n');
		}
		System.out.print(sb.toString());
	}
}