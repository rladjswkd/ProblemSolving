
/*
 * 예제 입력 1에 대한 처리
 * 각 도시(행)에서 출발해 그리디한 방법으로 비용이 가장 적은 도시를 선택한다.
 * 모든 도시를 방문한 후 자신에게 돌아올 때까지 반복
 * 
 * 각 행에 대해 순회하며 DFS 호출 -> O(N)
 * DFS() { -> O(N + E)
 *	 	방문하지 않은 도시 중 방문할 수 있으면서(비용 != 0) 비용이 가장 적은 도시 탐색 -> O(N)
 *	 		DFS()
 * }
 * 
 * 따라서 O(N^3 + EN^2)
 * 
 * 이 방식이 맞는 방식이라면 시간 초과 문제는 없다.
 * 
 * 그런데, 한 도시에서 출발해 모든 도시를 돌고 다시 그 도시로 돌아가는 최소 비용 경로가 항상 비용이 가장 적은 도시로 이동하는 경로임이 확실한가?
 * 
 * 0 5 6 9
 * 9 0 6 5
 * 9 5 0 7
 * 8 9 9 0
 * 
 * 1, 2, 3, 4 도시에서 각각 5, 6, 7, 8 비용을 갖는 이웃 도시를 선택했다고 하자.
 * 하지만 정답은 1, 2, 3, 4 도시 각각이 6, 5, 5, 8 비용을 갖는 이웃 도시를 선택하는 것이다.
 * 그럼 1에서는 최소 비용인 도시를 선택하지 않는 걸까?
 * 
 * 그렇지 않다. 
 * * 2번 도시를 먼저 선택하면 된다.
 * 2번 도시가 비용이 최소인 5를 선택하고, 4번 도시가 최소인 8을 선택하고, 1번 도시가 최소인 6(방문한 5는 제외)을 선택한다.
 * 
 * 모든 도시를 출발점으로 하여 탐색하므로 문제 없어 보인다.
 * ---
 * 
 * 인덱스를 활용해 각 "행"에 대해 최솟값을 선택하는 반복문 연산으로 DFS 재귀 호출을 대신할 수 있다.
 * 
 * ---
 * 
 * 다른 걸 떠나서 도시 간 이동 비용이 같은 도시가 여러 개일 수 있다.
 * DFS로 어떤 도시를 선택 해야할 지 알 수 없으므로, 비용이 같은 모든 도시를 다 돌아야 한다.
 * -> O(N^3 + EN^2)보다 더 걸릴 수 있다. 
 * 
 * ---
 * 
 * 위에서 고민했던 방법은 정답을 구하는 방법이 될 수 없다. 알고리즘 책들에서 말하는 approximate한 방법일 뿐이다.
 * 
 * dp를 이용한 방법으로 O(n^2 * 2^n) 시간에 풀 수 있다.
 */
import java.io.IOException;

public class Main {
	private static int n, res;
	private static int[][] w, dp;
	private static final int MAX_COST = 1000000 * 16;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	/*
	 * 출발 도시를 0번 도시로 정하고, 0번 도시로 돌아올 lastCity를 설정한 후, lastCity로 갈 그 전 도시를 설정하고,...
	 * 반복
	 */
	private static int solve(int city, int citiesToVisitMask) {
		int res;

		// 여기서의 city는 이전 호출에서의 prevCity다.

		// base case
		// 비트마스크에 city만 남아있다면 나머지 도시를 모두 방문한 상태이므로 별도로 체크
		if (citiesToVisitMask == 1 << city) {
			// 출발 도시에서 city로 갈 수 있다면 그 비용을 반환
			if (w[0][city] > 0)
				return w[0][city];
			// 그렇지 않다면 이 경로가 선택되면 안되기 때문에 최대 비용을 반환
			else
				return MAX_COST;
		}

		// 메모이제이션 활용. dp[city][citiesToVisitMask]에 값이 채워져있다면 이를 반환
		if (dp[city][citiesToVisitMask] != 0)
			return dp[city][citiesToVisitMask];
		// res를 최대값으로 초기화
		res = MAX_COST;
		for (int prevCity = 1; prevCity < n; prevCity++)
			// prevCity를 아직 방문하지 않았고, prevCity가 city가 아니면서, prevCity에서 city를 방문할 수 있을 때 재귀
			// 호출
			if ((citiesToVisitMask & 1 << prevCity) != 0 && city != prevCity && w[prevCity][city] > 0)
				res = Math.min(res, solve(prevCity, citiesToVisitMask ^ 1 << city) + w[prevCity][city]);
		return dp[city][citiesToVisitMask] = res;
	}

	public static void main(String[] args) throws IOException {
		n = readInt();
		w = new int[n][n];
		// 1 << n으로 차원 크기를 정해야 하위 n개의 비트인 0 ~ n - 1 비트를 활용할 수 있다.
		dp = new int[n][1 << n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				w[i][j] = readInt();
		res = MAX_COST;
		// 출발 도시를 0번 도시로 설정하고, 0번 도시와 city 사이의 모든 도시를 방문한 후, city에서 0번 도시로 돌아오는 비용을 더한
		// 값과 res를 비교해 둘 중 작은 값으로 res 업데이트
		// 출발 도시를 고정한다고? -> 최소 비용 경로가 존재한다면, 출발 지점이 어디든 그 비용은 같기 때문에 고정이 가능하다.
		// 0번이 아닌 다른 도시로 설정해도 똑같다.
		for (int lastCity = 1; lastCity < n; lastCity++)
			// lastcity에서 0으로 돌아올 수 있는 경우에만 호출
			if (w[lastCity][0] > 0)
				// (1 << n) - 1로 모든 도시에 대해 비트마스킹을 한다. 이후 출발 도시인 0번 도시를 마스크에서 제외하기 위해 1을 더 뺀다.
				res = Math.min(res, solve(lastCity, (1 << n) - 2) + w[lastCity][0]);
		System.out.println(res);
	}
}