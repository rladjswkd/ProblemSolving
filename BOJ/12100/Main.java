import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

// 백트래킹
// 참고, 한 행이 0 4 2 2 일 때 오른쪽으로 한 번 이동시키면 그 결과는 0 0 0 8이 아닌 0 0 4 4다.

// solve 함수를 호출할 때마다 새로운 newBoard를 생성해서 깊은 복사를 한 후, 연산을 수행하고 한 단계 더 재귀 호출을 수행하는
// 방식으로 구현했었는데, 이것보단 연산을 저장한 후, cnt가 5가 됐을 때 새로운 배열을 생성하고 저장해놓은 연산을 순서대로 적용하는 것이
// 훨씬 효율적이다.

// 이상하다. 백준 기준으로 매번 newBoard를 새로 생성하는 코드가 168ms, 한 번에 연산하는 코드가 236ms가 나온다.
// 심지어 백준 기준 메모리 사용량도 19488KB, 21096KB로 매번 newBoard를 새로 생성하는 코드가 더 적다.

// 매번 newBoard를 새로 생성하는 코드
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int n, res = Integer.MIN_VALUE;

	private static int readInt() throws IOException {
		int res = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			res = (res << 3) + (res << 1) + (cur & 15);
		return res;
	}

	/*
	 * dir : 0, 1, 2, 3의 값을 가지며 순서대로 상, 하, 좌, 우로 이동함을 나타낸다.
	 * cnt : 이동시킨 횟수
	 */
	private static void solve(int[][] board, int dir, int cnt) {
		int index;
		int[] indices; // indices 배열을 활용하면 board 배열을 순차적으로 읽을 수 있을 때 index 대신 사용한다.
		int[][] newBoard = new int[n][n];
		// 현재 dir에 맞게 이동시키는 연산
		switch (dir) {
			case 0:
				indices = new int[n];
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (board[i][j] == 0)
							continue;
						// newBoard의 현재 인덱스에 값이 없을 때
						if (newBoard[indices[j]][j] == 0)
							newBoard[indices[j]][j] = board[i][j];
						// newBoard의 현재 인덱스에 값이 있고, 이동해 올 board[i][j]와 값이 같을 때
						else if (newBoard[indices[j]][j] == board[i][j])
							newBoard[indices[j]++][j] <<= 1;
						// newBoard의 현재 인덱스에 값이 있고, 이동해 올 board[i][j]와 값이 다를 때
						else
							newBoard[++indices[j]][j] = board[i][j];
					}
				}
				break;
			case 1:
				indices = new int[n];
				for (int j = 0; j < n; j++)
					indices[j] = n - 1;
				for (int i = n - 1; i >= 0; i--) {
					for (int j = 0; j < n; j++) {
						if (board[i][j] == 0)
							continue;
						if (newBoard[indices[j]][j] == 0)
							newBoard[indices[j]][j] = board[i][j];
						else if (newBoard[indices[j]][j] == board[i][j])
							newBoard[indices[j]--][j] <<= 1;
						else
							newBoard[--indices[j]][j] = board[i][j];
					}
				}
				break;
			case 2:
				for (int i = 0; i < n; i++) {
					index = 0;
					for (int j = 0; j < n; j++) {
						if (board[i][j] == 0)
							continue;
						if (newBoard[i][index] == 0)
							newBoard[i][index] = board[i][j];
						else if (newBoard[i][index] == board[i][j])
							newBoard[i][index++] <<= 1;
						else
							newBoard[i][++index] = board[i][j];
					}
				}
				break;
			case 3:
				for (int i = 0; i < n; i++) {
					index = n - 1;
					for (int j = n - 1; j >= 0; j--) {
						if (board[i][j] == 0)
							continue;
						if (newBoard[i][index] == 0)
							newBoard[i][index] = board[i][j];
						else if (newBoard[i][index] == board[i][j])
							newBoard[i][index--] <<= 1;
						else
							newBoard[i][--index] = board[i][j];
					}
				}
				break;
		}
		// 이동시킨 횟수가 5회면 최댓값을 찾고 필요하면 res를 업데이트한 후, 반환.
		if (cnt == 5) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (res < newBoard[i][j])
						res = newBoard[i][j];
			return;
		}
		// 다음 dir로 이동시키는 연산
		for (int i = 0; i < 4; i++)
			solve(newBoard, i, cnt + 1);
	}

	public static void main(String[] args) throws IOException {
		int[][] board;

		n = readInt();
		board = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				board[i][j] = readInt();
		for (int i = 0; i < 4; i++)
			solve(board, i, 1);
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}

// 연산을 모아서 한 번에 하는 코드
// public class Main {
// private static BufferedReader br = new BufferedReader(new
// InputStreamReader(System.in));
// private static BufferedWriter bw = new BufferedWriter(new
// OutputStreamWriter(System.out));
// private static int n, res = Integer.MIN_VALUE;
// private static int[] operations = new int[5];
// private static int[][] board;

// private static int readInt() throws IOException {
// int res = 0, cur;

// while (48 <= (cur = br.read()) && cur <= 57)
// res = (res << 3) + (res << 1) + (cur & 15);
// return res;
// }

// private static void operate(int[][] newBoard, int dir) {
// int index, value;
// int[] indices;

// switch (dir) {
// case 0:
// indices = new int[n];
// for (int i = 1; i < n; i++) {
// for (int j = 0; j < n; j++) {
// // board[i][j]가 0일 때(비어있을 때)는 넘긴다.
// if (newBoard[i][j] == 0)
// continue;
// // board[i][j]를 옮겼으므로, 기존의 board[i][j]는 0으로 설정한다.
// value = newBoard[i][j];
// newBoard[i][j] = 0;
// // board[i][j]가 0이 아니고(비어있지 않고), board[indices[j]][j]가 0일 때는
// // board[indices[j]][j]를 board[i][j]로 설정하고 indices[j]는 그대로 둔다.
// // 추후 같은 값이 밀려오면 더해야 하기 때문이다.
// if (newBoard[indices[j]][j] == 0)
// newBoard[indices[j]][j] = value;
// // board[i][j]가 0이 아니고, board[indices[j]][j]와 그 값이 같을 때는
// // board[indices[j]][j]를 두 배로 키우고 indices[j]는 증가시킨다.
// // 1번째 인덱스의 행부터 시작하므로 indices[j] == i인 상황은 발생하지 않는다.
// // indices[j]는 1만큼 증가하거나 증가하지 않고, i는 항상 증가하기 때문이다.
// else if (newBoard[indices[j]][j] == value)
// newBoard[indices[j]++][j] <<= 1;
// // board[i][j]가 0이 아니고, board[indices[j]][j]와 그 값이 다를 때는
// // indices[j]를 1만큼 증가시킨 후 board[indices[j]][j]를 board[i][j]로 설정한다.
// else
// newBoard[++indices[j]][j] = value;
// }
// }
// break;
// case 1:
// indices = new int[n];
// for (int j = 0; j < n; j++)
// indices[j] = n - 1;
// for (int i = n - 2; i >= 0; i--) {
// for (int j = 0; j < n; j++) {
// if (newBoard[i][j] == 0)
// continue;
// value = newBoard[i][j];
// newBoard[i][j] = 0;
// if (newBoard[indices[j]][j] == 0)
// newBoard[indices[j]][j] = value;
// else if (newBoard[indices[j]][j] == value)
// newBoard[indices[j]--][j] <<= 1;
// else
// newBoard[--indices[j]][j] = value;
// }
// }
// break;
// case 2:
// for (int i = 0; i < n; i++) {
// index = 0;
// for (int j = 1; j < n; j++) {
// if (newBoard[i][j] == 0)
// continue;
// value = newBoard[i][j];
// newBoard[i][j] = 0;
// if (newBoard[i][index] == 0)
// newBoard[i][index] = value;
// else if (newBoard[i][index] == value)
// newBoard[i][index++] <<= 1;
// else
// newBoard[i][++index] = value;
// }
// }
// break;
// case 3:
// for (int i = 0; i < n; i++) {
// index = n - 1;
// for (int j = n - 2; j >= 0; j--) {
// if (newBoard[i][j] == 0)
// continue;
// value = newBoard[i][j];
// newBoard[i][j] = 0;
// if (newBoard[i][index] == 0)
// newBoard[i][index] = value;
// else if (newBoard[i][index] == value)
// newBoard[i][index--] <<= 1;
// else
// newBoard[i][--index] = value;
// }
// }
// break;
// }
// }

// private static void solve(int cnt) {
// int[][] newBoard;

// if (cnt == 5) {
// newBoard = new int[n][n];
// for (int i = 0; i < n; i++)
// for (int j = 0; j < n; j++)
// newBoard[i][j] = board[i][j];
// for (int i = 0; i < operations.length; i++)
// operate(newBoard, operations[i]);
// for (int i = 0; i < n; i++)
// for (int j = 0; j < n; j++)
// if (res < newBoard[i][j])
// res = newBoard[i][j];
// return;
// }
// for (int i = 0; i < 4; i++) {
// operations[cnt] = i;
// solve(cnt + 1);
// }
// }

// public static void main(String[] args) throws IOException {
// n = readInt();
// board = new int[n][n];
// for (int i = 0; i < n; i++)
// for (int j = 0; j < n; j++)
// board[i][j] = readInt();
// for (int i = 0; i < 4; i++) {
// operations[0] = i;
// solve(1);
// }
// bw.append(String.valueOf(res)).append('\n');
// bw.flush();
// br.close();
// bw.close();
// }
// }