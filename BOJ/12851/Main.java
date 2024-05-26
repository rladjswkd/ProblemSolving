
/*
 * dp
 * 
 * 수빈이의 위치가 X일 때, dp[X] = dp[X - 1], dp[X + 1], dp[2 * X] 중 최소값
 * 
 * -> dp[X - 1]은 dp[X]보다 이전, dp[X + 1]과 dp[2 * X]는 X보다 이후인데 어떤 순서로 dp를 계산해 나아가야 할까? 일단 스킵
 * 
 * dp + BFS로 풀 수 있을 것 같다.
 * 수빈이의 시작 지점 N을 큐에 넣고 이동 시간을 0으로 설정(dp 배열로 저장)한 후 BFS 시작.
 * - 동생의 위치 K는 0 이상이므로 수빈이가 이동할 다음 위치가 음수가 된다면(X - 1 < 0) 큐에 추가하지 않는다.
 * - 동생의 위치 K보다 수빈이의 위치 X가 작은 값일 때에도 X - 1을 고려할 필요가 있다. 예를 들어 수빈이의 위치가 12, 동생의 위치가 22이면 (12 - 1) * 2가 최선이다.
 * - 수빈이의 위치 X가 동생의 위치 K보다 크다면 X - 1만 고려한다.
 * 	-> X - 1은 X - 1이 음수가 아니면서 K < X거나 abs(K - (X - 1) * 2)가 abs(K - X * 2)보다 작을 때만 적용.
 * 	-> X + 1은 X + 1이 K보다 작거나 같다면 적용.
 * 	-> X * 2는 K가 짝수일 때 X * 2가 K보다 작거나 같다면 적용, K가 홀수일 때 X * 2가 K + 1보다 작거나 같다면 적용.
 * - 목표 지점인 동생의 위치 K는 0 이상 100000 이하지만 수빈이는 그 이상의 값까지 이동할 수 있지 않을까?
 * 	-> 단순히 X + 1로 이동하면 100000을 지나쳐 그 이상으로 넘어갈 필요가 없으므로 제외
 * 	-> X * 2로 이동하면 100000을 넘어 이동할 수 있는 값이 100002, 100004, 100006, .... 이 중 가장 작은 값인 100002의 경우 50001에서 2를 곱하고 100000으로 이동하기 위해 -1을 두 번 해줘야 하므로 3초가 걸린다.
 * 		그러지 않고 50001 에서 -1을 한 다음 *2를 하면 100000으로 2초만에 이동할 수 있다. 따라서 고려할 범위는 0 ~ 100000 뿐이다. 
 * 		이에 따라 0 ~ 100000 범위 체크하기!!!!!!!
 * - 수빈이의 위치 X에 대해 dp[X] + 1 값과 dp[X - 1], dp[X + 1], dp[2 * N]를 비교해 dp[X] + 1이 더 작다면 각 dp 값을 업데이트하고 이동한 지점을 큐에 넣기
 * 
 * X - 1, X + 1, X * 2에 대한 범위만 체크하고 14 ~ 16 줄은 무시하자. 정확한 로직을 세우기 쉽지 않다.
 * 단순히 범위만 체크해도 dp[X - 1], dp[X + 1], dp[X * 2] 값이 이미 채워져 있다면 무조건 이들은 dp[X] + 1보다 작다.
 * 따라서 dp를 boolean[] visited으로 변경하자.
 * 
 * visited으로 방문 여부를 체크하므로 큐에 더해지는 원소의 최대 누적 개수는 100000개다. 시간 내에 풀 수 있다.
 * 
 * 결론적으론 그냥 BFS 문제다.
 * 
 * ---
 * 
 * visited를 체크하면 한 번 방문한 위치를 다시 방문하지 않게 된다. 입력 1 3은 1, 2, 3이 가장 빠른 경로인데 1에서 2로 가는 방법이 1 + 1, 1 * 2 두 가지임에도 한 개만 체크하는 문제가 생긴다.
 * -> 방문 체크를 하지 않으면 답은 맞게 나오지만 큐에 더해지는 원소의 수가 너무 많아 시간 초과가 날 것이다.
 * 
 * visited가 아닌 dp를 다시 사용하자. 단, dp[i]에는 이동하는데 걸린 시간이 아닌 해당 위치까지 최소 시간으로 이동할 수 있는 경로의 수를 저장한다.
 * dp[i]에 i까지 이동할 수 있는 경로의 수를 저장하되, dp[i]에 도달할 때마다 큐에 i를 더하진 않고, 큐에 i는 최초 한 번만 더한다.
 * 
 * 현재 시각 time에 도착할 수 있는 모든 위치에 대해 cur - 1, cur + 1, cur * 2를 적용하면 여러 위치에 도달할 수 있을 것이고 이 중 우리의 관심 대상인 next 위치에 여러 경로를 통해 도달할 수도 있다.
 * 이떄 next는 time + 1 시각에 도착할 수 있는 위치이므로 time에 출발해서 time + 1에 도달하는 경로의 수만 고려하면 된다.
 * 
 * dp[next]의 값을 time일 때만 업데이트하는 로직을 추가할 필요는 없는 게, 큐에 next를 추가하는 로직에 dp[next]의 값이 0일 때만 추가5 1000하도록 하면 된다.
 * time + 1 이후로도 dp[next]의 값이 업데이트될 수는 있지만 더이상 큐에 추가되지 않기 때문에 실행 시간에 영향을 미치지 않는다.
 * 
 * ---
 * 
 * dp[next]의 값이 time과 time + 1 모두에 업데이트되는 경우가 있다.
 * 5_1000 파일을 보자. 위치 7은 time == 2일 때 큐에 추가되고, time == 3이고 cur == 7일 때 dp[7] == 1이어야 한다.
 * 하지만 time == 3이고 cur가 7이 되기 직전, 8일 때 dp[7]이 2로 업데이트되며 이후 cur == 7일 때 dp[7]의 값으로 2가 활용된다.
 * 
 * dp를 int[][] 타입으로 수정하고, 첫 번째 차원으로는 위치를, 두 번째 차원으로는 경로의 수와 처음 큐에 추가된 시각을 저장한다.
 * (int[][] dp = new int[100001][2])
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
	private static int time = 0, pathCount = 0;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int n, int k) {
		int cur, size, next;
		int[][] dp = new int[100001][2];
		// +1 이동은 X+1이 K보다 작거나 같을 때만 수행하고,
		// -1 이동은 X-1이 양수라면 항상 수행하며,
		// *2 연산은 K의 짝, 홀 여부에 따라 next가 최대 K + 1과 같거나 작으면 수행한다.
		// 따라서 모든 위치 0 ~ 100000까지를 다 고려할 필요 없이 k + 2까지만 고려하면 된다.
		// 또한, k가 n보다 작을 수 있다는 걸 고려해야 한다.
		// 그러면 아래와 같이 dp를 초기화할 수 있다.
		// int[][] dp = new int[Math.max(k + 2, n + 1)][2];
		Deque<Integer> q = new ArrayDeque<>();

		q.addLast(n);
		dp[n][0] = 1;
		dp[n][1] = time;
		while (!q.isEmpty() && dp[k][0] == 0) {
			size = q.size();
			time++;
			while (size-- > 0) {
				cur = q.removeFirst();
				next = cur - 1;
				if (0 <= next) {
					if (dp[next][1] == 0) {
						q.addLast(next);
						dp[next][1] = time;
						dp[next][0] += dp[cur][0];
					} else if (dp[next][1] == time)
						dp[next][0] += dp[cur][0];
				}
				next = cur + 1;
				if (next <= k) {
					if (dp[next][1] == 0) {
						q.addLast(next);
						dp[next][1] = time;
						dp[next][0] += dp[cur][0];
					} else if (dp[next][1] == time)
						dp[next][0] += dp[cur][0];
				}
				next = cur * 2;
				if ((k % 2 == 0 && next <= k) || (k % 2 == 1 && next <= k + 1)) {
					if (dp[next][1] == 0) {
						q.addLast(next);
						dp[next][1] = time;
						dp[next][0] += dp[cur][0];
					} else if (dp[next][1] == time)
						dp[next][0] += dp[cur][0];
				}
			}
		}
		pathCount = dp[k][0];
	}

	public static void main(String[] args) throws IOException {
		solve(readInt(), readInt());
		bw.write(new StringBuilder().append(time).append('\n').append(pathCount).append('\n').toString());
		bw.flush();
		br.close();
		bw.close();
	}
}