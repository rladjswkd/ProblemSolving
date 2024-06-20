
/*
 * LCA
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static List<List<Integer>> graph;
	private static int node1, node2;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int solve(int node) {
		// val은 node의 하위 노드들이 반환하는 값을 더하는 변수.
		// val > node1 && val > node2면 node의 서브트리에 node1, node2가 모두 존재한다는 뜻. 현재 node를 반환
		// val == node1이면 서브 트리에 node1이 존재한다는 뜻 -> node1을 반환
		// val == node2면 서브 트리에 node2가 존재한다는 뜻 -> node2를 반환
		// val == 0이면 node1도 node2도 서브 트리에 존재하지 않는다는 뜻 -> 0 반환
		int val = 0;

		if (node == node1)
			return node1;
		if (node == node2)
			return node2;
		// if (graph.get(node).isEmpty())
		// return 0;
		for (int sub : graph.get(node))
			val += solve(sub);
		if (val == node1 + node2)
			return node;
		return val;
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), n, root, child;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			n = readInt();
			// root는 모든 노드의 값을 더한 값으로 초기화한 후, 주어지는 연결 정보에 따라 하위 노드들의 값을 빼면 남는 노드의 값이다.
			root = n * (n - 1) / 2;
			graph = new ArrayList<>();
			for (int i = 0; i < n; i++)
				graph.add(new ArrayList<>());
			for (int i = 1; i < n; i++) {
				graph.get(readInt() - 1).add((child = readInt() - 1));
				root -= child;
			}
			node1 = readInt() - 1;
			node2 = readInt() - 1;
			sb.append(solve(root) + 1).append('\n');
		}
		System.out.print(sb.toString());
	}
}