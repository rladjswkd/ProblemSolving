import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;

public class Main {
	private static int n, k, seq;

	private static int flip(int target, int cur) {
		int powStart = (int) Math.pow(10, target), powEnd = (int) Math.pow(10, target - k + 1);
		int flipped = cur / powStart / 10, partToFlip = cur / powEnd;

		for (int i = 0; i < k; i++) {
			flipped = (flipped << 3) + (flipped << 1) + (partToFlip % 10);
			partToFlip /= 10;
		}
		return flipped * powEnd + cur % powEnd;
	}

	private static boolean check(int val) {
		int next = val % 10;

		while (val > 0) {
			val /= 10;
			if (val % 10 != next - 1)
				return false;
			next = val % 10;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		Deque<Integer> q = new ArrayDeque<>();
		Set<Integer> s = new HashSet<>();
		int cur, size, res = 0, flipped;
		boolean found;

		n = System.in.read() & 15;
		System.in.read();
		k = System.in.read() & 15;
		System.in.read();
		seq = 0;
		for (int i = 0; i < n; i++) {
			seq = (seq << 3) + (seq << 1) + (System.in.read() & 15);
			System.in.read();
		}
		q.addLast(seq);
		s.add(seq);
		found = check(seq);
		while (!q.isEmpty() && !found) {
			size = q.size();
			res++;
			while (size-- > 0 && !found) {
				cur = q.removeFirst();
				for (int i = n - 1; i - k + 1 >= 0; i--) {
					if (s.contains(flipped = flip(i, cur)))
						continue;
					if (check(flipped)) {
						found = true;
						break;
					} else {
						s.add(flipped);
						q.addLast(flipped);
					}
				}
			}
		}
		System.out.println(found ? res : -1);
	}
}