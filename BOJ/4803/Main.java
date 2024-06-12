
/*
 * 문제 설명
 * 
 * 예제 1
 * 
 * 6 3
 * 1 2
 * 2 3
 * 3 4
 * 
 * 1-2-3-4로 구성된 트리 1개, 5로 구성된 트리 1개, 6으로 구성된 트리 1개로 총 3개
 * 
 * 6 5
 * 1 2
 * 2 3
 * 3 4
 * 4 5
 * 5 6
 * 
 * 1-2-3-4-5-6 으로 구성된 트리 1개로 총 1개
 * 
 * 6 6
 * 1 2
 * 2 3
 * 1 3
 * 4 5
 * 5 6
 * 6 4
 * 
 * 1, 2, 3과 4, 5, 6이 사이클을 구성하기 때문에 총 0개의 트리
 * 0 0
 * 
 * "같은 간선이 여러 번 주어지지 않는다"
 * 
 * disjoin-set을 응용해서 풀 수 있을 것 같다.
 * 
 * 사이클이 생기지 않는다면 트리의 수는 (주어진 정점의 수 - 주어진 간선의 수)다.
 * 하지만 사이클이 생긴다면 (사이클이 생긴 그래프의 정점을 모두 뺀 나머지 정점의 수)다.
 * 
 * 간선을 반영하기 전에 트리의 개수는 정점의 수와 같다.
 * 이후 union을 할 때마다 트리의 개수를 1씩 뺀다.
 * 그러다 만약 union을 시도할 때 두 정점의 루트가 같다면 사이클이 생긴 것이고, 이때도 1을 빼준다.
 * 
 * 사이클이어도 1빼고 유효한 트리를 구성하는 간선이어도 1빼는 거면 이거 그냥 정점의 수 - 간선의 수 해도 되지 않나?
 * 
 * 당연히 안되지! 정점이 n개일 때 간선이 n(n - 1) / 2까지 들어오니까! 당연히 38 ~ 43 줄의 생각도 틀렸다. 간선의 수가 더 많을 수 있으니까.
 * 
 * 트리인 정점을 HashSet으로 추적 -> 사이클이 생기면 해당 정점을 제거
 * 또는 그냥 사이즈가 정점의 개수인 boolean[]을 사용해서 정점이면 true, 아니면 false로 설정.
 * 
 * HashSet vs boolean[] 누가 더 빠른지 테스트해보기
 * 정점의 개수만큼 순회해야 함에도 불구하고 boolean[]이 더 빠르다.
 */
import java.io.IOException;

public class Main {
	private static int[] ds;
	// 트리의 루트 정점들을 추적하려면 초반에 모두 true로 설정해줘야 하므로, 이를 피하기 위해 루트가 아닌 정점들을 추적(false면 루트)
	private static boolean[] nonRoots;
	private static final String CASE = "Case ",
			FOREST = ": A forest of ",
			TREES = " trees.\n",
			ONE_TREE = ": There is one tree.\n",
			NO_TREE = ": No trees.\n";

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

	private static void union(int u, int v) {
		int uRoot = find(u), vRoot = find(v);

		// 사이클 발생
		if (uRoot == vRoot) {
			nonRoots[uRoot] = true;
			return;
		}
		// 간선 연결(사이클이 존재하는 그래프에 연결하는 것일 수도, 유효한 트리에 연결하는 것일 수도 있다.)
		if (uRoot < vRoot) {
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!이 부분이
			// 중요하다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// 연결 후 vRoot는 당연히 root가 아니게 되지만,
			// vRoot가 이미 사이클이 있는 그래프의 루트라면 uRoot도 트리의 루트가 될 수 없다.
			if (nonRoots[vRoot])
				nonRoots[uRoot] = true;
			else
				nonRoots[vRoot] = true;
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!마찬가지!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			if (nonRoots[uRoot])
				nonRoots[vRoot] = true;
			else
				nonRoots[uRoot] = true;
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		int vertexCount, edgeCount, treeCount, caseCount = 0;
		StringBuilder sb = new StringBuilder();

		while ((vertexCount = readInt()) + (edgeCount = readInt()) != 0) {
			caseCount++;
			ds = new int[vertexCount];
			nonRoots = new boolean[vertexCount];
			for (int i = 0; i < vertexCount; i++)
				ds[i] = -1;
			for (int i = 0; i < edgeCount; i++)
				union(readInt() - 1, readInt() - 1);
			treeCount = 0;
			for (boolean rootFlag : nonRoots)
				if (!rootFlag)
					treeCount++;
			if (treeCount == 0)
				sb.append(CASE).append(caseCount).append(NO_TREE);
			else if (treeCount == 1)
				sb.append(CASE).append(caseCount).append(ONE_TREE);
			else
				sb.append(CASE).append(caseCount).append(FOREST).append(treeCount).append(TREES);
		}
		System.out.print(sb.toString());
	}
}