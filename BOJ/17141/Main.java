import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int labSize, virusCount, length, blankCount, res;
	private static int[][] lab, virusCells;
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int read() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void countTime(int bits) {
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur;
		boolean[][] visited = new boolean[labSize][labSize];
		int bc = 0, size, time = 0, nx, ny;

		for (int i = 0; i < length; i++) {
			if ((bits & 1 << i) != 0) {
				q.addLast(virusCells[i]);
				visited[virusCells[i][0]][virusCells[i][1]] = true;
			}
		}
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				for (int i = 0; i < 4; i++) {
					nx = cur[0] + dx[i];
					ny = cur[1] + dy[i];
					if (0 <= nx && nx < labSize && 0 <= ny && ny < labSize && !visited[nx][ny] && lab[nx][ny] != 1) {
						if (++bc == blankCount) {
							res = Math.min(res, time + 1);
							return;
						}
						visited[nx][ny] = true;
						q.addLast(new int[] { nx, ny });
					}
				}
			}
			time++;
		}
	}

	private static void select(int start, int count, int bits) {
		if (count == virusCount) {
			countTime(bits);
			return;
		}

		for (int i = start; i <= length - virusCount + count; i++)
			select(i + 1, count + 1, bits | 1 << i);
	}

	public static void main(String[] args) throws IOException {
		lab = new int[labSize = read()][labSize];
		virusCount = read();
		virusCells = new int[10][];
		for (int i = 0; i < labSize; i++) {
			for (int j = 0; j < labSize; j++) {
				if ((lab[i][j] = System.in.read() & 15) == 2)
					virusCells[length++] = new int[] { i, j };
				else if (lab[i][j] == 0)
					blankCount++;
				System.in.read();
			}
		}
		if (blankCount == 0 && length - virusCount <= 0)
			System.out.println(0);
		else {
			blankCount += length - virusCount;
			res = Integer.MAX_VALUE;
			select(0, 0, 0);
			System.out.println(res == Integer.MAX_VALUE ? -1 : res);
		}
	}
}