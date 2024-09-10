import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int a, b, c, d, res;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[a + b + 1];
		int[] cur;
		int w, nc;

		res = -1;
		q.addLast(new int[] { 0, 0, 0 });
		visited[0] = true;
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			if (cur[0] == c && cur[1] == d) {
				res = cur[2];
				return;
			}
			nc = cur[2] + 1;
			// A 물통 채우기 (w >= a)
			if (!visited[w = a + cur[1]]) {
				visited[w] = true;
				q.addFirst(new int[] { a, cur[1], nc });
				if (w <= b)
					q.addLast(new int[] { 0, w, nc + 1 });
				else
					q.addLast(new int[] { w - b, b, nc + 1 });
			}
			// B 물통 채우기 (w >= b)
			if (!visited[w = cur[0] + b]) {
				visited[w] = true;
				q.addFirst(new int[] { cur[0], b, nc });
				if (w <= a)
					q.addLast(new int[] { w, 0, nc + 1 });
				else
					q.addLast(new int[] { a, w - a, nc + 1 });
			}
			// A 물통 비우기 (w <= b)
			if (!visited[w = cur[1]]) {
				visited[w] = true;
				q.addFirst(new int[] { 0, w, nc });
				if (w <= a)
					q.addLast(new int[] { w, 0, nc + 1 });
				else
					q.addLast(new int[] { a, w - a, nc + 1 });
			}
			// B 물통 비우기 (w <= a)
			if (!visited[w = cur[0]]) {
				visited[w] = true;
				q.addFirst(new int[] { w, 0, nc });
				if (w <= b)
					q.addLast(new int[] { 0, w, nc + 1 });
				else
					q.addLast(new int[] { w - b, b, nc + 1 });
			}
		}
	}

	public static void main(String[] args) throws IOException {
		a = readInt();
		b = readInt();
		c = readInt();
		d = readInt();
		solve();
		System.out.println(res);
	}
}