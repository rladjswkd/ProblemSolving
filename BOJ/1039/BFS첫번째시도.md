### 4188 2

순서대로 8184, 8814를 선택하고, 최종적으로 8814를 출력하나 정답은 8841이다.

```java
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n, k, length;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int exchange(int value, int posLeft, int posRight) {
		// posLeft 위치의 값 - posRight 위치의 값
		int diff = value / posLeft % 10 - value / posRight % 10;

		return value - diff * posLeft + diff * posRight;
	}

	public static void main(String[] args) throws IOException {
		int cur, maxPow, res = 0;
		Deque<Integer> q = new ArrayDeque<>(15);

		n = length = 0;
		while (48 <= (cur = System.in.read()) && cur <= 57) {
			n = (n << 3) + (n << 1) + (cur & 15);
			length++;
		}
		k = readInt();
		maxPow = (int) Math.pow(10, length - 1);
		q.addLast(n);
		while (!q.isEmpty() && k > 0) {
			cur = q.removeFirst();
			// i가 가장 큰 자리일 때 j를 순회하며 교환
			for (int j = maxPow / 10; j >= 1; j /= 10)
				if (cur / j % 10 != 0)
					q.addLast(exchange(cur, maxPow, j));
			// i가 두 번째로 큰 자리일 때부터 고려
			for (int i = maxPow / 10; i >= 1; i /= 10)
				for (int j = i / 10; j >= 1; j /= 10)
					q.addLast(exchange(cur, i, j));
			// q에 담긴 값 중 가장 큰 값 선택
			res = 0;
			while (!q.isEmpty())
				res = Math.max(res, q.removeFirst());
			if (res == 0)
				break;
			q.addLast(res);
			k--;
		}
		if (k == 0)
			System.out.println(res);
		else
			System.out.println(-1);
	}
}
```
