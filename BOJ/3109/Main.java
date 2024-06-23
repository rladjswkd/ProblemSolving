
/*
 * 가능한 일직선으로 뻗어나가는 것이 최대한 많은 파이프를 설치하는 방법이다.
 * -> 그리디
 * 
 * 처음과 마지막 열은 항상 비어있다.
 * 방문 확인을 하기 위한 불린 배열은 따로 선언하지 않고 board를 활용하자.
 * 
 * 마지막 열의 첫 번째 행 부터 최대한 많은 행에 파이프라인을 놓을 수 있게 하기 ->
 * 첫 번재 열의 출발 행 값이 작을수록 파이프라인을 가능한 오른쪽 상단을 향해 가도록 하는 것이
 * 그 다음 행에서 출발하는 파이프라인이 선택할 수 있는 칸이 많아진다.
 * 
 * 따라서 오른쪽 상단 -> 오른쪽 직선 -> 오른쪽 하단 순서로 파이프라인을 설치하길 시도한다.
 * 만약 앞의 순서에서 파이프라인 설치를 성공한다면 그 뒤는 시도하지 않아도 된다.(앞의 순서가 우선순위가 더 높으므로)
 * 
 * 0 ~ r - 1 행을 "작은 수의 행부터" 돌면서 0 ~ c - 1 열 중 "작은 수의 열부터" 파이프라인을 설치하기
 * 
 */
import java.io.IOException;

public class Main {
	private static int r, c, res = 0;
	private static char[][] board;
	// private static int[] dx = { -1, 0, 1 }, dy = { 1, 1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static boolean solve(int x, int y) {
		if (y == c) {
			res++;
			return true;
		}
		if (x == -1 || x == r || board[x][y] != '.')
			return false;
		board[x][y] = 'o';
		return solve(x - 1, y + 1) || solve(x, y + 1) || solve(x + 1, y + 1);
	}

	public static void main(String[] args) throws IOException {
		board = new char[(r = readInt())][(c = readInt())];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++)
				board[i][j] = (char) System.in.read();
			System.in.read();
		}
		for (int i = 0; i < r; i++)
			solve(i, 0);
		System.out.println(res);
	}
}