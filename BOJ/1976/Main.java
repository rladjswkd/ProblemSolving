
/*
 * BFS?
 * 
 * 1. 여행 경로 상 현재 지점을 시작 지점, 다음 지점을 도착 지점으로 활용해 모든 경로 상 지점들에 대해 방문이 가능한지 반복적으로 확인 -> 비효율적
 * 
 * 2. visit 체크를 하며 N개의 도시를 순서대로 순회 -> 방문하지 않은 도시 i에 대해 bfs를 호출해 모든 연결된 도시를 방문하며 모두(i 포함) 새로운 set에 담기
 * -> 여행 계획의 도시들 중 하나라도 다른 집합에 포함된 경우 NO 출력 -> 이러느니 disjoint set
 * 
 * disjoint set
 * 
 * 모든 연결 정보를 활용해 도시들로 구성된 여러 집합을 생성하고 여행 경로의 지점들이 동일한 집합에 포함되었는지 확인
 * 경로 가중치는 모두 동일하므로 우선순위 큐는 사용하지 않아도 된다.
 * 
 * 한 도시에 여러 번 방문 가능하고, 최단 거리 등이 아닌 방문할 수 있는지가 중점이므로 disjoint set도 가능
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] ds;
	private static boolean res = true;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int find(int node) {
		int root = node, upper;

		while (ds[root] >= 0)
			root = ds[root];
		while (ds[node] >= 0) {
			upper = ds[node];
			ds[node] = root;
			node = upper;
		}
		return root;
	}

	private static void union(int u, int v) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return;
		if (ds[uRoot] < ds[vRoot]) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		int cityCount = readInt(), connection;
		int[] plan = new int[readInt()], cur;
		Deque<int[]> q = new ArrayDeque<>();

		for (int i = 0; i < cityCount; i++) {
			for (int j = 0; j < cityCount; j++) {
				connection = br.read() - 48;
				br.read();
				if (j > i && connection == 1)
					q.addLast(new int[] { i, j });
			}
		}
		ds = new int[cityCount];
		for (int i = 0; i < cityCount; i++)
			ds[i] = -1;
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			union(cur[0], cur[1]);
		}
		for (int i = 0; i < plan.length; i++)
			plan[i] = readInt() - 1;
		for (int i = 1; i < plan.length && res; i++)
			if (find(plan[i - 1]) != find(plan[i]))
				res = false;
		bw.write(res ? "YES\n" : "NO\n");
		bw.flush();
		br.close();
		bw.close();
	}
}