
/*
 * 위상 정렬
 * 키 순서에는 사이클이 존재할 수 없다.
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	private static List<List<Integer>> graph;
	private static boolean[] visited;
	private static Deque<Integer> order;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int id) {
		visited[id] = true;
		for (int next : graph.get(id))
			if (!visited[next])
				solve(next);
		order.addFirst(id + 1);
	}

	public static void main(String[] args) throws IOException {
		int studentCount = readInt(), compCount = readInt();
		StringBuilder sb = new StringBuilder();

		visited = new boolean[studentCount];
		graph = new ArrayList<>();
		order = new LinkedList<>();
		for (int i = 0; i < studentCount; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < compCount; i++)
			graph.get(readInt() - 1).add(readInt() - 1);
		for (int i = 0; i < studentCount; i++)
			if (!visited[i])
				solve(i);
		for (int o : order)
			sb.append(o).append(' ');
		System.out.println(sb.toString());
	}
}