import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int[][] castle, roomNumbers;
	private static List<Integer> sizes;
	private static boolean[][] visited;
	private static int h, w, roomCount, largestSize, largestCombinedSize;
	private static Deque<int[]> q;
	// 서, 북, 동, 남
	private static int[] dx = { 0, -1, 0, 1 }, dy = { -1, 0, 1, 0 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void search(int x, int y) {
		int size = 0, nx, ny;
		int[] cur;

		q.addLast(new int[] { x, y });
		visited[x][y] = true;
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			roomNumbers[cur[0]][cur[1]] = roomCount;
			size++;
			for (int i = 0; i < 4; i++) {
				nx = cur[0] + dx[i];
				ny = cur[1] + dy[i];
				if (0 <= nx && nx < h && 0 <= ny && ny < w && !visited[nx][ny] && (castle[cur[0]][cur[1]] & 1 << i) == 0) {
					visited[nx][ny] = true;
					q.addLast(new int[] { nx, ny });
				}
			}
		}
		sizes.add(size);
		roomCount++;
		if (largestSize < size)
			largestSize = size;
	}

	public static void main(String[] args) throws IOException {
		int ni, nj, n1, n2;

		w = readInt();
		h = readInt();
		castle = new int[h][w];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				castle[i][j] = readInt();
		roomCount = 0;
		largestSize = 0;
		visited = new boolean[h][w];
		q = new ArrayDeque<>();
		sizes = new ArrayList<>();
		roomNumbers = new int[h][w];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				if (!visited[i][j])
					search(i, j);
		largestCombinedSize = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				for (int k = 0; k < 4; k++) {
					ni = i + dx[k];
					nj = j + dy[k];
					if (0 <= ni && ni < h && 0 <= nj && nj < w && (n1 = roomNumbers[i][j]) != (n2 = roomNumbers[ni][nj])
							&& largestCombinedSize < sizes.get(n1) + sizes.get(n2))
						largestCombinedSize = sizes.get(n1) + sizes.get(n2);
				}
			}
		}
		System.out.printf("%d\n%d\n%d\n", roomCount, largestSize, largestCombinedSize);
	}
}