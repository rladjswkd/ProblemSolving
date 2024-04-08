import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
	public int solution(int[][] land) {
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur, dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 }, dp = new int[land[0].length], checker;
		boolean[][] vis = new boolean[land.length][land[0].length];
		int nx, ny, total = 0, max = 0;

		for (int i = 0; i < land.length; i++) {
			for (int j = 0; j < land[0].length; j++) {
				if (land[i][j] == 0 || vis[i][j])
					continue;
				q.addLast(new int[] { i, j });
				vis[i][j] = true;
				checker = new int[land[0].length];
				total = 0;
				while (!q.isEmpty()) {
					cur = q.removeFirst();
					total++;
					checker[cur[1]] = 1;
					for (int k = 0; k < 4; k++) {
						nx = cur[0] + dx[k];
						ny = cur[1] + dy[k];
						if (nx < 0 || nx > land.length - 1 || ny < 0 || ny > land[0].length - 1 || vis[nx][ny] || land[nx][ny] == 0)
							continue;
						q.addLast(new int[] { nx, ny });
						vis[nx][ny] = true;
					}
				}
				for (int idx = 0; idx < checker.length; idx++)
					if (checker[idx] == 1)
						dp[idx] += total;
			}
		}
		for (int v : dp)
			if (max < v)
				max = v;
		return max;
	}
}