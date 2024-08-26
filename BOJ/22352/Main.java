import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int h, w, length;
	private static int[][] sp1, sp2, list;
	private static final int[] dx = new int[] { -1, 1, 0, 0 }, dy = new int[] { 0, 0, -1, 1 };
	private static boolean found;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static boolean checkSP2(int originVal) {
		int val = sp2[list[0][0]][list[0][1]];

		for (int i = 1; i < length; i++)
			if (val != sp2[list[i][0]][list[i][1]])
				return false;
		if (val != originVal) {
			if (found)
				return false;
			found = true;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][] visited;
		int[] cur;
		int nx, ny;
		boolean flag = true;

		list = new int[1000000][2];
		sp1 = new int[h = readInt()][w = readInt()];
		sp2 = new int[h][w];
		visited = new boolean[h][w];
		// 값이 변한 부분을 찾았는지를 추적
		found = false;
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				sp1[i][j] = readInt();
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				sp2[i][j] = readInt();
		for (int i = 0; i < h && flag; i++) {
			for (int j = 0; j < w && flag; j++) {
				if (!visited[i][j]) {
					q.addLast(new int[] { i, j });
					visited[i][j] = true;
					list[length = 0][0] = i;
					list[length++][1] = j;
					while (!q.isEmpty()) {
						cur = q.removeFirst();
						for (int k = 0; k < 4; k++) {
							nx = cur[0] + dx[k];
							ny = cur[1] + dy[k];
							if (0 <= nx && nx < h && 0 <= ny && ny < w && !visited[nx][ny] && sp1[i][j] == sp1[nx][ny]) {
								visited[nx][ny] = true;
								q.addLast(new int[] { nx, ny });
								list[length][0] = nx;
								list[length++][1] = ny;
							}
						}
					}
					flag = checkSP2(sp1[i][j]);
				}
			}
		}
		System.out.println(flag ? "YES" : "NO");
	}
}