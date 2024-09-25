import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int subjectCount;
	private static int[] degree, res;
	private static boolean[][] graph;
	private static Deque<Integer> q;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve() {
		int term = 1, cur, size;

		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				res[cur] = term;
				for (int i = 0; i < subjectCount; i++)
					if (graph[cur][i] && --degree[i] == 0)
						q.addLast(i);
			}
			term++;
		}
	}

	public static void main(String[] args) throws IOException {
		int prerequisiteCount, post;
		StringBuilder sb = new StringBuilder();

		degree = new int[subjectCount = read()];
		prerequisiteCount = read();
		graph = new boolean[subjectCount][subjectCount];
		for (int i = 0; i < prerequisiteCount; i++) {
			graph[read() - 1][post = read() - 1] = true;
			degree[post]++;
		}
		q = new ArrayDeque<>();
		res = new int[subjectCount];
		for (int i = 0; i < subjectCount; i++)
			if (degree[i] == 0)
				q.addLast(i);
		solve();
		for (int term : res)
			sb.append(term).append(' ');
		System.out.println(sb.toString());
	}
}