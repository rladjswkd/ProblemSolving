import java.io.IOException;

public class Main {
	private static int n;
	private static int[] seq, res;
	private static boolean[] included;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int bound = 0, cur;
		StringBuilder sb = new StringBuilder();

		seq = new int[n = readInt()];
		included = new boolean[1000001];
		for (int i = 0; i < n; i++) {
			included[seq[i] = readInt()] = true;
			bound = Math.max(bound, seq[i]);
		}
		res = new int[bound + 1];
		for (int i = 0; i < n; i++) {
			cur = seq[i];
			for (int other = cur + cur; other <= bound; other += cur) {
				if (included[other]) {
					res[cur]++;
					res[other]--;
				}
			}
		}
		for (int v : seq)
			sb.append(res[v]).append(' ');
		System.out.println(sb.toString());
	}
}