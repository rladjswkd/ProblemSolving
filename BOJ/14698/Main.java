import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static final int D = 1000000007;
	private static final long L = 2 * 1000000000000000000L;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15;
		int c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static long readLong() throws IOException {
		long n = System.in.read() & 15;
		int c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), n;
		PriorityQueue<Long> pq = new PriorityQueue<>();
		long res, curr;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			n = readInt();
			for (int i = 0; i < n; i++)
				pq.offer(readLong());
			res = 1;
			while (pq.size() > 1) {
				pq.offer(curr = pq.poll() * pq.poll());
				if (L / curr < res)
					res = ((res % D) * (curr % D)) % D;
				else
					res = res * curr;
			}
			sb.append(res % D).append('\n');
			pq.clear();
		}
		System.out.print(sb.toString());
	}
}