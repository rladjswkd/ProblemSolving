import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int INFINITY = Integer.MAX_VALUE;
	private static final int UNDEFINED = -1;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int v = readInt(), e = readInt(), startNode = readInt() - 1, alt;
		int[] dist = new int[v], prev = new int[v], cur;
		List<List<int[]>> graph = new ArrayList<>();
		// 서로 다른 두 정점 사이에 여러 간선이 존재할 수 있다. -> HashMap을 쓰면 이 중 하나의 간선만 표현됨
		// Map<Integer, List<int[]>> graph = new HashMap<>((int) (v / 0.75 + 1));
		PriorityQueue<int[]> pq = new PriorityQueue<>(v, (a, b) -> a[1] - b[1]);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < v; i++) {
			graph.add(new ArrayList<>());
			dist[i] = INFINITY;
			prev[i] = UNDEFINED;
		}
		for (int i = 0; i < e; i++)
			graph.get(readInt() - 1).add(new int[] { readInt() - 1, readInt() });
		dist[startNode] = 0;
		pq.add(new int[] { startNode, 0 });
		while (!pq.isEmpty()) {
			cur = pq.poll();
			for (int[] neighbor : graph.get(cur[0])) {
				alt = dist[cur[0]] + neighbor[1];
				if (alt < dist[neighbor[0]]) {
					prev[neighbor[0]] = cur[0];
					dist[neighbor[0]] = alt;
					pq.add(new int[] { neighbor[0], alt });
				}
			}
		}
		for (int val : dist) {
			if (val == INFINITY)
				sb.append("INF\n");
			else
				sb.append(val).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}