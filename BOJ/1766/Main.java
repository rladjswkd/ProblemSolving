
/*
 * 위상 정렬
 * 
 * 가능하면 쉬운 문제부터 풀기 -> 위상 정렬 결과가 여러 개가 아닌 하나다.
 * 
 * dfs로 위상 정렬 구현하고, dfs로 접근할 때도 어려운 문제를 먼저 접근 -> 쉬운 문제가 앞에 정렬될 수 있도록.
 * 
 * 이전 반례는 통과했지만, 실패.
 * 3 2
 * 3 1
 * 3 2
 * 
 * 우선순위 큐를 적용한 kahn's algorithm 방식을 사용해야 풀 수 있는 문제같다... dfs로는 좀 복잡해지는 것 같다.
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	private static List<List<Integer>> relation = new ArrayList<>();
	private static int[] res;
	private static int idx;
	private static int[] degree;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int problemCount = readInt(), relationCount = readInt(), post;
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		degree = new int[problemCount];
		for (int i = 0; i < problemCount; i++)
			relation.add(new ArrayList<>());
		for (int i = 0; i < relationCount; i++) {
			relation.get(readInt() - 1).add((post = readInt() - 1));
			degree[post]++;
		}
		res = new int[problemCount];
		idx = 0;
		for (int i = 0; i < problemCount; i++)
			if (degree[i] == 0)
				pq.add(i);
		while (!pq.isEmpty()) {
			// post 재활용. 그냥 문제 번호를 담는데 쓴다.
			post = pq.poll();
			res[idx++] = post + 1;
			for (int next : relation.get(post))
				if (--degree[next] == 0)
					pq.add(next);
		}
		sb.append(res[0]);
		for (int i = 1; i < problemCount; i++)
			sb.append(' ').append(res[i]);
		System.out.println(sb.toString());
	}
}