import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n;
	private static int[][] house, doors, dist;
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur;
		int sdx = doors[0][0], sdy = doors[0][1], edx = doors[1][0], edy = doors[1][1], nx, ny, ndir;

		dist = new int[n][n];
		// 0은 방문하지 않은 상태를 나타내고, 1부터 거울의 개수를 나타낸다. 1 => 0, 2 => 1, ..., n => n - 1
		dist[sdx][sdy] = 1;
		// 시작 문에서 상, 하, 좌, 우로 빛이 뻗어나간다.
		for (int i = 0; i < 4; i++) {
			nx = sdx + dx[i];
			ny = sdy + dy[i];
			// 이미 방문한 지점을 지나 뻗어나갈 수는 있으므로 유효한 범위의 칸을 모두 고려한다.
			while (0 <= nx && nx < n && 0 <= ny && ny < n) {
				// 현재 칸이 벽이라면 더이상 뻗어나가지 않는다.
				if (house[nx][ny] == '*')
					break;
				// 유효한 범위의 칸을 모두 고려하되, 이미 방문한 칸에서 다시 뻗어나가는 것을 고려하진 않는다.
				if (dist[nx][ny] == 0) {
					dist[nx][ny] = 1;
					if (house[nx][ny] == '!')
						// 세 번째 값은 진행 방향을 나타낸다.
						q.addLast(new int[] { nx, ny, i });
				}
				nx += dx[i];
				ny += dy[i];
			}
		}
		// 거울을 설치할 수 있는 위치들이 q에 들어가고, 이 위치들에 대해 진행 방향에서 좌, 우로 꺾은 방향으로 진행하며 반대편 문에 도달할
		// 때까지 과정을 반복한다.
		// 좌로 회전: 상->좌 하->우 좌->하 우->상 0->2 1->3 2->1 3->0
		// 우로 회전: 상->우 하->좌 좌->상 우->하 0->3 1->2 2->0 3->1
		while (!q.isEmpty() && dist[edx][edy] == 0) {
			cur = q.removeFirst();
			// 좌로 회전하는 거울 설치
			ndir = cur[2] <= 1 ? cur[2] + 2 : 3 - cur[2];
			// 직전
			nx = cur[0] + dx[ndir];
			ny = cur[1] + dy[ndir];
			while (0 <= nx && nx < n && 0 <= ny && ny < n) {
				if (house[nx][ny] == '*')
					break;
				if (dist[nx][ny] == 0) {
					dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
					if (house[nx][ny] == '!')
						q.addLast(new int[] { nx, ny, ndir });
				}
				nx += dx[ndir];
				ny += dy[ndir];
			}
			// 우로 회전하는 거울 설치
			ndir = cur[2] <= 1 ? 3 - cur[2] : cur[2] - 2;
			// 직전
			nx = cur[0] + dx[ndir];
			ny = cur[1] + dy[ndir];
			while (0 <= nx && nx < n && 0 <= ny && ny < n) {
				if (house[nx][ny] == '*')
					break;
				if (dist[nx][ny] == 0) {
					dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
					if (house[nx][ny] == '!')
						q.addLast(new int[] { nx, ny, ndir });
				}
				nx += dx[ndir];
				ny += dy[ndir];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int index = 0;

		house = new int[n = readInt()][n];
		doors = new int[2][];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if ((house[i][j] = System.in.read()) == '#')
					doors[index++] = new int[] { i, j };
			System.in.read();
		}
		solve();
		// dist에 담긴 값에서 1을 빼는 것이 실제 거울의 개수. 무조건 다른 문에 도달할 수 있으므로,
		// dist[door[1][0]][door[1][1]]의 값은 1 이상이다.
		System.out.println(dist[doors[1][0]][doors[1][1]] - 1);
	}
}