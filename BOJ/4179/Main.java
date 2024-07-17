import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int h, w, res = 0;
	private static int[][] maze;
	// 불이 확산한 칸이든 지훈이가 방문한 칸이든, 지훈이가 다시 방문할 수 없거나 필요가 없기 때문에 하나의 배열로 처리할 수 있다.
	private static boolean[][] visited;
	private static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	private static Deque<int[]> jhQ = new ArrayDeque<>(), fireQ = new ArrayDeque<>();

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		int[] cur;
		int nx, ny, size;

		// 지훈이의 이동 가능 여부를 아직 판단하지 않은 칸이 큐에 남아있으면 계속 반복
		while (!jhQ.isEmpty()) {
			res++;
			// 불 확산
			if (!fireQ.isEmpty()) {
				size = fireQ.size();
				while (size-- > 0) {
					cur = fireQ.removeFirst();
					for (int i = 0; i < 4; i++) {
						nx = cur[0] + dx[i];
						ny = cur[1] + dy[i];
						// 불은 지훈이가 있던 자리든 빈 자리든 벽만 아니면 모두 이동 가능
						if (0 <= nx && nx < h && 0 <= ny && ny < w && !visited[nx][ny] && maze[nx][ny] != '#') {
							fireQ.addLast(new int[] { nx, ny });
							visited[nx][ny] = true;
						}
					}
				}
			}
			// 지훈 이동 -> 이미 가장 바깥의 while문 조건식에서 jhQ는 비어있지 않음을 확인.
			size = jhQ.size();
			while (size-- > 0) {
				cur = jhQ.removeFirst();
				// 현재 칸이 미로의 가장자리라면, 다음 턴에 바로 탈출
				// res에는 시작점부터 cur까지가 반영되어있으므로, res를 그대로 출력하면 된다.
				if (cur[0] == 0 || cur[0] == h - 1 || cur[1] == 0 || cur[1] == w - 1)
					return;
				for (int i = 0; i < 4; i++) {
					nx = cur[0] + dx[i];
					ny = cur[1] + dy[i];
					// 지훈이는 빈 칸으로만 이동할 수 있다.(이미 방문한 칸은 방문할 필요가 없다.)
					if (0 <= nx && nx < h && 0 <= ny && ny < w && !visited[nx][ny] && maze[nx][ny] == '.') {
						jhQ.addLast(new int[] { nx, ny });
						visited[nx][ny] = true;
					}
				}
			}
		}
		// 지훈이가 탈출하지 못했을 때
		res = -1;
	}

	public static void main(String[] args) throws IOException {
		int cur;

		maze = new int[h = readInt()][w = readInt()];
		visited = new boolean[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				maze[i][j] = cur = System.in.read();
				if (cur == 'J') {
					jhQ.addLast(new int[] { i, j });
					visited[i][j] = true;
				} else if (cur == 'F') {
					fireQ.addLast(new int[] { i, j });
					visited[i][j] = true;
				}
			}
			System.in.read();
		}
		solve();
		if (res == -1)
			System.out.println("IMPOSSIBLE");
		else
			System.out.println(res);
	}
}