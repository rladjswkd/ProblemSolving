import java.io.IOException;

public class Main {
	private static int n, bits, res;
	private static int[][] results;
	private static int[] order, scores;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int idx) {
		int out, player, score, result, base;

		if (idx == 9) {
			player = score = 0;
			for (int inning = 0; inning < n; inning++) {
				out = base = 0;
				while (out < 3) {
					if ((result = results[inning][order[player]]) == 0)
						out++;
					else {
						base = (base | 1) << result;
						score += scores[base >>> 4];
						base &= 0b1110;
					}
					player = (player + 1) % 9;
				}
			}
			res = Math.max(res, score);
			return;
		}
		if (idx == 3) {
			solve(idx + 1);
			return;
		}
		for (int i = 1; i < 9; i++) {
			if ((bits & 1 << i) == 0) {
				order[idx] = i;
				bits |= 1 << i;
				solve(idx + 1);
				bits ^= 1 << i;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		results = new int[n = read()][9];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 9; j++) {
				results[i][j] = System.in.read() & 15;
				System.in.read();
			}
		}
		order = new int[9];
		scores = new int[] { 0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4 };
		solve(0);
		System.out.println(res);
	}
}