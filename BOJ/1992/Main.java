import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static char[][] board;
	private static StringBuilder sb;

	private static void solve(int xs, int xe, int ys, int ye) {
		int v = 0;

		for (int i = xs; i <= xe; i++)
			for (int j = ys; j <= ye; j++)
				v += board[i][j] - 48;
		if (v == (xe - xs + 1) * (ye - ys + 1))
			sb.append(1);
		else if (v == 0)
			sb.append(0);
		else {
			sb.append('(');
			solve(xs, (xs + xe) / 2, ys, (ys + ye) / 2);
			solve(xs, (xs + xe) / 2, (ys + ye) / 2 + 1, ye);
			solve((xs + xe) / 2 + 1, xe, ys, (ys + ye) / 2);
			solve((xs + xe) / 2 + 1, xe, (ys + ye) / 2 + 1, ye);
			sb.append(')');
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());

		board = new char[n][n];
		for (int i = 0; i < n; i++)
			board[i] = br.readLine().toCharArray();
		sb = new StringBuilder(n * n);
		solve(0, n - 1, 0, n - 1);
		bw.write(sb.append('\n').toString());
		bw.flush();
		br.close();
		bw.close();
	}
}