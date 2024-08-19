import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n, k, from, to;
	private static List<List<Integer>> graph;
	private static int[] prev;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		Deque<Integer> q = new ArrayDeque<>();
		int cur;

		// prev에는 1-base로 경로 상 직전 코드의 인덱스를 담고, 0이면 해당 인덱스의 코드에 방문하지 않음을 나타내는 값으로 사용한다.
		prev = new int[n];
		q.addLast(from);
		prev[from] = -1;
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			for (int neighbor : graph.get(cur)) {
				if (prev[neighbor] == 0) {
					prev[neighbor] = cur + 1;
					if (neighbor == to)
						return;
					q.addLast(neighbor);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int[] codes;
		int code, dist, oneCount;
		StringBuilder sb;

		codes = new int[n = readInt()];
		k = readInt();
		graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			code = 0;
			for (int j = 0; j < k; j++)
				code = code << 1 | (System.in.read() & 15);
			codes[i] = code;
			System.in.read();
			graph.add(new ArrayList<>());
		}
		from = readInt() - 1;
		to = readInt() - 1;
		// 해밍 거리 구하기
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				dist = codes[i] ^ codes[j];
				oneCount = 0;
				for (int bit = 0; bit < k; bit++) {
					if ((dist & 1 << bit) > 0)
						oneCount++;
					if (oneCount > 1)
						break;
				}
				if (oneCount == 1) {
					graph.get(i).add(j);
					graph.get(j).add(i);
				}
			}
		}
		solve();
		// 경로가 존재하지 않을 때
		if (prev[to] == 0)
			System.out.println(-1);
		// 경로가 존재할 때
		else {
			sb = new StringBuilder();
			// codes 재활용(경로를 순서대로 담는 스택의 역할), code는 codes의 인덱스로 재활용.
			code = 0;
			while (to != from) {
				codes[code++] = to;
				to = prev[to] - 1;
			}
			codes[code] = from;
			for (int i = code; i >= 0; i--)
				sb.append(codes[i] + 1).append(' ');
			System.out.println(sb.toString());

		}
	}
}