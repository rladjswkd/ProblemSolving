import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

	static void exchangeRight(char[][] board, int i, int j) {
		char e = board[i][j];

		board[i][j] = board[i][j + 1];
		board[i][j + 1] = e;
	}

	static void exchangeBelow(char[][] board, int i, int j) {
		char e = board[i][j];

		board[i][j] = board[i + 1][j];
		board[i + 1][j] = e;
	}

	static int checkRow(char[] row) {
		char ch = row[0];
		int res = 0, count = 1;

		for (int i = 1; i < row.length; i++) {
			if (ch != row[i]) {
				res = Math.max(res, count);
				ch = row[i];
				count = 1;
			} else {
				count++;
			}
		}
		res = Math.max(res, count);
		return res;
	}

	static int checkCol(char[][] board, int col) {
		char ch = board[0][col];
		int res = 0, count = 1;

		for (int i = 1; i < board[0].length; i++) { // 열의 길이여야 하지만 보드가 정사각형이므로 board[0].length로 대체
			if (ch != board[i][col]) {
				res = Math.max(res, count);
				ch = board[i][col];
				count = 1;
			} else {
				count++;
			}
		}
		res = Math.max(res, count);
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n, res = 0;
		char[][] board;

		n = Integer.parseInt(br.readLine());
		board = new char[n][n];
		for (int i = 0; i < n; i++) {
			board[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < n; i++) {
			res = Math.max(res, checkRow(board[i]));
			res = Math.max(res, checkCol(board, i));
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (j != n - 1 && board[i][j] != board[i][j + 1]) { // 오른쪽 원소와 교환
					exchangeRight(board, i, j);
					res = Math.max(res, checkRow(board[i]));
					res = Math.max(res, checkCol(board, j));
					res = Math.max(res, checkCol(board, j + 1));
					exchangeRight(board, i, j);
				}
				if (i != n - 1 && board[i][j] != board[i + 1][j]) { // 아래 원소와 교환
					exchangeBelow(board, i, j);
					res = Math.max(res, checkCol(board, j));
					res = Math.max(res, checkRow(board[i]));
					res = Math.max(res, checkRow(board[i + 1]));
					exchangeBelow(board, i, j);
				}
			}
		}
		System.out.println(res);
	}
}

/*
 * 1. 행 우선으로 모든 원소를 순서대로 순회하며 오른쪽, 아래 원소와만 교환한다.
 * 1-1. 교환하는 두 원소가 같으면 교환하지 않고 아래 과정을 생략한다.
 * 2. 오른쪽 원소와 교환할 땐 둘을 포함한 행, 둘 각각의 열들에 대해 연속하는 사탕을 확인한다.
 * 3. 아래 원소와 교한할 땐 둘을 포함한 열, 둘 각각의 행들에 대해 연속하는 사탕을 확인한다.
 * 
 */
