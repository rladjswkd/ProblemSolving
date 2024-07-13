import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int n, res = Integer.MAX_VALUE;
	private static int[][] map;
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	private static List<int[]> coasts = new ArrayList<>();

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void distinguish() {
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur;
		int nx, ny, isleCount = 0;
		boolean[][] visited = new boolean[n][n];
		boolean isCoast;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					q.addLast(new int[] { i, j });
					visited[i][j] = true;
					// 지도 상의 각 섬을 구별하기 위해 섬마다 값을 1, 2, 3, ... 과 같이 다르게 설정한다.
					// isleCount는 섬 하나를 완전히 탐색한 후 그 값이 1 증가한다.
					map[i][j] += isleCount;
					while (!q.isEmpty()) {
						cur = q.removeFirst();
						isCoast = false;
						for (int k = 0; k < 4; k++) {
							nx = cur[0] + dx[k];
							ny = cur[1] + dy[k];
							if (nx < 0 || n <= nx || ny < 0 || n <= ny || visited[nx][ny])
								continue;
							// map[nx][ny]가 map[x][y]와 같은 섬에 포함되는 육지일 때
							if (map[nx][ny] == 1) {
								q.addLast(new int[] { nx, ny });
								visited[nx][ny] = true;
								map[nx][ny] += isleCount;
							}
							// map[nx][ny]가 바다일 때 현재 땅은 바다와 접해있다.
							else if (map[nx][ny] == 0)
								isCoast = true;
						}
						if (isCoast)
							coasts.add(cur);
					}
					isleCount++;
				}
			}
		}
	}

	private static void findShortestBridge() {
		Deque<int[]> q;
		int[] cur;
		int nx, ny, bridge, size;
		boolean[][] visited;

		for (int[] coast : coasts) {
			q = new ArrayDeque<>();
			visited = new boolean[n][n];
			q.addLast(coast);
			visited[coast[0]][coast[1]] = true;
			bridge = 0;
			while (!q.isEmpty() && bridge < res) {
				size = q.size();
				bridge++;
				while (size-- > 0) {
					cur = q.removeFirst();
					for (int i = 0; i < 4; i++) {
						nx = cur[0] + dx[i];
						ny = cur[1] + dy[i];
						if (nx < 0 || n <= nx || ny < 0 || n <= ny || visited[nx][ny])
							continue;
						// map[nx][ny]가 바다라면 다리를 지어야 할 대싱
						if (map[nx][ny] == 0) {
							q.addLast(new int[] { nx, ny });
							visited[nx][ny] = true;
						}
						// map[nx][ny]가 바다는 아니면서 시작 섬과 다른 값을 갖는다면(= 다른 섬) 종료
						else if (map[nx][ny] != map[coast[0]][coast[1]]) {
							// 시작 도시가 bridge에 포함되므로 1을 빼준다.
							res = Math.min(res, bridge - 1);
							break;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		map = new int[n = readInt()][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = System.in.read() & 15;
				System.in.read();
			}
		}
		// BFS로 각 섬 구분
		distinguish();
		// 최단 거리 다리 찾기
		findShortestBridge();
		System.out.println(res);
	}
}