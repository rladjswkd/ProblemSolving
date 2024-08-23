import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;

public class Main {
	private static int n, k, maxPow;
	private static Deque<Integer> q;
	private static Set<Integer> s;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int exchange(int value, int posLeft, int posRight) {
		// posLeft 위치의 값 - posRight 위치의 값
		int diff = value / posLeft % 10 - value / posRight % 10;

		return value - diff * posLeft + diff * posRight;
	}

	public static void main(String[] args) throws IOException {
		int cur, exchanged, res = -1, size;

		n = 0;
		maxPow = 1;
		while (48 <= (cur = System.in.read()) && cur <= 57) {
			n = (n << 3) + (n << 1) + (cur & 15);
			maxPow *= 10;
		}
		k = readInt();
		maxPow /= 10;
		q = new ArrayDeque<>();
		q.addLast(n);
		while (!q.isEmpty() && k > 0) {
			s = new HashSet<>();
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				// i가 가장 큰 자리일 때 j를 순회하며 교환
				for (int j = maxPow / 10; j >= 1; j /= 10) {
					if (cur / j % 10 != 0 && !s.contains(exchanged = exchange(cur, maxPow, j))) {
						s.add(exchanged);
						q.addLast(exchanged);
					}
				}
				// i가 두 번째로 큰 자리일 때부터 고려
				for (int i = maxPow / 10; i >= 1; i /= 10) {
					for (int j = i / 10; j >= 1; j /= 10) {
						if (!s.contains(exchanged = exchange(cur, i, j))) {
							s.add(exchanged);
							q.addLast(exchanged);
						}
					}
				}
			}
			k--;
		}
		if (k == 0)
			while (!q.isEmpty())
				res = Math.max(res, q.removeFirst());
		System.out.println(res);
	}
}