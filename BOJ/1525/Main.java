import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static final int TARGET = 123456780;
	private static Set<Integer> s;
	private static int res;

	private static int swap(int seq, int idx1, int idx2) {
		int pow1 = 1, pow2 = 1, v;

		// 반복문을 쓰더라도 부동소수점을 사용하지 않는 것이 훨씬 시간 효율적이다.
		for (int i = 1; i <= 8 - idx1; i++)
			pow1 = (pow1 << 3) + (pow1 << 1);
		for (int i = 1; i <= 8 - idx2; i++)
			pow2 = (pow2 << 3) + (pow2 << 1);
		v = seq / pow1 % 10;
		// idx1의 값을 0으로 만들고, idx2의 값은 기존 idx1의 값으로 채운다
		return seq + v * (pow2 - pow1);
	}

	private static void solve(int seq, int index, int count) {
		Deque<int[]> q = new ArrayDeque<>();
		int newIndex, newSeq, size;
		int[] cur;

		q.addLast(new int[] { seq, index });
		s.add(seq);
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				// cur[0]이 우리가 찾는 목표라면 res를 업데이트하고 리턴
				if (cur[0] == TARGET)
					return;
				// 위칸 숫자를 빈칸으로 이동
				if (0 <= (newIndex = cur[1] - 3) && newIndex <= 5 && !s.contains(newSeq = swap(cur[0], newIndex, cur[1]))) {
					s.add(newSeq);
					q.addLast(new int[] { newSeq, newIndex });
				}
				// 아래칸 숫자를 빈칸으로 이동
				if (3 <= (newIndex = cur[1] + 3) && newIndex <= 8 && !s.contains(newSeq = swap(cur[0], newIndex, cur[1]))) {
					s.add(newSeq);
					q.addLast(new int[] { newSeq, newIndex });
				}
				// 왼칸 숫자를 빈칸으로 이동
				if (0 <= (newIndex = cur[1] - 1) && newIndex % 3 <= 1 && !s.contains(newSeq = swap(cur[0], newIndex, cur[1]))) {
					s.add(newSeq);
					q.addLast(new int[] { newSeq, newIndex });
				}
				// 오른칸 숫자를 빈칸으로 이동
				if ((newIndex = cur[1] + 1) <= 8 && newIndex % 3 >= 1 && !s.contains(newSeq = swap(cur[0], newIndex, cur[1]))) {
					s.add(newSeq);
					q.addLast(new int[] { newSeq, newIndex });
				}
			}
			res++;
		}
	}

	public static void main(String[] args) throws IOException {
		int start = 0, cur, zeroIndex = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				start = (start << 3) + (start << 1) + (cur = System.in.read() & 15);
				if (cur == 0)
					zeroIndex = 3 * i + j;
				System.in.read();
			}
		}
		s = new HashSet<>();
		if (start == TARGET)
			System.out.println(0);
		else {
			res = 0;
			solve(start, zeroIndex, 0);
			System.out.println(!s.contains(TARGET) ? -1 : res);
		}
	}
}