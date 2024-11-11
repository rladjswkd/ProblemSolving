import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

public class Main {
	private static int n, l, r, x, res;
	private static int[] a;
	private static Set<Integer> visited;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void select(int idx, int count, int total, int lowest, int highest, int mask) {
		if (count >= 2 && l <= total && total <= r && highest - lowest >= x && !visited.contains(mask)) {
			visited.add(mask);
			res++;
		}
		if (idx == n)
			return;
		// 현재 문제를 고르지 않는 경우
		select(idx + 1, count, total, lowest, highest, mask);
		// 현재 문제를 고르는 경우
		select(idx + 1, count + 1, total + a[idx], Math.min(lowest, a[idx]), Math.max(highest, a[idx]), mask | 1 << idx);
	}

	public static void main(String[] args) throws IOException {
		n = read();
		l = read();
		r = read();
		x = read();
		a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = read();
		visited = new HashSet<>();
		select(0, 0, 0, 1000001, 0, 0);
		System.out.println(res);
	}
}