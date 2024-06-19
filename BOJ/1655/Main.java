
/*
 * 우선순위 큐
 * 
 * 1. 현재의 중위값을 기준으로 왼쪽 값을 담을 left(중위값보다 작은 값), 오른쪽 값(중위값과 같거나 큰 값)을 담을 right를 사용해
 * 		두 우선순위 큐의 사이즈가 2만큼 차이나면 큰 쪽에서 값을 poll해 그 값을 중위값으로 사용하고 기존의 중위값은 반대쪽 큐에 넣기
 * -> 6 6 6 5 5 5 와 같은 입력에 대해 left와 right의 사이즈는 다음과 같다.
 * 		left	right
 * 6	0			0
 * 6	0			1
 * 6	0			2 (중위값이 두 번째 6으로 변경) -> left: 1, right: 1
 * 5	2			1
 * 5	3			1 (중위값이 세 번째 6으로 변경) -> left: 2, right: 2
 * 5	3			2
 * 
 * 여기서 마지막 5가 들어왔을 때, 중위값이 첫 번째 5로 바뀌어야 하지만 바뀌지 않는다.
 * 또한 첫 번째 5가 들어올 때도 5 6 6 6 순서이므로 첫 번째 6으로 중위값이 바뀌어야 한다.
 * 따라서 1번 방식은 땡
 * 
 * 
 * 2. 현재 중위값의 입력된 순서(인덱스)를 저장하고 두 큐의 사이즈를 더한 값이 x라고 할 떄, 중위값을 포함하여 0 ~ x의 인덱스를 상상해
 * 		새로운 중위값의 인덱스를 구했을 때, 현재 중위값의 인덱스와 다르면 중위값을 업데이트하고 기존의 중위값을 적절한 우선순위 큐에 추가
 * 
 * 
 * 
 */
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			n = ~n + 1;
		return n;
	}

	public static void main(String[] args) throws IOException {
		// position: 입력된 n개의 값을 배열로 생각할 때 중위값 median의 인덱스
		int n = readInt(), median = readInt(), position = 0, next;
		PriorityQueue<Integer> right = new PriorityQueue<>(), left = new PriorityQueue<>((a, b) -> b - a);
		StringBuilder sb = new StringBuilder();

		sb.append(median).append('\n');
		for (int i = 1; i < n; i++) {
			next = readInt();
			if (next < median) {
				left.add(next);
				position++;
			} else
				right.add(next);
			if (left.size() + right.size() >>> 1 < position) {
				right.add(median);
				median = left.poll();
				position--;
			} else if (left.size() + right.size() >>> 1 > position) {
				left.add(median);
				median = right.poll();
				position++;
			}
			sb.append(median).append('\n');
		}
		System.out.print(sb.toString());
	}
}