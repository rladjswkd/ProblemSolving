import java.io.IOException;

public class Main {
	private static int n;
	private static int[] r, stack, res;
	private static final String STR = "IMPOSSIBLE\n";

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args) throws IOException {
		int t = read(), length, val, idx;
		StringBuilder sb = new StringBuilder();
		
		r = new int[100];
		stack = new int[100];
		res = new int[100];
		while (t-- > 0) {
			n = read();
			length = 0;
			for (int i = 0; i < n; i++)
				if ((r[i] = read()) == 0)
					stack[length++] = i;
			val = 0;
			while (length > 0) {
				res[idx = stack[--length]] = ++val;
				for (int i = idx; i < n; i++)
					if (--r[i] == 0)
						stack[length++] = i;
			}
			if (val < n)
				sb.append(STR);
			else {
				sb.append(res[0]);
				for (int i = 1; i < n; i++)
					sb.append(' ').append(res[i]);
				sb.append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}