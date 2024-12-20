import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	// connectedCount: 발전소에 직, 간접적으로 연결되어 전기를 공급받을 수 있는 도시의 수
	private static int cityCount, cableCount, connectedCount;
	private static int[] powerPlants, ds;
	// 각 도시 별 발전소에 연결되어 전기를 공급받을 수 있는지 여부를 저장
	private static boolean[] connected;
	private static PriorityQueue<int[]> pq;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
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

	private static boolean union(int u, int v) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return false;
		// 발전소에 연결된 노드를 루트로 활용
		if (connected[uRoot]) {
			ds[uRoot] += ds[vRoot];
			connectedCount -= ds[vRoot];
			ds[vRoot] = uRoot;
		} else if (connected[vRoot]) {
			ds[vRoot] += ds[uRoot];
			connectedCount -= ds[uRoot];
			ds[uRoot] = vRoot;
		} else {
			if (uRoot < vRoot) {
				ds[uRoot] += ds[vRoot];
				ds[vRoot] = uRoot;
			} else {
				ds[vRoot] += ds[uRoot];
				ds[uRoot] = vRoot;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		int[] cur;
		int res = 0;

		cityCount = readInt();
		cableCount = readInt();
		connectedCount = readInt();
		powerPlants = new int[connectedCount];
		connected = new boolean[cityCount];
		for (int i = 0; i < connectedCount; i++)
			connected[powerPlants[i] = readInt() - 1] = true;
		ds = new int[cityCount];
		for (int i = 0; i < cityCount; i++)
			ds[i] = -1;
		pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		for (int i = 0; i < cableCount; i++)
			pq.add(new int[] { readInt() - 1, readInt() - 1, readInt() });
		while (!pq.isEmpty() && connectedCount < cityCount) {
			cur = pq.poll();
			// 두 도시 모두 이미 발전소에 연결되어있다면 넘기기
			if (connected[find(cur[0])] && connected[find(cur[1])])
				continue;
			// 현재 뽑은 케이블로 연결되는 두 도시가 모두 발전소에 연결되지 않은 도시일 수 있고, 이 경우엔 두 도시를 연결하고 난 후에도
			// connected[cur[0]], connected[cur[1]] 모두 false이므로 connected 배열을 쓴다고 해서 분리 집합
			// 관련 로직을 제거할 수 없다.
			if (union(cur[0], cur[1]))
				res += cur[2];
		}
		System.out.println(res);
	}
}
