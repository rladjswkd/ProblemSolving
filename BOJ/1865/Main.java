import java.io.IOException;

public class Main {
	private static int pointCount, edgeCount;
	private static int[] costs = new int[500];
	private static int[][] edges = new int[5200][3];
	private static final int INFINITY = 500 * 10000 + 1;
	private static final String YES = "YES", NO = "NO";

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	// 원래 벨만-포드 로직대로라면 start에서 출발해 아직 방문하지 않은 노드라면 relaxation에서 고려하지 않아야 하므로
	// 라인 28과 35에서 costs[edge[0]] != INFINITY를 먼저 확인한 후 costs[edge[0]] + edge[2] <
	// costs[edge[1]]을 확인해야 한다.
	// 하지만 이 문제는 단순히 해당 그래프 내에 어디든지 "음의 간선이 존재하냐"를 묻는 것이기 때문에, 이를 확인하지 않고
	// relaxation에서 모든 노드를 고려한다.
	// 이렇게 하지 않고 원래 벨만-포드 로직을 사용하며 모든 노드를 시작 지점으로 하여 확인하면 시간초과가 발생한다.
	// 문제가 좋진 않은 것 같다.
	private static boolean solve(int start) {
		int[] edge;

		for (int i = 0; i < pointCount; i++)
			costs[i] = INFINITY;
		costs[start] = 0;
		for (int i = 0; i < pointCount; i++) {
			for (int j = 0; j < edgeCount; j++) {
				edge = edges[j];
				if (costs[edge[0]] + edge[2] < costs[edge[1]])
					costs[edge[1]] = costs[edge[0]] + edge[2];
			}
		}
		for (int i = 0; i < edgeCount; i++) {
			edge = edges[i];
			if (costs[edge[0]] + edge[2] < costs[edge[1]])
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), roadCount, wormholeCount;
		boolean flag;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			pointCount = readInt();
			edgeCount = (roadCount = (readInt() << 1)) + (wormholeCount = readInt());
			// 양방향. 자기 자신이 자기 자신으로 가는 간선에 대한 정보가 들어왔을 때 올바르지 않은 연산을 하게 되나? 그렇지는 않다.
			for (int i = 0; i < roadCount; i += 2) {
				edges[i][0] = edges[i + 1][1] = readInt() - 1;
				edges[i][1] = edges[i + 1][0] = readInt() - 1;
				edges[i][2] = edges[i + 1][2] = readInt();
			}
			for (int i = 0; i < wormholeCount; i++) {
				edges[roadCount + i][0] = readInt() - 1;
				edges[roadCount + i][1] = readInt() - 1;
				edges[roadCount + i][2] = ~readInt() + 1;
			}
			flag = true;
			// 어느 지점에서 시작하든 상관없다.
			flag = solve(0);
			sb.append(flag ? NO : YES).append('\n');
		}
		System.out.print(sb.toString());
	}
}