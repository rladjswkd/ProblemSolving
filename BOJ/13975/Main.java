import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static int t, k;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		PriorityQueue<Long> pq = new PriorityQueue<>(1000000);
		StringBuilder sb = new StringBuilder();
		long res, curr;

		t = read();
		while (t-- > 0) {
			k = read();
			for (int i = 0; i < k; i++)
				pq.offer((long) read());
			pq.offer(res = pq.poll() + pq.poll());
			while (pq.size() > 1) {
				pq.offer(curr = (pq.poll() + pq.poll()));
				res += curr;
			}
			sb.append(res).append('\n');
			pq.clear();
		}
		System.out.print(sb.toString());
	}
}