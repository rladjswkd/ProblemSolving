import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n, res;
	private static int[][] plain;
	// 두 번째 'B'와 'E'의 행 인덱스, 열 인덱스, 가로 및 세로 정보(가로면 0, 세로면 1)
	private static int[] begin, end;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[n][n][2];
		int[] cur;
		int nx, ny, size;

		q.addLast(begin);
		visited[begin[0]][begin[1]][begin[2]] = true;
		res = 0;
		while (!q.isEmpty() && !visited[end[0]][end[1]][end[2]]) {
			size = q.size();
			while (size-- > 0 && !visited[end[0]][end[1]][end[2]]) {
				cur = q.removeFirst();
				nx = cur[0];
				ny = cur[1];
				// 가로 상태로 이동할 땐 행은 [0, n - 1], 열은 [1, n - 2] 범위에서 이동 가능
				if (cur[2] == 0) {
					// 위로 이동: 위의 가로 세 칸이 1이 아니어야 한다.
					nx--;
					if (0 <= nx && nx < n && plain[nx][ny - 1] != '1' && plain[nx][ny] != '1' && plain[nx][ny + 1] != '1'
							&& !visited[nx][ny][0]) {
						visited[nx][ny][0] = true;
						q.addLast(new int[] { nx, ny, 0 });
					}
					// 아래로 이동: 아래 가로 세 칸이 1이 아니어야 한다.
					nx += 2;
					if (0 <= nx && nx < n && plain[nx][ny - 1] != '1' && plain[nx][ny] != '1' && plain[nx][ny + 1] != '1'
							&& !visited[nx][ny][0]) {
						visited[nx][ny][0] = true;
						q.addLast(new int[] { nx, ny, 0 });
					}
					// 왼쪽으로 이동: nx행 ny - 1열이 1이 아니어야 한다.
					nx--;
					ny--;
					if (1 <= ny && ny < n - 1 && plain[nx][ny - 1] != '1' && !visited[nx][ny][0]) {
						visited[nx][ny][0] = true;
						q.addLast(new int[] { nx, ny, 0 });
					}
					// 오른쪽으로 이동: nx행 ny + 1열이 1이 아니어야 한다.
					ny += 2;
					if (1 <= ny && ny < n - 1 && plain[nx][ny + 1] != '1' && !visited[nx][ny][0]) {
						visited[nx][ny][0] = true;
						q.addLast(new int[] { nx, ny, 0 });
					}
					// 회전: 현재 칸이 맨 윗행 또는 맨 아랫행이 아니어야 하며, 위의 가로 세 칸과 아래의 가로 세 칸이 1이 아니어야 한다.
					ny--;
					if (1 <= nx && nx < n - 1 && !visited[nx][ny][1]
							&& plain[nx - 1][ny - 1] != '1' && plain[nx - 1][ny] != '1' && plain[nx - 1][ny + 1] != '1'
							&& plain[nx + 1][ny - 1] != '1' && plain[nx + 1][ny] != '1' && plain[nx + 1][ny + 1] != '1') {
						visited[nx][ny][1] = true;
						q.addLast(new int[] { nx, ny, 1 });
					}
				}
				// 세로 상태로 이동할 땐 행은 [1, n - 2], 열은 [0, n - 1] 범위에서 이동 가능
				else {
					// 위로 이동: nx - 1행 ny 열이 1이 아니어야 한다.
					nx--;
					if (1 <= nx && nx < n - 1 && plain[nx - 1][ny] != '1' && !visited[nx][ny][1]) {
						visited[nx][ny][1] = true;
						q.addLast(new int[] { nx, ny, 1 });
					}
					// 아래로 이동: nx + 1행 ny열이 1이 아니어야 한다.
					nx += 2;
					if (1 <= nx && nx < n - 1 && plain[nx + 1][ny] != '1' && !visited[nx][ny][1]) {
						visited[nx][ny][1] = true;
						q.addLast(new int[] { nx, ny, 1 });
					}
					// 왼쪽으로 이동: 왼쪽 세로 세 칸이 1이 아니어야 한다.
					nx--;
					ny--;
					// nx - 1, nx, nx + 1은 애초에 모두 유효한 범위의 행 인덱스다.
					if (0 <= ny && ny < n && plain[nx - 1][ny] != '1' && plain[nx][ny] != '1' && plain[nx + 1][ny] != '1'
							&& !visited[nx][ny][1]) {
						visited[nx][ny][1] = true;
						q.addLast(new int[] { nx, ny, 1 });
					}
					// 오른쪽으로 이동: 오른쪽 세로 세 칸이 1이 아니어야 한다.
					ny += 2;
					if (0 <= ny && ny < n && plain[nx - 1][ny] != '1' && plain[nx][ny] != '1' && plain[nx + 1][ny] != '1'
							&& !visited[nx][ny][1]) {
						visited[nx][ny][1] = true;
						q.addLast(new int[] { nx, ny, 1 });
					}
					// 회전: 현재 칸이 맨 왼쪽 행 또는 맨 오른쪽 행이 아니어야 하며, 왼쪽의 세로 세 칸과 오른쪽의 세로 세 칸이 1이 아니어야 한다.
					ny--;
					if (1 <= ny && ny < n - 1 && !visited[nx][ny][0]
							&& plain[nx - 1][ny - 1] != '1' && plain[nx][ny - 1] != '1' && plain[nx + 1][ny - 1] != '1'
							&& plain[nx - 1][ny + 1] != '1' && plain[nx][ny + 1] != '1' && plain[nx + 1][ny + 1] != '1') {
						visited[nx][ny][0] = true;
						q.addLast(new int[] { nx, ny, 0 });
					}
				}
			}
			res++;
		}
		if (!visited[end[0]][end[1]][end[2]])
			res = 0;
	}

	public static void main(String[] args) throws IOException {
		int bx = -1, by = -1, ex = -1, ey = -1, ch;

		plain = new int[n = readInt()][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				plain[i][j] = ch = System.in.read();
				if (bx == -1 && by == -1 && ch == 'B') {
					bx = i;
					by = j;
				}
				if (ex == -1 && ey == -1 && ch == 'E') {
					ex = i;
					ey = j;
				}
			}
			System.in.read();
		}
		// bx, by, ex, ey는 연속하는 세 칸의 B와 E중 가장 왼쪽의 가장 위 칸의 인덱스를 저장한다.
		begin = by < n - 2 && plain[bx][by + 1] == 'B' ? new int[] { bx, by + 1, 0 } : new int[] { bx + 1, by, 1 };
		end = ey < n - 2 && plain[ex][ey + 1] == 'E' ? new int[] { ex, ey + 1, 0 } : new int[] { ex + 1, ey, 1 };
		solve();
		System.out.println(res);
	}
}