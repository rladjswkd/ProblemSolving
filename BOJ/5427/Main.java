
/*
 * BFS
 * 
 * 불, 상근이 별도의 큐 -> 불 먼저, 상근이 나중
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	private static StringBuilder sb = new StringBuilder();

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}
	// visit 체크를 할 필요 없을 것 같다?
	// 불은 현재 이동할 칸이 '.' 또는 '@'이면 이동시키고 아니면 이동시키지 않는다. 이동한 칸의 값을 '*'로 바꾼다.
	// 상근이는 현재 이동할 칸이 '.'일 때만 이동시킨다. 이동한 칸의 값을 '@'로 바꾼다.

	private static void solve(char[][] board, Deque<int[]> q, int h, int w) {
		int fireCount = q.size() - 1, personCount = 1, newCount, nx, ny, dist = 0;
		int[] cur;

		while (personCount > 0) {
			dist++;
			newCount = 0;
			while (fireCount-- > 0) {
				cur = q.removeFirst();
				for (int i = 0; i < 4; i++) {
					nx = cur[0] + dx[i];
					ny = cur[1] + dy[i];
					if (0 <= nx && nx < h && 0 <= ny && ny < w && (board[nx][ny] == '.' || board[nx][ny] == '@')) {
						newCount++;
						q.addLast(new int[] { nx, ny });
						board[nx][ny] = '*';
					}
				}
			}
			fireCount = newCount;
			newCount = 0;
			while (personCount-- > 0) {
				cur = q.removeFirst();
				for (int i = 0; i < 4; i++) {
					nx = cur[0] + dx[i];
					ny = cur[1] + dy[i];
					if (0 <= nx && nx < h && 0 <= ny && ny < w && board[nx][ny] == '.') {
						newCount++;
						q.addLast(new int[] { nx, ny });
						board[nx][ny] = '@';
					} else if (nx < 0 || h <= nx || ny < 0 || w <= ny) {
						sb.append(dist).append('\n');
						return;
					}
				}
			}
			personCount = newCount;
		}
		sb.append("IMPOSSIBLE\n");
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), w;
		char[][] board;
		Deque<int[]> q;
		int[] start = null;

		while (t-- > 0) {
			w = readInt();
			board = new char[readInt()][w];
			q = new ArrayDeque<>();
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < w; j++) {
					board[i][j] = (char) br.read();
					if (board[i][j] == '*')
						q.addLast(new int[] { i, j });
					else if (board[i][j] == '@')
						start = new int[] { i, j };
				}
				br.read();
			}
			q.addLast(start);
			solve(board, q, board.length, board[0].length);
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}