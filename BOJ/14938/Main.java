
/*
 * 플로이드 워셜
 * 
 * 문제 설명에서 시작 지점이 2일 때, 수색 범위 4를 가지고 1, 2, 3, 5번 지역에 방문해 23개의 아이템을 얻을 수 있다고 했으니
 * 2에서 1이나 3으로 이동한 후 남은 1의 수색 가능 범위를 가지고 다음 경로를 찾는 등의 생각을 할 필요가 없다.
 * 시작 지점으로부터 나머지 지점으로 가는 "각각의" 최단 경로를 구하면 된다.
 * 
 * 어느 지점에 떨어져야 가장 많은 아이템을 먹을 수 있을까? -> 각 지점에 대해 나머지 지점으로 가는 최단 경로
 * 
 * 처음에 가중치와 상관없이 BFS로 가능하지 않나 생각했었는데, 노드 번호에 따라 최단 경로로 방문하지 않을 수 있으므로 안된다.
 * 1 -> 2 : 1
 * 1 -> 3 : 5
 * 2 -> 3 : 1
 * 위와 같은 간선이 있을 때 큐로 BFS를 돌리면 방문 순서는 다음과 같다.
 * 1) 1->2
 * 2) 1->3
 * 3) 2->3
 * 여기서 1->2->3이 3을 방문하는 최단경로임에도 1->3이 먼저 방문처리되어 1에서 3으로 가는 최단 경로는 5로 계산된다.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int INFINITY = 100 * 15 + 1;
	private static int[] items;
	private static int[][] graph;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int solve(int zoneCount, int searchRange) {
		int maxItemCount = 0, currentItemCount;
		int[] costs;

		for (int via = 0; via < zoneCount; via++)
			for (int start = 0; start < zoneCount; start++)
				for (int end = 0; end < zoneCount; end++)
					graph[start][end] = Math.min(graph[start][end], graph[start][via] + graph[via][end]);
		for (int i = 0; i < zoneCount; i++) {
			currentItemCount = 0;
			costs = graph[i];
			for (int j = 0; j < zoneCount; j++)
				if (costs[j] <= searchRange)
					currentItemCount += items[j];
			maxItemCount = Math.max(maxItemCount, currentItemCount);
		}
		return maxItemCount;
	}

	public static void main(String[] args) throws IOException {
		int zoneCount = readInt(), searchRange = readInt(), roadCount = readInt();
		int u, v;

		items = new int[zoneCount];
		graph = new int[zoneCount][zoneCount];
		for (int i = 0; i < zoneCount; i++) {
			items[i] = readInt();
			Arrays.fill(graph[i], INFINITY);
			graph[i][i] = 0;
		}
		for (int i = 0; i < roadCount; i++) {
			u = readInt() - 1;
			v = readInt() - 1;
			graph[u][v] = graph[v][u] = readInt();
		}
		bw.append(String.valueOf(solve(zoneCount, searchRange))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}