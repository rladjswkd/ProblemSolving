import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int[][] chessBoard, walls;
	private static int wallsLength;
	// 상, 하, 좌, 우, 좌상, 좌하, 우상, 우하, 제자리
	private static final int[] dx = { -1, 1, 0, 0, -1, 1, -1, 1, 0 }, dy = { 0, 0, -1, 1, -1, -1, 1, 1, 0 };

	public static void main(String[] args) throws IOException {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][] visited;
		int[] cur;
		int nx, ny, wallsIndex, size, res = 0;

		chessBoard = new int[8][8];
		walls = new int[64][];
		wallsLength = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((chessBoard[i][j] = System.in.read()) == '#')
					walls[wallsLength++] = new int[] { i, j };
			}
			System.in.read();
		}
		// 가장 왼쪽 아랫칸은 항상 벽이 아니다.
		q.addLast(new int[] { 7, 0 });
		while (!q.isEmpty()) {
			// 벽이 모두 사라진 후에, 캐릭터가 체스판 위에 살아서 존재한다면 우상단에 도달 가능한 것이므로 종료
			if (wallsLength == 0) {
				res = 1;
				break;
			}
			// 새로운 방문 여부 체크용 배열 초기화
			visited = new boolean[8][8];
			// 캐릭터 이동시키기
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				// 직전 단계에서 현재 칸에 캐릭터가 이동한 후 벽이 이동해 현재 칸이 벽이 되었다면 현재 칸은 버린다.
				if (chessBoard[cur[0]][cur[1]] == '#')
					continue;
				for (int i = 0; i < 9; i++) {
					nx = cur[0] + dx[i];
					ny = cur[1] + dy[i];
					if (0 <= nx && nx < 8 && 0 <= ny && ny < 8 && chessBoard[nx][ny] == '.' && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.addLast(new int[] { nx, ny });
					}
				}
			}
			// 벽 이동시키기.
			wallsIndex = 0;
			// 기존 벽이 있던 칸은 빈 칸으로 만들기
			// 이 과정을 별도로 수행하지 않고 아래 for문에서 함께 수행하면 연속한 행의 같은 열에 위치한 벽이 제거될 수 있다.
			// 예를 들어 chessBoard[0][1], chessBoard[1][1]이 벽이라고 할 때
			// 0행 1열을 지우고 1행 1열을 벽으로 만든 뒤, 1행 1열을 고려할 때 1행 1열의 벽을 지우게 된다.
			for (int i = 0; i < wallsLength; i++)
				chessBoard[walls[i][0]][walls[i][1]] = '.';
			// 벽이 이동할 위치가 유효한 위치라면 벽 이동 시키기
			for (int i = 0; i < wallsLength; i++) {
				if (walls[i][0] + 1 < 8) {
					walls[wallsIndex][0] = nx = walls[i][0] + 1;
					walls[wallsIndex][1] = ny = walls[i][1];
					chessBoard[nx][ny] = '#';
					wallsIndex++;
				}
			}
			wallsLength = wallsIndex;
			// // 벽 출력
			// System.out.println();
			// for (int[] i : chessBoard) {
			// for (int j : i)
			// System.out.print((char) j);
			// System.out.println();
			// }
		}
		System.out.println(res);
	}
}