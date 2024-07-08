import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n, m;
	private static int[][] map, res;
	private static List<int[]> zeros = new ArrayList<>();
	private static Deque<int[]> ones = new ArrayDeque<>();
	private static boolean[][] visited;
	private static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int[] coord) {
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur;
		int nx, ny, count = 0;

		q.addLast(coord);
		visited[coord[0]][coord[1]] = true;
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			// 연결된 0인 칸들의 수 세기
			count++;
			for (int i = 0; i < 4; i++) {
				nx = cur[0] + dx[i];
				ny = cur[1] + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny])
					continue;
				if (map[nx][ny] == 0) {
					visited[nx][ny] = true;
					q.addLast(new int[] { nx, ny });
				} else {
					// 여기서 방문 처리한 1들은 해당 칸이 0이 됐을 때 방문할 수 있는 0인 칸들의 수를 더해줄 때 다시 방문하지 않음으로 처리해줘야 한다.
					visited[nx][ny] = true;
					ones.addLast(new int[] { nx, ny });
				}
			}
		}
		while (!ones.isEmpty()) {
			// cur, nx, ny 재활용
			cur = ones.removeFirst();
			nx = cur[0];
			ny = cur[1];
			res[nx][ny] += count;
			// 1인 칸들을 다시 방문하지 않음 처리를 해줘서 다음 BFS에서 다시 방문할 수 있게 처리
			visited[nx][ny] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		n = readInt();
		m = readInt();
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				if ((map[i][j] = System.in.read() & 15) == 0)
					zeros.add(new int[] { i, j });
			System.in.read();
		}
		res = new int[n][m];
		visited = new boolean[n][m];
		for (int[] zero : zeros)
			if (!visited[zero[0]][zero[1]])
				solve(zero);
		// 1 x 1 행렬이 주어지고 그 칸의 값이 벽이 있는 1이면, BFS 처리 후에도 res의 그 칸 값은 0이므로 0을 출력하게 된다.
		// 따라서 아래와 같이 res를 기준으로 정하면 안되고 map에서 애초에 1인 칸이었는지를 확인해야 한다.
		// for (int[] arr : res) {
		// for (int each : arr) {
		// if (each == 0)
		// sb.append(each);
		// else
		// sb.append(each + 1);
		// }
		// sb.append('\n');
		// }
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0)
					sb.append(0);
				else
					sb.append((res[i][j] + 1) % 10);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}