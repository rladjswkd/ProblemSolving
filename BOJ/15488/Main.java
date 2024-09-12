import java.io.IOException;

public class Main {
	private static int n, x, y, k;
	private static final int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1 }, dy = { 2, 1, -1, -2, -2, -1, 1, 2 };
	private static double[][][] counter;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static double solve(int x, int y, int turn) {
		int nx, ny;
		double res = 0;

		// turn이 k일 땐 더이상 이동을 하지 않는다.
		// 이미 (x, y) 칸에 나이트가 존재하므로 체스판을 벗어나지 않을 확률은 1이다.
		if (turn == k)
			return 1.0;
		// top down 활용
		if (counter[turn][x][y] != 0)
			return counter[turn][x][y];
		for (int i = 0; i < 8; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (0 <= nx && nx < n && 0 <= ny && ny < n)
				res += solve(nx, ny, turn + 1) / 8;
		}
		return (counter[turn][x][y] = res);
	}

	public static void main(String[] args) throws IOException {
		n = readInt();
		x = readInt() - 1;
		y = readInt() - 1;
		k = readInt();
		counter = new double[k][n][n];
		System.out.println(solve(x, y, 0));
	}
}