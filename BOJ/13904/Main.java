import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static int res = 0;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), maxD = 0, d;
		PriorityQueue<int[]> assignments = new PriorityQueue<>((a, b) -> b[0] - a[0]);
		PriorityQueue<int[]> pending = new PriorityQueue<>((a, b) -> b[1] - a[1]);

		for (int i = 0; i < n; i++) {
			assignments.add(new int[] { d = readInt(), readInt() });
			if (maxD < d)
				maxD = d;
		}
		// 마지막 날짜의 과제부터 역순으로 결정
		for (int day = maxD; day >= 1; day--) {
			// 현재 day에서 수행가능한 모든 과제들을 pending에 저장하기
			while (!assignments.isEmpty() && assignments.peek()[0] >= day)
				pending.add(assignments.poll());
			// 현재 day에 수행가능한 과제가 있다면 그 중 점수가 가장 큰 과제를 수행하기
			if (!pending.isEmpty())
				res += pending.poll()[1];
		}
		System.out.println(res);
	}
}