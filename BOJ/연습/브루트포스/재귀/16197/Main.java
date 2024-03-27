import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static int r, c;
	private static int[] c1 = { -1, -1 }, c2 = { -1, -1 };
	private static String[] board;
	private static int res = 11;

	private static void search() {
		if (c1[0] )
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int j;

		board = br.readLine().split(" ");
		r = Integer.parseInt(board[0]);
		c = Integer.parseInt(board[1]);
		board = new String[r];
		for (int i = 0; i < board.length; i++) {
			board[i] = br.readLine();
			if ((j = board[i].indexOf('o')) != -1) {
				if (c1[0] == -1 && c1[1] == -1) {
					c1[0] = i;
					c1[1] = j;
				} else {
					c2[0] = i;
					c2[1] = j;
				}
			}
		}
		search()
		br.close();
		if (res > 10)
			bw.append(String.valueOf(-1)).append('\n');
		else
			bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		bw.close();
	}
}