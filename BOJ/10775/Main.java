
/*
 * i번째 비행기를 "1번부터 g_i번 게이트 중 하나에 도킹"
 * 한 비행기가 공항 내 어느 게이트에도 도킹할 수 없다면 그 시점부터 공항은 폐쇄
 * 
 * 주어진 g_i에 따라 가능한 뒤에 도킹하는 것이 좋지 않을까?
 * 근데 가능한 뒤에 도킹하는 것을 순차 탐색으로 찾으면 시간초과. 10^5 * 10^5
 * 예를 들어 입력이 아래와 같다면 시간 초과!
 * 10000
 * 10000
 * 10000
 * 10000
 * 10000
 * ...
 * 10000
 * 
 * -> 10000 * (10000 * 10001 / 2)
 * 
 * boolean 배열을 사용해 비행기를 도킹한 게이트는 true, 아닌 게이트는 false로 두고 가장 뒤의 false를 이분 탐색으로 찾기?
 * -> 예제 1번만 해도 1번 비행기를 4번 게이트, 2번 게이트를 1번 게이트에 도킹하면 배열이 true, false, false, true가 되는데 이는 정렬된 형태가 아니다..
 * 따라서 이분 탐색을 사용할 수 없다.
 * 
 * 어쨌든 탐색 시간을 O(n)보다 줄여야 하는데 방법을 생각해보자.
 * 세그먼트 트리? -> 각 범위 내에서 선택할 수 있는 가장 뒤 게이트 저장?
 * 세그먼트 트리에서는 true, false, false, true 와 같은 정렬되지 않은 상황에서 문제가 없을까?
 * 
 * 오 가능할 듯.
 * 도킹된 게이트를 나타내는 노드는 -1로 값을 설정하고, 그렇지 않은 노드는 자기 자신의 인덱스를 가리키게 한 후, 범위 내 검색시 무조건 큰 값을 반환하게 하면 될듯
 * 
 * ---
 * 
 * 문제 유형에는 분리 집합이 적혀있었다.
 * 분리 집합? -> g_i에 대해, g_i번째 집합과 g_i-1번째 집합을 union한 후, g_i-1번째 집합을 루트로 설정.
 * 만약 g_i-1번째 집합 또한 루트가 아니라면, g_i-2, g_i-3, ...을 계속 탐색해 루트로 설정
 */
import java.io.IOException;

public class Main {
	private static int g, p;
	private static int[] ds;

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

		if (uRoot == vRoot)
			return;
		else if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		int lastGate, res = 0;

		g = readInt();
		p = readInt();
		// 가장 앞인 0번 인덱스는 선택할 수 있는 게이트가 없다는 걸 나타내는 집합으로 사용하자.
		ds = new int[g + 1];
		for (int i = 0; i <= g; i++)
			ds[i] = -1;
		while (p-- > 0) {
			if ((lastGate = find(readInt())) == 0)
				break;
			// 루트는 1 ~ g_i 중 도킹할 수 있는 가장 큰 번호의 게이트를 나타낸다.
			union(lastGate - 1, lastGate);
			res++;
		}
		while (p-- > 0)
			readInt();
		System.out.println(res);
	}
}