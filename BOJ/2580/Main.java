import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[][] board = new int[9][9];
	private static boolean[][] usedX = new boolean[9][9], usedY = new boolean[9][9], usedSquare = new boolean[9][9];
	private static List<int[]> blanks = new ArrayList<>();

	private static boolean solve(int index) {
		int x, y;

		if (index == blanks.size())
			return true;
		x = blanks.get(index)[0];
		y = blanks.get(index)[1];
		for (int val = 0; val < 9; val++) {
			if (!usedX[x][val] && !usedY[y][val] && !usedSquare[3 * (x / 3) + y / 3][val]) {
				usedX[x][val] = usedY[y][val] = usedSquare[3 * (x / 3) + y / 3][val] = true;
				board[x][y] = val + 1;
				if (solve(index + 1))
					return true;
				else
					usedX[x][val] = usedY[y][val] = usedSquare[3 * (x / 3) + y / 3][val] = false;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = br.read() - 48;
				br.read();
				// 3 X 3 정사각형은 왼쪽 상단부터 오른쪽 방향으로 0, 1, 2 그 다음줄에서 3, 4, 5 마지막 줄에서 6, 7, 8과 같이
				// 적용한다. -> 3 * (i / 3) + j / 3
				if (board[i][j] == 0)
					blanks.add(new int[] { i, j });
				else
					usedX[i][board[i][j]
							- 1] = usedY[j][board[i][j] - 1] = usedSquare[3 * (i / 3) + j / 3][board[i][j] - 1] = true;
			}
		}
		// 아래와 같이 반복문으로 그냥 한 번만 순회하면 칸을 잘못 채운 후 진행했을 때 되돌릴 수 없다.
		/*
		 * 반례
		 * 0 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 0
		 * 0 0 0 0 0 0 0 0 0
		 * 출력
		 * 1 2 3 4 5 6 7 8 9
		 * 4 5 6 1 2 3 0 0 0
		 * 7 8 9 0 0 0 1 2 3
		 * 2 1 4 3 6 5 8 7 0
		 * 3 6 5 2 1 4 9 0 0
		 * 8 7 0 9 0 0 2 1 4
		 * 5 3 1 6 4 2 0 9 7
		 * 6 4 2 5 3 1 0 0 8
		 * 9 0 7 8 0 0 3 4 1
		 */
		// for (int i = 0; i < 9; i++) {
		// for (int j = 0; j < 9; j++) {
		// if (board[i][j] != 0)
		// continue;
		// for (int k = 0; k < 9; k++) {
		// if (!usedX[i][k] && !usedY[j][k] && !usedSquare[3 * (i / 3) + j / 3][k]) {
		// usedX[i][k] = usedY[j][k] = usedSquare[3 * (i / 3) + j / 3][k] = true;
		// board[i][j] = k + 1;
		// break;
		// }
		// }
		// }
		// }
		solve(0);
		for (int[] row : board) {
			sb.append(row[0]);
			for (int i = 1; i < 9; i++)
				sb.append(' ').append(row[i]);
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}