import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static int t, n;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
		PriorityQueue<int[]> idq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		int[] task;
		StringBuilder sb = new StringBuilder();

		t = read();
		n = read();
		for (int i = 0; i < n; i++)
			pq.add(new int[] { read(), read(), read() });
		while (!pq.isEmpty()) {
			task = pq.peek();
			while (!pq.isEmpty() && pq.peek()[2] == task[2])
				idq.add(pq.poll());
			while (!idq.isEmpty()) {
				task = idq.poll();
				task[2]--;
				if (--task[1] != 0)
					pq.add(task);
				sb.append(task[0]).append('\n');
				if (--t == 0) {
					System.out.print(sb.toString());
					return;
				}
			}
		}
	}
}