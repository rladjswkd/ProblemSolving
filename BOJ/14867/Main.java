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
		// A 또는 B가 비어있거나([0][x]) 가득 찰 때([1][x]) 다른 물병에 대한 처리 여부 확인
		boolean[][] visitedA = new boolean[2][b + 1], visitedB = new boolean[2][a + 1];
		int[] cur;
		int size, count = 0, na, nb;

		res = -1;
		q.addLast(new int[] { 0, 0 });
		visitedA[0][0] = visitedB[0][0] = true;
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				if (cur[0] == c && cur[1] == d) {
					res = count;
					return;
				}
				// A를 가득 채우는 경우
				if (!visitedA[1][cur[1]] && (visitedA[1][cur[1]] = true))
					q.addLast(new int[] { a, cur[1] });
				// B를 가득 채우는 경우
				if (!visitedB[1][cur[0]] && (visitedB[1][cur[0]] = true))
					q.addLast(new int[] { cur[0], b });
				// A를 비우는 경우
				if (!visitedA[0][cur[1]] && (visitedA[0][cur[1]] = true))
					q.addLast(new int[] { 0, cur[1] });
				// B를 비우는 경우
				if (!visitedB[0][cur[0]] && (visitedB[0][cur[0]] = true))
					q.addLast(new int[] { cur[0], 0 });
				// A를 B에 붓는 경우
				if (cur[0] <= b - cur[1]) {
					na = 0;
					nb = cur[1] + cur[0];
					if (!visitedA[0][nb] && (visitedA[0][nb] = true))
						q.addLast(new int[] { na, nb });
				} else {
					na = cur[1] + cur[0] - b;
					nb = b;
					if (!visitedB[1][na] && (visitedB[1][na] = true))
						q.addLast(new int[] { na, nb });
				}
				// B를 A에 붓는 경우
				if (cur[1] <= a - cur[0]) {
					na = cur[0] + cur[1];
					nb = 0;
					if (!visitedB[0][na] && (visitedB[0][nb] = true))
						q.addLast(new int[] { na, nb });
				} else {
					na = a;
					nb = cur[0] + cur[1] - a;
					if (!visitedA[1][nb] && (visitedA[1][nb] = true))
						q.addLast(new int[] { na, nb });
				}
			}
			count++;
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