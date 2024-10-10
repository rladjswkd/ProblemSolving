import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;

public class Main {
	private static int s, t, resultBits, resultCount;
	private static StringBuilder sb;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static boolean solve() {
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur;
		int next, count = 0, size;
		Set<Integer> set = new HashSet<>();

		q.addLast(new int[] { s, 0, 0 });
		set.add(s);
		cur = q.removeFirst();
		if (cur[0] <= t / cur[0] && !set.contains(next = cur[0] * cur[0])) {
			if (next == t) {
				resultBits = cur[1] | 0 << count;
				resultCount = count + 1;
				return true;
			}
			set.add(next);
			q.addLast(new int[] { next, cur[1] | 0 << count, cur[2] });
		}
		if (cur[0] <= t - cur[0] && !set.contains(next = cur[0] + cur[0])) {
			if (next == t) {
				resultBits = cur[1] | 1 << count;
				resultCount = count + 1;
				return true;
			}
			set.add(next);
			q.addLast(new int[] { next, cur[1] | 1 << count, cur[2] });
		}
		count++;
		q.addLast(new int[] { 1, 0, '/' });
		set.add(1);
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				if (cur[0] <= t / cur[0] && !set.contains(next = cur[0] * cur[0])) {
					if (next == t) {
						resultBits = cur[1] | 0 << count;
						resultCount = count + 1;
						if (cur[2] != 0) {
							sb.append('/');
							resultBits >>>= 1;
							resultCount--;
						}
						return true;
					}
					set.add(next);
					q.addLast(new int[] { next, cur[1] | 0 << count, cur[2] });
				}
				if (cur[0] <= t - cur[0] && !set.contains(next = cur[0] + cur[0])) {
					if (next == t) {
						resultBits = cur[1] | 1 << count;
						resultCount = count + 1;
						if (cur[2] != 0) {
							sb.append('/');
							resultBits >>>= 1;
							resultCount--;
						}
						return true;
					}
					set.add(next);
					q.addLast(new int[] { next, cur[1] | 1 << count, cur[2] });
				}
			}
			count++;
		}
		return false;
	}

	private static void buildString() {
		while (resultCount > 0) {
			if ((resultBits & 1) == 0)
				sb.append('*');
			else
				sb.append('+');
			resultBits >>>= 1;
			resultCount--;
		}
	}

	public static void main(String[] args) throws IOException {
		if ((s = read()) == (t = read()))
			System.out.println(0);
		else if (s == 0)
			System.out.println(-1);
		else if (t == 1)
			System.out.println('/');
		else {
			sb = new StringBuilder();
			if (solve()) {
				buildString();
				System.out.println(sb.toString());
			} else
				System.out.println(-1);
		}
	}
}