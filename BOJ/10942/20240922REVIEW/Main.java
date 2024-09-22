import java.io.IOException;

public class Main {
	private static int size, queryCount;
	private static int[] seq;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		boolean[][] checker;
		int l, r;
		StringBuilder sb = new StringBuilder();

		seq = new int[size = read()];
		for (int i = 0; i < size; i++)
			seq[i] = read();
		checker = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			l = r = i;
			while (l >= 0 && r < size) {
				if (seq[l] == seq[r])
					checker[l][r] = true;
				else
					break;
				l--;
				r++;
			}
			l = i;
			r = i + 1;
			while (l >= 0 && r < size) {
				if (seq[l] == seq[r])
					checker[l][r] = true;
				else
					break;
				l--;
				r++;
			}
		}
		queryCount = read();
		while (queryCount-- > 0)
			sb.append(checker[read() - 1][read() - 1] ? 1 : 0).append('\n');
		System.out.print(sb.toString());
	}
}