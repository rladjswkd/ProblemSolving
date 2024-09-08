import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
	private static int personCount, relCount, INFINITY = 101;
	private static List<List<Integer>> graph;
	private static int[] ds;
	private static Deque<Integer> q;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int find(int node) {
		int root = node, upper;

		while (ds[root] >= 0)
			root = ds[root];
		while (ds[node] >= 0) {
			upper = ds[node];
			ds[node] = root;
			node = upper;
		}
		return root;
	}

	private static boolean union(int u, int v) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return false;
		if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
		return true;
	}

	private static int measureTime(int start) {
		int cur, size, time = 0;
		boolean[] visited = new boolean[personCount];

		q.add(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				cur = q.removeFirst();
				for (int adj : graph.get(cur)) {
					if (!visited[adj]) {
						visited[adj] = true;
						q.addLast(adj);
					}
				}
			}
			time++;
		}
		return time - 1;
	}

	public static void main(String[] args) throws IOException {
		int setCount, u, v, idx, time;
		int[] answer, times, mapper;
		StringBuilder sb = new StringBuilder();

		setCount = personCount = readInt();
		relCount = readInt();
		graph = new ArrayList<>();
		ds = new int[personCount];
		for (int i = 0; i < personCount; i++) {
			graph.add(new ArrayList<>());
			ds[i] = -1;
		}
		for (int i = 0; i < relCount; i++) {
			graph.get(u = readInt() - 1).add(v = readInt() - 1);
			graph.get(v).add(u);
			if (union(u, v))
				setCount--;
		}
		// 1-base
		mapper = new int[personCount];
		idx = 1;
		for (int i = 0; i < personCount; i++)
			if (ds[i] < 0)
				mapper[i] = idx++;
		answer = new int[setCount];
		times = new int[setCount];
		for (int i = 0; i < setCount; i++)
			times[i] = INFINITY;
		q = new ArrayDeque<>();
		for (int i = 0; i < personCount; i++) {
			idx = mapper[find(i)] - 1;
			if ((time = measureTime(i)) < times[idx]) {
				times[idx] = time;
				answer[idx] = i + 1;
			}
		}
		Arrays.sort(answer);
		sb.append(setCount).append('\n');
		for (int leader : answer)
			sb.append(leader).append('\n');
		System.out.print(sb.toString());
	}
}