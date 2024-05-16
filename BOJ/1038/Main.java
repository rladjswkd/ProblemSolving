/*
 * 최대 감소하는 수: 9876543210 -> int로는 부족하다.
 * 
 * 브루트포스로 0 ~ 9876543210까지 모든 수를 순회하면서 한 자리씩 감소하는지 확인하면 무조건 시간초과
 * 
 * 중복 연산을 제거할 수 있나?
 * 
 * 0 ~ 9, 10, 20, 21, 30, 31, 32, 40, 41, 42, 43, ...
 * 
 * 현재 고른 숫자를 k라고 하고, k가 가장 큰 자리의 수라고 하면, 그 다음으로 큰 자리의 수는 k - 1까지 가능하다.
 * 
 * 1의 자리 -> 0 ~ 9 까지 모두 가능 -> 10
 * 10의 자리 -> 1 ~ 9 까지 가능 -> 1 * 1 + 2 * 2 + ... + 9 * 9
 * 100의 자리 -> 2 ~ 9 까지 가능 -> 2 * 1(210) + 3 * 3(310, 320, 321) + ... + 9 * (1 +
 * 2 + 3 + ... + 8)
 * ...
 * 10억의 자리 -> 9만 가능
 * 
 * 이 문제는 브루트포스 + 백트래킹으로 풀 수 있다.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static long solve(int n) {
		int size;
		long cur;
		Deque<Long> q = new ArrayDeque<>();

		q = new ArrayDeque<>();
		for (long i = 1; i <= 9; i++)
			q.addLast(i);
		n -= 9;
		// 마지막에는 9876543210만 남는다.
		while (q.size() > 1) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				for (int i = 0; i < cur % 10; i++) {
					q.addLast(cur * 10 + i);
					if (--n == 0)
						return q.getLast();
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt();

		if (n <= 9)
			bw.append((char) (n + 48)).append('\n');
		else
			bw.append(String.valueOf(solve(n))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}