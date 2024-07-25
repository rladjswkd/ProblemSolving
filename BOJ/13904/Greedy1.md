주어진 과제들을 우선순위 큐에 저장하고, 점수가 큰 과제를 먼저 뽑는다.

뽑은 과제를 수행할 수 있는 날짜 중 가장 마지막 날짜에 해당 과제를 배치하고, 그 다음 과제를 우선순위 큐에서 뽑아 반복한다.

만약 현재 과제를 배치할 수 있는 날짜가 없다면 배치하지 않고 넘어간다.

```java
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), maxD = 0, d, res = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		int[] schedule, cur;

		for (int i = 0; i < n; i++) {
			pq.add(new int[] { d = readInt(), readInt() });
			if (maxD < d)
				maxD = d;
		}
		schedule = new int[maxD + 1];
		while (!pq.isEmpty()) {
			cur = pq.poll();
			d = cur[0];
			// 현재 과제를 수행할 수 있는 가능한 늦은 날짜를 탐색
			while (schedule[d] != 0)
				d--;
			// 가장 빠른 날짜가 1이므로, d가 0이라면 가능한 날짜가 없는 것이다.
			if (d > 0) {
				schedule[d] = d;
				res += cur[1];
			}
		}
		System.out.println(res);
	}
}
```
