import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int height, width;
	private static long[][] dp;
	private static boolean[][][] repairRoads;
	private static Deque<Integer> q;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve() {
		int cur, size, x, y;
		boolean[][] visited = new boolean[height + 1][width + 1];

		q = new ArrayDeque<>(Math.max(height, width));
		q.add(0);
		dp[0][0] = 1;
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				x = cur / 1000;
				y = cur % 1000;
				if (x + 1 <= height && !repairRoads[0][x + 1][y]) {
					dp[x + 1][y] += dp[x][y];
					if (!visited[x + 1][y]) {
						visited[x + 1][y] = true;
						q.addLast(cur + 1000);
					}
				}
				if (y + 1 <= width && !repairRoads[1][x][y + 1]) {
					dp[x][y + 1] += dp[x][y];
					if (!visited[x][y + 1]) {
						visited[x][y + 1] = true;
						q.addLast(cur + 1);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int repairRoadCount, a, b, c, d;

		dp = new long[(height = read()) + 1][(width = read()) + 1];
		repairRoadCount = read();
		repairRoads = new boolean[2][height + 1][width + 1];
		for (int i = 0; i < repairRoadCount; i++) {
			a = read();
			b = read();
			c = read();
			d = read();
			if (a != c)
				repairRoads[0][Math.max(a, c)][b] = true;
			else
				repairRoads[1][a][Math.max(b, d)] = true;
		}
		solve();
		System.out.println(dp[height][width]);
	}
}