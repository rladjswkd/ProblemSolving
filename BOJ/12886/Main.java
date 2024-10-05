import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int a, b, c, res, total;
	// q에 들어갈 원소를 {셋 중 최솟값, 나머지 하나, 셋 중 최댓값}으로 구성하면,
	// cur[0] < cur[1], cur[0] < cur[2], cur[1] < cur[2]의 3 가지 경우만 가능하다.
	private static int[][] groups = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 2 } };

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve() {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[1501][1501];
		int[] cur;
		int min, max, x, y, z;

		q.addLast(
				new int[] { min = Math.min(a, (Math.min(b, c))), total - min - (max = Math.max(a, (Math.max(b, c)))), max });
		visited[min][max] = true;
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			// cur[0] < cur[1], cur[0] < cur[2], cur[1] < cur[2]의 3 가지 경우만 가능하다.
			for (int[] group : groups) {
				x = cur[group[0]];
				y = cur[group[1]];
				y -= x;
				x += x;
				z = total - x - y;
				if (x == y && y == z) {
					res = 1;
					return;
				}
				min = Math.min(x, Math.min(y, z));
				max = Math.max(x, Math.max(y, z));
				if (!visited[min][max]) {
					visited[min][max] = true;
					q.addLast(new int[] { min, total - min - max, max });
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		if ((total = (a = read()) + (b = read()) + (c = read())) % 3 != 0)
			System.out.println(0);
		else if (a == b && b == c)
			System.out.println(1);
		else {
			solve();
			System.out.println(res);
		}
	}
}