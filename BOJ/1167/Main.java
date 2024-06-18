
/*
 * 임의의 노드로부터 가장 먼 노드는 무조건 지름의 끝 노드인가?
 *
 * 지름 D는 트리 내에서 가장 거리가 긴 두 정점 사이의 간선 가중치의 합이다.
 * 이 두 정점을 양 손으로 잡고 팽팽해질때까지 쭉 당긴 상황을 생각해보자.
 * 임의의 노드는 지름의 끝 노드와 그렇지 않은 노드로 나눌 수 있다.
 * 1. 지름의 끝 노드
 * 		주어진 조건에 따라 지름의 반대편 끝 노드와의 거리가 가장 멀기 때문에 지름의 끝 노드를 찾을 수 있다.
 * 2. 지름의 끝이 아닌 노드
 * 		팽팽해진 양 끝 노드의 지름을 선분이라 생각하고, 그 위의 어느 지점에 시작노드가 있다고 가정해보자.
 * 		이 노드를 기준으로 선분이 R, D - R로 나뉘고 이 노드가 지름으로부터 떨어진 거리를 X라고 할 때, X는 min(R, D-R)보다 클 수 없다.
 *		만약 크다면, 지름은 R이 아니라 max(R, D-R) + X가 될 것이다.
 *		이러한 상황에, 시작 노드에서 가장 먼 거리의 노드가 양 끝 노드가 아닌 다른 노드라고 가정해보자.
 *		그럼 지름으로부터 이 도착 노드까지의 거리 Y는 max(R, D-R)보다 클 수 밖에 없다.
 *		그럼 이 또한 지름이 D라는 가정에 오류가 생긴다.
 *		X <= min(R, D-R), max(R, D-R) <= Y가 되므로 지름이 R + D-R이 아니라 X + Y여야 하기 때문이다.
 *		물론 등호가 성립하는 상황에는 지름이 여러 개 존재하는 유효한 상황일 것이다.
 *	
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int endpoint = 0, maxDist = 0;
	private static List<List<int[]>> graph = new ArrayList<>();
	private static boolean[] visited;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			return ~n + 1;
		return n;
	}

	private static void findFurthestNode(int node, int currentDist) {
		visited[node] = true;
		if (maxDist < currentDist) {
			endpoint = node;
			maxDist = currentDist;
		}
		for (int[] next : graph.get(node))
			if (!visited[next[0]])
				findFurthestNode(next[0], currentDist + next[1]);
	}

	public static void main(String[] args) throws IOException {
		int v, other;
		List<int[]> node;

		v = readInt();
		visited = new boolean[v];
		for (int i = 0; i < v; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < v; i++) {
			node = graph.get(readInt() - 1);
			while ((other = readInt()) != -1)
				node.add(new int[] { other - 1, readInt() });
		}
		// 1번 노드에서 시작해 가장 먼 점을 찾고, 그 점에서 가장 먼 거리를 탐색
		findFurthestNode(0, 0);
		visited = new boolean[v];
		findFurthestNode(endpoint, 0);
		System.out.println(maxDist);
	}
}