import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static int h, w, res = Integer.MAX_VALUE;
	private static String[] board;
	private static int[] dy = { 0, 0, -1, 1 }, dx = { -1, 1, 0, 0 };

	private static boolean checkOneCoinOut(int c1x, int c1y, int c2x, int c2y) {
		return (c1x == h - 1 && c2x != h - 1)
				|| (c1x == 0 && c2x != 0)
				|| (c1y == w - 1 && c2y != w - 1)
				|| (c1y == 0 && c2y != 0);
	}

	private static boolean checkValidity(int cx, int cy) {
		return (0 <= cx && cx < h) && (0 <= cy && cy < w);
	}

	private static void search(int c1x, int c1y, int c2x, int c2y, int cnt) {
		int new1x, new1y, new2x, new2y;

		if (cnt > 10 || cnt >= res)
			return;
		if (checkOneCoinOut(c1x, c1y, c2x, c2y) || checkOneCoinOut(c2x, c2y, c1x, c1y)) {
			res = Math.min(res, cnt + 1);
			return;
		}
		for (int i = 0; i < 4; i++) {
			new1x = c1x + dy[i];
			new1y = c1y + dx[i];
			new2x = c2x + dy[i];
			new2y = c2y + dx[i];
			if (!checkValidity(new1x, new1y) || !checkValidity(new2x, new2y)
					|| (board[new1x].charAt(new1y) == '#' && board[new2x].charAt(new2y) == '#'))
				continue;
			if (board[new1x].charAt(new1y) == '#') {
				new1x = c1x;
				new1y = c1y;
			}
			if (board[new2x].charAt(new2y) == '#') {
				new2x = c2x;
				new2y = c2y;
			}
			search(new1x, new1y, new2x, new2y, cnt + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int c1x = -1, c1y = -1, c2x = -1, c2y = -1;

		board = br.readLine().split(" ");
		h = Integer.parseInt(board[0]);
		w = Integer.parseInt(board[1]);
		board = new String[h];
		for (int i = 0; i < board.length; i++)
			board[i] = br.readLine();
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length(); j++) {
				if (board[i].charAt(j) == 'o') {
					if (c1x == -1) {
						c1x = i;
						c1y = j;
					} else {
						c2x = i;
						c2y = j;
					}
				}
			}
		search(c1x, c1y, c2x, c2y, 0);
		br.close();
		if (res > 10)
			bw.append(String.valueOf(-1)).append('\n');
		else
			bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		bw.close();

	}
}