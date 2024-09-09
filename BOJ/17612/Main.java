import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Main {
	static class Customer implements Comparable<Customer> {
		int id, time, counterID;

		Customer(int id, int time, int counterID) {
			this.id = id;
			this.time = time;
			this.counterID = counterID;
		}

		@Override
		public int compareTo(Customer o) {
			return time == o.time ? o.counterID - counterID : time - o.time;
		}
	}

	private static int n, k;
	private static int[][] customers;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		Deque<Integer> availableCounters = new ArrayDeque<>();
		PriorityQueue<Customer> pq = new PriorityQueue<>();
		Customer c;
		int idx, end;
		long order = 1, res = 0;

		n = readInt();
		k = readInt();
		end = Math.min(n, k);
		customers = new int[n][];
		for (int i = 0; i < n; i++)
			customers[i] = new int[] { readInt(), readInt() };
		// 고객의 수가 계산대의 수보다 적을 수 있다.
		for (idx = 0; idx < end; idx++)
			pq.add(new Customer(customers[idx][0], customers[idx][1], idx + 1));
		while (!pq.isEmpty()) {
			res += order++ * (c = pq.poll()).id;
			availableCounters.addFirst(c.counterID);
			while (!pq.isEmpty() && c.time == pq.peek().time) {
				res += order++ * (c = pq.poll()).id;
				availableCounters.addFirst(c.counterID);
			}
			while (!availableCounters.isEmpty() && idx < n) {
				pq.add(new Customer(customers[idx][0], customers[idx][1] + c.time, availableCounters.removeFirst()));
				idx++;
			}
		}
		System.out.println(res);
	}
}