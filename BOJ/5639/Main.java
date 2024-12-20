import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static StringBuilder sb = new StringBuilder();

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	/*
	 * 1. 왼쪽 서브트리만 존재하는 경우:
	 * rSubStart는 end + 1이 되며, 이에 따라
	 * traversePostOrder(graph, start + 1, rSubStart - 1)은
	 * traversePostOrder(graph, start + 1, end)가 된다.
	 * traversePostOrder(graph, rSubStart, end)는
	 * traversePostOrder(graph, end + 1, end)가 된다.
	 * 
	 * 2. 오른쪽 서브 트리만 존재하는 경우:
	 * rSubStart는 초기값인 start + 1이 되며, 이에 따라
	 * traversePostOrder(graph, start + 1, rSubStart - 1)은
	 * traversePostOrder(graph, start + 1, start)가 된다.
	 * traversePostOrder(graph, rSubStart, end)는
	 * traversePostOrder(graph, start + 1, end)가 된다.
	 * 
	 * 3. 둘 다 존재하는 경우:
	 * rSubStart는 [start + 2, end] 범위 중 하나가 되며
	 * traversePostOrder(graph, start + 1, rSubStart - 1)은
	 * traversePostOrder(graph, start + 1, start + 1)
	 * ~ traversePostOrder(graph, start + 1, end - 1)이 된다.
	 * traversePostOrder(graph, rSubStart, end)는
	 * traversePostOrder(graph, start + 2, end)
	 * ~ traversePostOrder(graph, end, end)가 된다.
	 * 
	 * 4. 둘 다 존재하지 않는 경우(분할 정복 로직 상 start == end인 상황):
	 * 현재 노드 출력하고 반환
	 */
	private static void traversePostOrder(List<Integer> graph, int start, int end) {
		int cur = graph.get(start), rSubStart = start + 1;

		while (rSubStart <= end && graph.get(rSubStart) < cur)
			rSubStart++;
		// 왼쪽 서브 트리
		if (start + 1 <= rSubStart - 1)
			traversePostOrder(graph, start + 1, rSubStart - 1);
		// 오른쪽 서브 트리
		if (rSubStart <= end)
			traversePostOrder(graph, rSubStart, end);
		// 현재 노드
		sb.append(cur).append('\n');
	}

	public static void main(String[] args) throws IOException {
		int val;
		List<Integer> graph = new ArrayList<>();

		while ((val = readInt()) != 0)
			graph.add(val);
		traversePostOrder(graph, 0, graph.size() - 1);
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}