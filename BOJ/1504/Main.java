
/*
 * 방향성이 없는 그래프!! -> 양방향!!
 * 거쳐야 할 두 정점을 v1, v2라고 하면
 * 1 -> v1 -> v2 -> n과 1 -> v2 -> v1 -> n 경로를 이동하는데 필요한 비용을 다익스트라 알고리즘으로 구하여 비교한다.
 * 
 * 둘 중 작은 값을 출력한다.
 * 
 * 경로 상의 한 노드에서 다음 노드까지 이동하는 데엔 방문했던 노드를 다시 방문할 필요가 없다.
 * 방문한 노드를 다시 방문할 필요가 있는 경우는 예를 들어 1 -> v1를 이동하는 최단 경로 상에서 방문한 노드가 v1 -> v2로 이동하는 최단 경로에 포함된 경우일 것이다.
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static List<List<int[]>> graph = new ArrayList<>();
	private static final int INFINITY = Integer.MAX_VALUE;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int findLowestCostNode(int[] costs, boolean[] processed) {
		int lowestCostNode = -1, lowestCost = INFINITY;

		for (int i = 0; i < costs.length; i++) {
			if (!processed[i] && costs[i] < lowestCost) {
				lowestCostNode = i;
				lowestCost = costs[i];
			}
		}
		return lowestCostNode;
	}

	private static long findShortestPath(int from, int to) {
		boolean[] processed = new boolean[graph.size()];
		int[] costs = new int[graph.size()];
		int cur, newCost;

		for (int i = 0; i < costs.length; i++)
			costs[i] = INFINITY;
		costs[from] = 0;
		while ((cur = findLowestCostNode(costs, processed)) != -1) {
			for (int[] neighbor : graph.get(cur)) {
				newCost = neighbor[1] + costs[cur];
				if (!processed[neighbor[0]] && newCost < costs[neighbor[0]])
					costs[neighbor[0]] = newCost;
			}
			processed[cur] = true;
		}
		return costs[to];
	}

	private static int solve(int n, int v1, int v2) {
		// findShortestPath(v1, v2) == findShortestPath(v2, v1)
		long dist = findShortestPath(v1, v2),
				path1 = findShortestPath(0, v1) + dist + findShortestPath(v2, n - 1),
				path2 = findShortestPath(0, v2) + dist + findShortestPath(v1, n - 1);
		// 아래와 같은 반례 때문에 등호도 넣어야 한다.
		// 2 0
		// 1 2
		if (path1 >= INFINITY && path2 >= INFINITY)
			return -1;
		else
			return (int) Math.min(path1, path2);
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), e = readInt(), node1, node2, distance;

		for (int i = 0; i < n; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < e; i++) {
			node1 = readInt() - 1;
			node2 = readInt() - 1;
			distance = readInt();
			graph.get(node1).add(new int[] { node2, distance });
			graph.get(node2).add(new int[] { node1, distance });
		}
		bw.append(String.valueOf(solve(n, readInt() - 1, readInt() - 1))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}