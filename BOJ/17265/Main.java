import java.io.IOException;

public class Main {
	private static int n;
	private static int[][] board;

	private static int cal(int operand1, int operand2, int operator) {
		int res;

		switch (operator) {
			case '*':
				res = operand1 * operand2;
				break;
			case '+':
				res = operand1 + operand2;
				break;
			case '-':
				res = operand1 - operand2;
				break;
			default:
				res = 0;
				break;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		int distance = (n = System.in.read() & 15) - 1 << 1, i, j;
		int[][] dpH, dpL;

		System.in.read();
		board = new int[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				board[i][j] = System.in.read();
				System.in.read();
			}
		}
		dpH = new int[n][n];
		dpL = new int[n][n];
		dpH[0][0] = dpL[0][0] = board[0][0] & 15;
		for (int d = 1; d < n; d++) {
			i = 0;
			j = d;
			if (board[i][j] < 48) {
				dpH[i][j] = dpH[i][j - 1];
				dpL[i][j] = dpL[i][j - 1];
			} else {
				dpH[i][j] = cal(dpH[i][j - 1], board[i][j] & 15, board[i][j - 1]);
				dpL[i][j] = cal(dpL[i][j - 1], board[i][j] & 15, board[i][j - 1]);
			}
			i++;
			j--;
			while (j > 0) {
				if (board[i][j] < 48) {
					dpH[i][j] = Math.max(dpH[i - 1][j], dpH[i][j - 1]);
					dpL[i][j] = Math.min(dpL[i - 1][j], dpL[i][j - 1]);
				} else {
					dpH[i][j] = Math.max(cal(dpH[i - 1][j], board[i][j] & 15, board[i - 1][j]),
							cal(dpH[i][j - 1], board[i][j] & 15, board[i][j - 1]));
					dpL[i][j] = Math.min(cal(dpL[i - 1][j], board[i][j] & 15, board[i - 1][j]),
							cal(dpL[i][j - 1], board[i][j] & 15, board[i][j - 1]));
				}
				i++;
				j--;
			}
			if (board[i][j] < 48) {
				dpH[i][j] = dpH[i - 1][j];
				dpL[i][j] = dpL[i - 1][j];
			} else {
				dpH[i][j] = cal(dpH[i - 1][j], board[i][j] & 15, board[i - 1][j]);
				dpL[i][j] = cal(dpL[i - 1][j], board[i][j] & 15, board[i - 1][j]);
			}
		}
		for (int d = n; d <= distance; d++) {
			i = d - n + 1;
			j = n - 1;
			while (j >= d - n + 1) {
				if (board[i][j] < 48) {
					dpH[i][j] = Math.max(dpH[i - 1][j], dpH[i][j - 1]);
					dpL[i][j] = Math.min(dpL[i - 1][j], dpL[i][j - 1]);
				} else {
					dpH[i][j] = Math.max(cal(dpH[i - 1][j], board[i][j] & 15, board[i - 1][j]),
							cal(dpH[i][j - 1], board[i][j] & 15, board[i][j - 1]));
					dpL[i][j] = Math.min(cal(dpL[i - 1][j], board[i][j] & 15, board[i - 1][j]),
							cal(dpL[i][j - 1], board[i][j] & 15, board[i][j - 1]));
				}
				i++;
				j--;
			}
		}
		System.out.println(new StringBuilder().append(dpH[n - 1][n - 1]).append(' ').append(dpL[n - 1][n - 1]));
	}
}