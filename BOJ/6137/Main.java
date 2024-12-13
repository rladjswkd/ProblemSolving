import java.io.IOException;

public class Main {
	private static int n;
	private static char[] s;
	private static StringBuilder t;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int f = 0, r = (n = read()) - 1, stride, count = 0;

		if (n == 0)
			return;
		t = new StringBuilder();
		s = new char[n];
		for (int i = 0; i < n; i++) {
			s[i] = (char) System.in.read();
			System.in.read();
		}
		while (f <= r) {
			if (count > 0 && count % 80 == 0)
				t.append('\n');
			if (s[f] < s[r])
				t.append(s[f++]);
			else if (s[f] > s[r])
				t.append(s[r--]);
			else {
				stride = 1;
				while (f + stride <= r - stride && s[f + stride] == s[r - stride])
					stride++;
				if (f + stride > r - stride)
					t.append(s[f++]);
				else if (s[f + stride] < s[r - stride])
					t.append(s[f++]);
				else if (s[f + stride] > s[r - stride])
					t.append(s[r--]);
			}
			count++;
		}
		System.out.println(t.toString());
	}
}