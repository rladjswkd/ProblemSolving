import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int w, h;
	private static int[][] map, dist;
	// 상, 하, 좌, 우
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	// private static void printDist() {
	// System.out.println();
	// for (int i = 0; i < h; i++) {
	// for (int j = 0; j < w; j++)
	// System.out.printf("%d ", dist[i][j]);
	// System.out.println();
	// }
	// }

	public static void main(String[] args) throws IOException {
		int nx, ny, ndir, startX, startY, endX, endY;
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur;

		w = readInt();
		map = new int[h = readInt()][w];
		dist = new int[h][w];
		startX = startY = endX = endY = -1;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if ((map[i][j] = System.in.read()) == 'C') {
					if (startX == -1) {
						startX = i;
						startY = j;
					} else {
						endX = i;
						endY = j;
					}
				}
			}
			System.in.read();
		}
		// 레이저 시작 위치를 거울 0개로 방문할 수 있는 칸으로 표시
		dist[startX][startY] = 1;
		// 레이저 시작 위치에서 상, 하, 좌, 우로 뻗어나가는 칸들을 큐에 추가
		for (int i = 0; i < 4; i++) {
			nx = startX + dx[i];
			ny = startY + dy[i];
			while (0 <= nx && nx < h && 0 <= ny && ny < w && map[nx][ny] != '*') {
				dist[nx][ny] = 1;
				q.addLast(new int[] { nx, ny, i });
				nx += dx[i];
				ny += dy[i];
			}
		}
		// printDist();
		while (!q.isEmpty() && dist[endX][endY] == 0) {
			cur = q.removeFirst();
			// 현재 방향에 대해 좌, 우로 90도 회전한 방향으로 뻗어나가는 칸들을 현재 거울 개수 + 1개로 방문할 수 있다고 표시
			// 좌로 90도: 상-좌 하-우 좌-하 우-상 0-2 1-3 2-1 3-0
			ndir = cur[2] <= 1 ? cur[2] + 2 : 3 - cur[2];
			nx = cur[0] + dx[ndir];
			ny = cur[1] + dy[ndir];
			// while (0 <= nx && nx < h && 0 <= ny && ny < w && dist[nx][ny] == 0 &&
			// map[nx][ny] != '*') {
			//
			while (0 <= nx && nx < h && 0 <= ny && ny < w && (dist[nx][ny] == 0 || dist[nx][ny] >= dist[cur[0]][cur[1]] + 1)
					&& map[nx][ny] != '*') {
				if (dist[nx][ny] == 0) {
					dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
					q.addLast(new int[] { nx, ny, ndir });
				}
				nx += dx[ndir];
				ny += dy[ndir];
			}
			// 우로 90도: 상-우 하-좌 좌-상 우-하 0-3 1-2 2-0 3-1
			ndir = cur[2] <= 1 ? 3 - cur[2] : cur[2] - 2;
			nx = cur[0] + dx[ndir];
			ny = cur[1] + dy[ndir];
			// while (0 <= nx && nx < h && 0 <= ny && ny < w && dist[nx][ny] == 0 &&
			// map[nx][ny] != '*') {
			// 다른 경로로 인해 현재 레이저가 진행할 칸이 막혀있더라도 그 dist값이 현재 레이저의 dist값보다 크거나 같다면 레이저는 진행할 수
			// 있어야 한다.
			while (0 <= nx && nx < h && 0 <= ny && ny < w && (dist[nx][ny] == 0 || dist[nx][ny] >= dist[cur[0]][cur[1]] + 1)
					&& map[nx][ny] != '*') {
				if (dist[nx][ny] == 0) {
					dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
					q.addLast(new int[] { nx, ny, ndir });
				}
				nx += dx[ndir];
				ny += dy[ndir];
			}
			// printDist();
		}
		System.out.println(dist[endX][endY] - 1);
	}
}