
/*
 * 0-1 BFS
 * https://cp-algorithms.com/graph/01_bfs.html
 * 
 * BFS이므로 시간 복잡도가 O(V + E)여서 min priority queue를 사용하는 다익스트라 알고리즘의 O((V + E)logV)보다 빠르다.
 * 
 * deque를 사용한다.
 * 검은 방이면 이동 비용이 1, 흰 방이면 이동 비용이 0이다.
 * 다음 방이 유효한 범위의 방이고 아직 방문하지 않은 방이라면, 이동 비용이 0일 땐 deque의 앞에 추가한고, 1일 땐 deque의 뒤에 추가한다.
 * 
 * 잘 생각해보면 deque내의 원소들은 앞에서부터 순서대로 non-decreasing order로 정렬되어있다.
 */
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n, res = 0;
	private static int[][] rooms;
	private static int[] dx = new int[] { -1, 1, 0, 0 }, dy = new int[] { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		// int[0]: 행 인덱스, int[1]: 열 인덱스, int[2]: 이동 비용
		Deque<int[]> dq = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];
		int[] cur;
		int nx, ny;

		dq.addLast(new int[] { 0, 0, 0 });
		visited[0][0] = true;
		while (!dq.isEmpty()) {
			cur = dq.removeFirst();
			if (cur[0] == n - 1 && cur[1] == n - 1) {
				res = cur[2];
				break;
			}
			for (int i = 0; i < 4; i++) {
				nx = cur[0] + dx[i];
				ny = cur[1] + dy[i];
				if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) {
					// 흰 방일 땐 이동 비용이 없으므로(0) dq의 앞에 추가
					if (rooms[nx][ny] == 1)
						dq.addFirst(new int[] { nx, ny, cur[2] });
					// 검은 방일 땐 이동 비용이 있으므로(1) dq의 뒤에 추가
					else
						dq.addLast(new int[] { nx, ny, cur[2] + 1 });
					visited[nx][ny] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		n = readInt();
		rooms = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				rooms[i][j] = System.in.read() & 15;
			// '\n'
			System.in.read();
		}
		solve();
		System.out.println(res);
	}
}