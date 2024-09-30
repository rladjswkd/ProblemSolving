import java.io.IOException;

public class Main {
	private static int height, width, playerCount, blankCount, positionCount;
	private static int[] strides, res, dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 }, fronts, rears;
	private static int[][] board, positions;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve() {
		int cur, nx, ny, stride, size, front, rear;
		int[] q;

		while (positionCount > 0) {
			for (int p = 0; p < playerCount && positionCount > 0; p++) {
				q = positions[p];
				stride = strides[p];
				front = fronts[p];
				rear = rears[p];
				while (front < rear && stride-- > 0) {
					size = rear - front;
					while (size-- > 0) {
						cur = q[front++];
						positionCount--;
						for (int i = 0; i < 4; i++) {
							nx = cur / 1000 + dx[i];
							ny = cur % 1000 + dy[i];
							if (0 <= nx && nx < height && 0 <= ny && ny < width && board[nx][ny] == '.') {
								board[nx][ny] = p;
								res[p]++;
								q[rear++] = nx * 1000 + ny;
								positionCount++;
								if (--blankCount == 0)
									return;
							}
						}
					}
				}
				fronts[p] = front;
				rears[p] = rear;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int c;
		StringBuilder sb = new StringBuilder();

		board = new int[height = read()][width = read()];
		strides = new int[playerCount = read()];
		positions = new int[playerCount][height * width];
		fronts = new int[playerCount];
		rears = new int[playerCount];
		for (int i = 0; i < playerCount; i++)
			strides[i] = read();
		res = new int[playerCount];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				c = System.in.read();
				if (48 <= c && c <= 57) {
					c = (c & 15) - 1;
					positions[c][rears[c]++] = i * 1000 + j;
					res[c]++;
					positionCount++;
				} else if (c == '.')
					blankCount++;
				board[i][j] = c;
			}
			System.in.read();
		}
		solve();
		for (int count : res)
			sb.append(count).append(' ');
		System.out.println(sb.toString());
	}
}