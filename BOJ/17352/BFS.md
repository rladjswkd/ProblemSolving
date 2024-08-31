```java
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n;
	private static List<List<Integer>> graph;
	private static StringBuilder sb;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n];
		int cur = 0;

		q.addLast(0);
		visited[0] = true;
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			for (int neighbor : graph.get(cur)) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					q.addLast(neighbor);
				}
			}
		}
		// cur 재활용
		for (int i = 1; i < n; i++) {
			if (!visited[i]) {
				cur = i;
				break;
			}
		}
		sb.append(1).append(' ').append(cur + 1);
	}

	public static void main(String[] args) throws IOException {
		int u, v;

		graph = new ArrayList<>(n = readInt());
		for (int i = 0; i < n; i++)
			graph.add(new ArrayList<>());
		for (int i = 2; i < n; i++) {
			graph.get(u = readInt() - 1).add(v = readInt() - 1);
			graph.get(v).add(u);
		}
		sb = new StringBuilder();
		solve();
		System.out.println(sb.toString());
	}
}
```
