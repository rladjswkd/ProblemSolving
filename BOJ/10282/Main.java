
/*
 * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Using_a_priority_queue
 * 
 * Instead of filling the priority queue with all nodes in the initialization
 * phase, it is also possible to initialize it to contain only source; then,
 * inside the if alt < dist[v] block, the decrease_priority() becomes an
 * add_with_priority() operation if the node is not already in the queue.
 * 
 * -> https://people.mpi-inf.mpg.de/~mehlhorn/ftp/Toolbox/ShortestPaths.pdf 198 페이지
 * 없으면 추가하고 있으면 decrease_priority() 호출하는 방법인데,
 * 자바의 우선순위 큐에는 decrease_priority() 관련 함수가 없다.
 * 
 * Yet another alternative is to add nodes unconditionally to the priority queue
 * and to instead check after extraction (u ← Q.extract_min()) that it isn't
 * revisiting, or that no shorter connection was found yet in the if alt <
 * dist[v] block. This can be done by additionally extracting the associated
 * priority p from the queue and only processing further if p == dist[u] inside
 * the while Q is not empty loop.
 * 
 * 입출력 방식, 우선순위 큐 정렬을 위해 클래스의 compareTo를 재정의하거나 람다 함수로 생성자에 전달해주는 방식 등
 * 여러 가지 방식을 제출해 실행 시간을 비교했으므로, 백준에 가서 내 제출 확인하기
 */

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static StringBuilder sb = new StringBuilder();
	private static List<List<int[]>> graph;
	private static final int INFINITY = 10000 * 1000 + 1;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int computerCount, int hackedComputer) {
		int count = 0, time = 0;
		int[] costs = new int[computerCount], cur;
		// processed 없어도 통과한다.
		boolean[] processed = new boolean[computerCount];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

		for (int i = 0; i < computerCount; i++)
			costs[i] = INFINITY;
		costs[hackedComputer] = 0;
		pq.add(new int[] { hackedComputer, 0 });
		while (!pq.isEmpty()) {
			cur = pq.poll();
			if (processed[cur[0]]) // 또는 if (cur[1] == cost[cur[0]])
				continue;
			for (int[] neighbor : graph.get(cur[0])) {
				if (!processed[neighbor[0]] && costs[cur[0]] + neighbor[1] < costs[neighbor[0]]) {
					costs[neighbor[0]] = costs[cur[0]] + neighbor[1];
					pq.add(new int[] { neighbor[0], costs[neighbor[0]] });
				}
			}
			processed[cur[0]] = true;
			count++;
			time = Math.max(time, costs[cur[0]]);
		}
		sb.append(count).append(' ').append(time).append('\n');
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), computerCount, dependencyCount, hackedComputer, sub;

		while (t-- > 0) {
			computerCount = readInt();
			dependencyCount = readInt();
			hackedComputer = readInt() - 1;
			graph = new ArrayList<>();
			for (int i = 0; i < computerCount; i++)
				graph.add(new ArrayList<>());
			while (dependencyCount-- > 0) {
				sub = readInt() - 1;
				graph.get(readInt() - 1).add(new int[] { sub, readInt() });
			}
			solve(computerCount, hackedComputer);
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}