import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	// 0, 1, 2, 3: 0번 보드가 0, 90, 180, 270도 회전한 보드
	// 4, 5, 6, 7: 1번 보드가 0, 90, 180, 270도 회전한 보드
	// 8, 9, 10, 11: 2번 보드가 0, 90, 180, 270도 회전한 보드
	// 12, 13, 14, 15: 3번 보드가 0, 90, 180, 270도 회전한 보드
	// 16, 17, 18, 19: 4번 보드가 0, 90, 180, 270도 회전한 보드
	private static boolean[][][] boards, maze;
	private static int[] selected, stacked;
	private static boolean[][][][][] processed;
	private static int res;
	// 위, 아래, 앞, 뒤, 왼쪽, 오른쪽
	private static final int[] dz = { -1, 1, 0, 0, 0, 0 }, dx = { 0, 0, -1, 1, 0, 0 }, dy = { 0, 0, 0, 0, -1, 1 };

	private static void findShortestRoute(int sx, int sy, int ex, int ey) {
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur;
		boolean[][][] visited = new boolean[5][5][5];
		int nz, nx, ny, size, move = 0;

		q.addLast(new int[] { 0, sx, sy });
		visited[0][sx][sy] = true;
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				for (int i = 0; i < 6; i++) {
					nz = cur[0] + dz[i];
					nx = cur[1] + dx[i];
					ny = cur[2] + dy[i];
					if (0 <= nz && nz < 5 && 0 <= nx && nx < 5 && 0 <= ny && ny < 5 && maze[nz][nx][ny] && !visited[nz][nx][ny]) {
						if (nz == 4 && nx == ex && ny == ey) {
							res = Math.min(res, move + 1);
							return;
						}
						visited[nz][nx][ny] = true;
						q.addLast(new int[] { nz, nx, ny });
					}
				}
			}
			move++;
		}
	}

	private static void stackBoards(int count, int bits) {
		if (count == 5) {
			// 현재 상태가 이미 처리된 상태가 아닐때만 실행
			if (!processed[stacked[0]][stacked[1]][stacked[2]][stacked[3]][stacked[4]]) {
				// 미로 생성. 굳이 생성하지 않고 그냥 boards[selected[x]][i][j]로 대신해도 되지만, findShortestRoute
				// 함수 내에서 visited 배열을 처리하기 수월해진다. 그러지 않을 경우 visited가 boolean[20][5][5]여야 한다.
				for (int i = 0; i < 5; i++)
					maze[i] = boards[stacked[i]];
				// 입구 선택. 상판에 포함된 4개의 꼭지점 중에서만 선택해도 된다.
				if (maze[0][0][0] && maze[4][4][4])
					findShortestRoute(0, 0, 4, 4);
				if (maze[0][0][4] && maze[4][4][0])
					findShortestRoute(0, 4, 4, 0);
				if (maze[0][4][0] && maze[4][0][4])
					findShortestRoute(4, 0, 0, 4);
				if (maze[0][4][4] && maze[4][0][0])
					findShortestRoute(4, 4, 0, 0);
				// 현재 판이 쌓인 상태와 정확히 역순으로 쌓인 상태에 대해 방문 처리
				processed[stacked[0]][stacked[1]][stacked[2]][stacked[3]][stacked[4]] = true;
				processed[stacked[4]][stacked[3]][stacked[2]][stacked[1]][stacked[0]] = true;
			}
			return;
		}
		for (int i = 0; i < 5; i++) {
			if ((bits & 1 << i) == 0) {
				stacked[count] = selected[i];
				stackBoards(count + 1, bits | 1 << i);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int idx;

		boards = new boolean[20][5][5];
		// 회전을 고려한 모든 상태의 판을 생성
		for (int k = 0; k < 5; k++) {
			// k * 4 rotated에서의 시작 인덱스를 나타낸다.
			idx = k << 2;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (System.in.read() == '1')
						boards[idx][i][j] = boards[idx + 1][j][4
								- i] = boards[idx + 2][4 - i][4 - j] = boards[idx + 3][4 - j][i] = true;
					System.in.read();
				}
			}
		}
		// 각 그룹에서 하나씩 판을 선택하는 모든 경우를 확인
		// processed: 확인한 판들의 조합을 추적
		// selected: 선택한 판들의 조합을 추적
		// stacked: 선택한 판들을 쌓는 순서를 추적
		// maze: 선택하고 쌓은 판들로 구성된 미로를 추적
		processed = new boolean[20][20][20][20][20];
		selected = new int[5];
		stacked = new int[5];
		maze = new boolean[5][5][5];
		res = Integer.MAX_VALUE;
		for (int b1 = 0; b1 < 4; b1++) {
			selected[0] = b1;
			for (int b2 = 4; b2 < 8; b2++) {
				selected[1] = b2;
				for (int b3 = 8; b3 < 12; b3++) {
					selected[2] = b3;
					for (int b4 = 12; b4 < 16; b4++) {
						selected[3] = b4;
						for (int b5 = 16; b5 < 20; b5++) {
							selected[4] = b5;
							// 선택한 회전 판 조합이 이미 처리한 조합이 아닐 때만 선택한 5개의 판을 쌓는 경우 확인
							// 쌓는 순서에 대해선 여기서 고려할 필요가 없다.
							if (!processed[b1][b2][b3][b4][b5]) {
								// 회전에 대한 방문 처리
								for (int degree = 1; degree <= 3; degree++)
									processed[(b1 + degree) % 4][4 + (b2 - 4 + degree) % 4][8 + (b3 - 8 + degree) % 4][12
											+ (b4 - 12 + degree) % 4][16 + (b5 - 16 + degree) % 4] = true;
								stackBoards(0, 0);
							}
						}
					}
				}
			}
		}
		System.out.println(res == Integer.MAX_VALUE ? -1 : res);
	}
}