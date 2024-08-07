import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int h, w, res;
	private static int[][] factory;
	// 동, 서, 남, 북
	private static final int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int[] start, int endX, int endY, int endD) {
		Deque<int[]> q = new ArrayDeque<>();
		int[][] visited = new int[h][w];
		int[] cur;
		int size, x, y, dir, nx, ny, ndir;

		q.addLast(start);
		visited[start[0]][start[1]] |= 1 << start[2];
		while (!q.isEmpty() && (visited[endX][endY] & 1 << endD) == 0) {
			size = q.size();
			res++;
			while (size-- > 0) {
				cur = q.removeFirst();
				nx = x = cur[0];
				ny = y = cur[1];
				dir = cur[2];
				// 현재 칸에서 왼쪽 방향으로 회전하기. 동->북 서->남 남->동 북->서. 0->3 1->2 2->0 3->1
				ndir = dir <= 1 ? 3 - dir : dir - 2;
				if ((visited[x][y] & 1 << ndir) == 0) {
					visited[x][y] |= 1 << ndir;
					q.addLast(new int[] { x, y, ndir });
				}
				// 현재 칸에서 오른쪽 방향으로 회전하기. 동->남 서->북 남->서 북->동. 0->2 1->3 2->1 3->0
				ndir = dir <= 1 ? dir + 2 : 3 - dir;
				if ((visited[x][y] & 1 << ndir) == 0) {
					visited[x][y] |= 1 << ndir;
					q.addLast(new int[] { x, y, ndir });
				}
				// 현재 칸과 방향에 대해 1, 2, 3칸 이동하기
				// nx와 ny는 각각 x, y로 초기화되어있고, k가 1, 2, 3으로 증가하면서 dx[dir], dy[dir]를 계속 더해주면 된다.
				for (int k = 1; k <= 3; k++) {
					nx += dx[dir];
					ny += dy[dir];
					// 현재 칸이 유효한 범위의 칸이 아니거나 유효하더라도 궤도가 설치되지 않은 칸이라면 직선으로 더이상 진행할 수 없다.
					if (nx < 0 || h <= nx || ny < 0 || w <= ny || factory[nx][ny] == 1)
						break;
					if ((visited[nx][ny] & 1 << dir) > 0)
						continue;
					visited[nx][ny] |= 1 << dir;
					q.addLast(new int[] { nx, ny, dir });
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		factory = new int[h = readInt()][w = readInt()];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				factory[i][j] = System.in.read() & 15;
				System.in.read();
			}
		}
		res = 0;
		solve(new int[] { readInt() - 1, readInt() - 1, readInt() - 1 },
				readInt() - 1, readInt() - 1, readInt() - 1);
		System.out.println(res);
	}
}