import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int h, w, res;
	private static int[][] floorPlan;
	private static boolean[] keys;
	private static Deque<int[]> starts;
	private static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void setValue(int ch, int i, int j) {
		floorPlan[i][j] = ch;
		if (ch != '*')
			starts.addLast(new int[] { i, j });
	}

	private static void checkValue(int i, int j) {
		int value = floorPlan[i][j];

		if (value == '$')
			res++;
		else if ('a' <= value && value <= 'z')
			keys[value - 97] = true;
	}

	private static void solve() {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[h][w];
		int[] space;
		int processedCount = 1, size;
		int ch, nx, ny;

		while (processedCount > 0) {
			processedCount = 0;
			size = starts.size();
			while (size-- > 0) {
				space = starts.removeFirst();
				ch = floorPlan[space[0]][space[1]];
				// 시작 칸에 방문하지 않았고, 시작 칸이 벽이거나 열쇠를 갖지 못한 칸이 아닐 때만 BFS를 진행
				// starts에 들어있는 칸들은 방문처리가 될 일이 없다.
				if (!visited[space[0]][space[1]] && ch != '*' && !('A' <= ch && ch <= 'Z' && !keys[ch - 65])) {
					q.addLast(space);
					visited[space[0]][space[1]] = true;
					processedCount++;
					while (!q.isEmpty()) {
						space = q.removeFirst();
						checkValue(space[0], space[1]);
						for (int i = 0; i < 4; i++) {
							nx = space[0] + dx[i];
							ny = space[1] + dy[i];
							if (nx < 0 || h <= nx || ny < 0 || w <= ny || visited[nx][ny])
								continue;
							ch = floorPlan[nx][ny];
							// 벽도 아니고 문이면서 열쇠가 없는 것도 아닐 때 아직 방문하지 않은 칸이라면 진행
							if (ch != '*' && !('A' <= ch && ch <= 'Z' && !keys[ch - 65])) {
								q.addLast(new int[] { nx, ny });
								visited[nx][ny] = true;
								processedCount++;
							}
							// 문이면서 열쇠가 없는 상황이라면 starts에 저장
							else if ('A' <= ch && ch <= 'Z')
								starts.addLast(new int[] { nx, ny });
						}
					}
				}
				// 열쇠가 없는 문인 경우 다시 starts에 추가하기
				else if ('A' <= ch && ch <= 'Z')
					starts.addLast(space);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), ch;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			floorPlan = new int[h = readInt()][w = readInt()];
			keys = new boolean[26];
			res = 0;
			starts = new ArrayDeque<>();
			for (int j = 0; j < w; j++)
				setValue(System.in.read(), 0, j);
			System.in.read();
			for (int i = 1; i < h - 1; i++) {
				setValue(System.in.read(), i, 0);
				for (int j = 1; j < w - 1; j++)
					floorPlan[i][j] = System.in.read();
				setValue(System.in.read(), i, w - 1);
				System.in.read();
			}
			for (int j = 0; j < w; j++)
				setValue(System.in.read(), h - 1, j);
			System.in.read();
			while ((ch = System.in.read()) != '\n' && ch != 48)
				keys[ch - 97] = true;
			// 사전에 소지한 열쇠가 없어서 ch로 '0'이 들어온다면 개행 문자 처리를 위해 System.in.read()를 한 번 더 호출
			if (ch == 48)
				System.in.read();
			solve();
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}