import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int h, w, virusLength, blankLength, res;
	// virusCells도 1차원 배열로 만들 수 있지만, q에 추가할 때 어차피 new int[]{i, j}를 생성해야 하기 때문에
	// 2차원 배열로 만들었다.
	// i * w + j 를 값으로 하는 1차원 배열로 만들고, q로 int[]가 아닌 Integer 값들을 다룬다면, 가장자리 셀들에서 문제가
	// 생긴다.
	private static int[][] map, virusCells;
	private static int[] blankCells, dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	private static Deque<int[]> q;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void calculateSafeArea() {
		int[] cur;
		int blankCount = blankLength - 3, nx, ny;
		boolean[][] visited = new boolean[h][w];

		if (blankCount == 0)
			return;
		for (int i = 0; i < virusLength; i++)
			q.addLast(virusCells[i]);
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			for (int i = 0; i < 4; i++) {
				nx = cur[0] + dx[i];
				ny = cur[1] + dy[i];
				// 지도 상 셀의 값이 1이나 2일 때는 방문할 필요가 없다.
				if (0 <= nx && nx < h && 0 <= ny && ny < w && map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.addLast(new int[] { nx, ny });
					blankCount--;
				}
			}
		}
		if (res < blankCount)
			res = blankCount;
	}

	private static void buildWalls(int start, int count) {
		if (count == 3) {
			calculateSafeArea();
			return;
		}
		for (int i = start; i <= blankLength - 3 + count; i++) {
			map[blankCells[i] / w][blankCells[i] % w] = 1;
			buildWalls(i + 1, count + 1);
			map[blankCells[i] / w][blankCells[i] % w] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		int c;

		map = new int[h = read()][w = read()];
		virusCells = new int[10][];
		blankCells = new int[h * w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if ((c = System.in.read() & 15) == 2)
					virusCells[virusLength++] = new int[] { i, j };
				else if (c == 0)
					blankCells[blankLength++] = i * w + j;
				map[i][j] = c;
				System.in.read();
			}
		}
		q = new ArrayDeque<>();
		buildWalls(0, 0);
		System.out.println(res);
	}
}