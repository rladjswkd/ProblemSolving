import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static int res;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), maxD = 0, d;
		PriorityQueue<int[]> dateQ = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		PriorityQueue<Integer> payQ = new PriorityQueue<>((a, b) -> b - a);

		for (int i = 0; i < n; i++) {
			dateQ.add(new int[] { readInt(), d = readInt() });
			if (maxD < d)
				maxD = d;
		}
		res = 0;
		for (int i = maxD; i >= 1; i--) {
			while (!dateQ.isEmpty() && dateQ.peek()[1] >= i)
				payQ.add(dateQ.poll()[0]);
			if (!payQ.isEmpty())
				res += payQ.poll();
		}
		System.out.println(res);
	}
}