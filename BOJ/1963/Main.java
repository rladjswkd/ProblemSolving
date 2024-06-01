
/*
 * 에라토스테네스의 체로 9999까지 소수판별 -> BFS
 * "주어진 두 소수 A에서 B로 바꾸는 과정에서도..." -> 주어진 두 값은 항상 소수다.
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
	private static boolean[] nonPrimes, visited;
	private static Deque<Integer> q;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static boolean changeOneDigit(int start, int end, int interval, int target) {
		for (int i = start; i <= end; i += interval) {
			// target은 항상 소수
			if (i == target)
				return true;
			if (!nonPrimes[i] && !visited[i - 1000]) {
				q.addLast(i);
				visited[i - 1000] = true;
			}
		}
		return false;
	}

	private static int solve(boolean[] nonPrimes, int current, int target) {
		int cur, size, res = 0;

		if (current == target)
			return 0;
		q = new ArrayDeque<>();
		// 인덱스 0: 1000, 인덱스 1: 1001, ..., 인덱스 8999 : 9999
		visited = new boolean[9000];
		q.addLast(current);
		visited[current - 1000] = true;
		while (!q.isEmpty()) {
			size = q.size();
			res++;
			while (size-- > 0) {
				cur = q.removeFirst();
				// 천의 자리 확인
				if (changeOneDigit(1000 + cur % 1000, 9000 + cur % 1000, 1000, target))
					return res;
				// 백의 자리 확인
				if (changeOneDigit(cur - cur % 1000 + cur % 100, cur - cur % 1000 + 900 + cur % 100, 100, target))
					return res;
				// 십의 자리 확인
				if (changeOneDigit(cur - cur % 100 + cur % 10, cur - cur % 100 + 90 + cur % 10, 10, target))
					return res;
				// 일의 자리 확인
				if (changeOneDigit(cur - cur % 10, cur - cur % 10 + 9, 1, target))
					return res;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), count;
		StringBuilder sb = new StringBuilder();

		nonPrimes = new boolean[10000];
		nonPrimes[0] = nonPrimes[1] = true;
		for (int i = 2; i <= 100; i++) {
			if (nonPrimes[i])
				continue;
			for (int j = i + i; j < 10000; j += i)
				nonPrimes[j] = true;
		}
		while (t-- > 0) {
			count = solve(nonPrimes, readInt(), readInt());
			if (count == -1)
				sb.append("Impossible\n");
			else
				sb.append(count).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}