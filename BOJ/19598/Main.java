import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {
	static class Time implements Comparable<Time> {
		int start, end;

		Time(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Time o) {
			return start - o.start;
		}
	}

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt();
		Time[] times = new Time[n];
		PriorityQueue<Integer> pq = new PriorityQueue<>(n);

		for (int i = 0; i < n; i++)
			times[i] = new Time(readInt(), readInt());
		Arrays.sort(times);
		pq.offer(times[0].end);
		for (int i = 1; i < n; i++) {
			if (pq.peek() <= times[i].start)
				pq.poll();
			pq.offer(times[i].end);
		}
		System.out.println(pq.size());
	}
}