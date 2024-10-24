import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int personCount;
	private static List<List<Integer>> graph;
	private static int[] scores;
	private static Deque<Integer> q;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int start) {
		boolean[] visited = new boolean[personCount];
		int cur, size, dist = 0;

		q.addLast(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				for (int neighbor : graph.get(cur)) {
					if (!visited[neighbor]) {
						visited[neighbor] = true;
						q.addLast(neighbor);
					}
				}
			}
			dist++;
		}
		scores[start] = dist - 1;
	}

	public static void main(String[] args) throws IOException {
		int u, v, score, counter = 1;
		StringBuilder sb = new StringBuilder();

		graph = new ArrayList<>(personCount = read());
		for (int i = 0; i < personCount; i++)
			graph.add(new ArrayList<>());
		while ((u = read() - 1) != 130 && (v = read() - 1) != 130) {
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		scores = new int[personCount];
		q = new ArrayDeque<>();
		for (int i = 0; i < personCount; i++)
			solve(i);
		score = personCount + 1;
		for (int i = 0; i < personCount; i++) {
			if (scores[i] < score) {
				score = scores[i];
				counter = 1;
			} else if (scores[i] == score)
				counter++;
		}
		sb.append(score).append(' ').append(counter).append('\n');
		for (int i = 0; i < personCount; i++)
			if (scores[i] == score)
				sb.append(i + 1).append(' ');
		System.out.println(sb.toString());
	}
}