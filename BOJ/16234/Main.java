
/*
 * BFS 여러 번 반복
 * 
 * 인구 이동이 발생하는 일 수가 2000일 이하인 문제만 주어진다.
 * BFS를 돌려야 할 나라를 탐색하고 BFS를 실행하는 시간 복잡도(aggregate analysis):
 * O(V + E) -> V는 최대 250, 그때 E는 49 * 50 * 2 = 4900
 * 인구 수 업데이트 -> 250
 * 
 * 2000 * (250 + 4900 + 250) = 10800000
 * 시간 내에 가능.
 * 
 */
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int n, l, r;
	private static int[][] populations;
	private static Deque<int[]> q;
	private static boolean[][] visited;
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int checkBorders(int x, int y) {
		int[] cur;
		int nx, ny, diff, population = 0;
		List<int[]> federation = new ArrayList<>();

		q.addLast(new int[] { x, y });
		visited[x][y] = true;
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			federation.add(cur);
			population += populations[cur[0]][cur[1]];
			for (int i = 0; i < 4; i++) {
				nx = cur[0] + dx[i];
				ny = cur[1] + dy[i];
				if (nx < 0 || n <= nx || ny < 0 || n <= ny || visited[nx][ny])
					continue;
				diff = Math.abs(populations[cur[0]][cur[1]] - populations[nx][ny]);
				if (l <= diff && diff <= r) {
					q.addLast(new int[] { nx, ny });
					visited[nx][ny] = true;
				}
			}
		}
		population /= federation.size();
		for (int[] coord : federation)
			populations[coord[0]][coord[1]] = population;
		// 시작 도시인 i행 j열의 도시를 제외하고 그 값이 양수면 연합과 인구 이동이 발생한 것.
		return federation.size() - 1;
	}

	private static int solve() {
		int res = 0, count = 0;

		q = new ArrayDeque<>();
		while (true) {
			count = 0;
			visited = new boolean[n][n];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (!visited[i][j])
						count += checkBorders(i, j);
			if (count == 0)
				break;
			res++;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		n = readInt();
		l = readInt();
		r = readInt();
		populations = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				populations[i][j] = readInt();
		System.out.println(solve());
	}
}