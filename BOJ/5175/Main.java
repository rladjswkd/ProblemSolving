import java.io.IOException;

public class Main {
	private static int problemCount, all, count, res;
	private static int[] problems;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int idx, int algorithm, int problem, int curCount) {
		if (idx == problemCount) {
			if (algorithm == all && curCount < count) {
				res = problem;
				count = curCount;
			}
			return;
		}
		solve(idx + 1, algorithm | problems[idx], problem | 1 << idx, curCount + 1);
		solve(idx + 1, algorithm, problem, curCount);
	}

	public static void main(String[] args) throws IOException {
		int k = read(), c, n, problem;
		StringBuilder sb = new StringBuilder();

		problems = new int[20];
		for (int tc = 1; tc <= k; tc++) {
			res = 0;
			all = (1 << read()) - 1;
			problemCount = read();
			for (int i = 0; i < problemCount; i++) {
				problem = 0;
				c = 0;
				while (c != 10) {
					n = 0;
					while (48 <= (c = System.in.read()) && c <= 57)
						n = (n << 3) + (n << 1) + (c & 15);
					if (n != 0)
						problem |= 1 << n - 1;
				}
				problems[i] = problem;
			}
			count = problemCount + 1;
			solve(0, 0, 0, 0);
			sb.append("Data Set ").append(tc).append(':');
			for (int j = 0; j < problemCount; j++)
				if ((res & 1 << j) > 0)
					sb.append(' ').append((char) (j + 65));
			sb.append("\n\n");
		}
		System.out.print(sb.toString());
	}
}