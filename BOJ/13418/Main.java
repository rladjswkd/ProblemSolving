import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static int buildingCount, roadCount;
	private static PriorityQueue<int[]> worstPQ;
	private static PriorityQueue<int[]> bestPQ;
	private static int[] dsWorst, dsBest;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int find(int[] ds, int node) {
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

	private static boolean union(int[] ds, int u, int v) {
		int uRoot = find(ds, u), vRoot = find(ds, v);

		if (uRoot == vRoot)
			return false;
		if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		int[] road;
		int worst, best, setCount;

		buildingCount = readInt();
		roadCount = readInt();
		// 입구와 1번 건물은 오르막, 내리막에 관계 없이 무조건 연결해야 하므로 별도로 저장한다.
		// 건물 번호는 필요하지 않고 오르막, 내리막 정보만 있으면 되므로 이를 저장.
		// 대신 x번 건물을 x-1번 건물로 간주한다.
		readInt();
		readInt();
		worst = best = readInt();
		worstPQ = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		bestPQ = new PriorityQueue<>((a, b) -> b[2] - a[2]);
		for (int i = 0; i < roadCount; i++) {
			worstPQ.add(road = new int[] { readInt() - 1, readInt() - 1, readInt() });
			bestPQ.add(road);
		}
		dsWorst = new int[buildingCount];
		dsBest = new int[buildingCount];
		for (int i = 0; i < buildingCount; i++)
			dsWorst[i] = dsBest[i] = -1;
		setCount = buildingCount;
		while (!worstPQ.isEmpty() && setCount > 1) {
			road = worstPQ.poll();
			if (union(dsBest, road[0], road[1]))
				worst += road[2];
		}
		setCount = buildingCount;
		while (!bestPQ.isEmpty() && setCount > 1) {
			road = bestPQ.poll();
			if (union(dsWorst, road[0], road[1]))
				best += road[2];
		}
		// worst가 오르막길 우선으로 경로를 짠 결과고 best가 내리막길 우선으로 경로를 짠 결과다.
		// 0이 오르막길, 1이 내리막길이므로 실제 피로도를 계산하기 위해 오르막길의 개수를 세려면 총 도로의 수에서 worst, best의 값을
		// 빼야한다.
		// 입구와 1번 건물을 잇는 도로까지 포함하면 도로는 총 buildingCount개다.
		best = buildingCount - best;
		worst = buildingCount - worst;
		System.out.println((worst + best) * (worst - best));
	}
}