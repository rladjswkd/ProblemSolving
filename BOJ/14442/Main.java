
/*
 * BFS
 * 
 * 
 * 아래 방식도 생각했었지만, 문제가 있다.
 * dist를 [k][n][m] 차원으로 선언하지 않고 [n][m] 의 2차원으로 선언한 후, 거리가 아닌 부순 벽의 개수를 저장한다.
 * 현재 경로가 i 개의 벽을 부수며 한 지점에 도달했고, 그 전에 그 지점에 도달한 경로는 j 개의 벽을 부수며 이동했을 때, i < j면 dist를 업데이트하고 현재의 경로를 큐에 추가한다.
 * 
 * 문제는 부순 벽의 개수와 최단 경로가 상관이 없다는 것이다.
 * 벽을 많이 부수며 이동한 경로가 최단 경로일 수도 있고, 반대로 적게 부수며 이동한 경로가 최단 경로일 수 있다.
 * 
 */
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n, m, k;
	private static int[][] map;
	// int[0]: 0 ~ k 개의 벽을 부수며 해당 칸에 도달할 때의 거리, int[1]: 행 인덱스, int[2]: 열 인덱스:
	// -> k가 더 범위가 작은 수이므로 상위 차원으로 두는 것이 효율적이다.
	private static int[][][] dist;
	// int[0]: (i, j)까지 이동하며 부순 벽의 개수, int[1]: 행 인덱스, int[2]: 열 인덱스
	private static Deque<int[]> q = new ArrayDeque<>();
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] arr;
		int nx, ny, res = -1;

		n = readInt();
		m = readInt();
		k = readInt();
		map = new int[n][m];
		dist = new int[k + 1][n][m];
		for (int i = 0; i < n; i++) {
			arr = map[i];
			for (int j = 0; j < m; j++)
				arr[j] = System.in.read() & 15;
			// '\n', EOF
			System.in.read();
		}
		// BFS
		q.addLast(new int[] { 0, 0, 0 });
		dist[0][0][0] = 1;
		while (!q.isEmpty()) {
			// arr 변수 재활용
			arr = q.removeFirst();
			if (arr[1] == n - 1 && arr[2] == m - 1) {
				res = dist[arr[0]][arr[1]][arr[2]];
				break;
			}
			for (int i = 0; i < 4; i++) {
				nx = arr[1] + dx[i];
				ny = arr[2] + dy[i];
				if (nx < 0 || n <= nx || ny < 0 || m <= ny)
					continue;
				// 벽이 아니고 현재 경로와 같은 수의 벽을 부수며 (nx, ny)에 도달한 경로가 없을 때
				if (map[nx][ny] == 0 && dist[arr[0]][nx][ny] == 0) {
					q.addLast(new int[] { arr[0], nx, ny });
					dist[arr[0]][nx][ny] = dist[arr[0]][arr[1]][arr[2]] + 1;
				}
				// 벽이지만 지금까지 부순 벽의 수가 k개보다 작고, 현재 경로가 부순 벽의 수 + 1의 벽을 부수며 (nx, ny)에 도달한 경로가 없을 때
				else if (map[nx][ny] == 1 && arr[0] < k && dist[arr[0] + 1][nx][ny] == 0) {
					q.addLast(new int[] { arr[0] + 1, nx, ny });
					dist[arr[0] + 1][nx][ny] = dist[arr[0]][arr[1]][arr[2]] + 1;
				}
			}
		}
		System.out.println(res == 0 ? -1 : res);
	}
}