import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int labSize, activationCount, virusCount = 0, res = Integer.MAX_VALUE, blankCount = 0;
	private static int[][] lab, virusCoords = new int[10][];
	private static long[] masks, visited;
	private static Deque<int[]> q = new ArrayDeque<>();
	private static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	// 첫 번째 시도 방식에서 사용한 size, dist 변수를 제거하고, 큐의 원소에 행, 열 인덱스와 함께 해당 행, 열 칸에 도달하는 데
	// 걸린 시간을 저장한다.
	// 이후 dist를 업데이트하는데, 다음과 같은 조건으로 업데이트한다.
	// 1. 현재 칸(cur)이 비활성화 상태인 바이러스 칸이 아니어야 한다.
	// 2. dist보다 현재 칸에 도달하는데 걸린 시간이 더 클 때, 그 시간 값으로 dist를 업데이트한다.

	private static void solve(int chosen) {
		int[] cur;
		int dist = 0, nx, ny, tempBlankCount = blankCount;

		for (int i = 0; i < labSize; i++)
			visited[i] = masks[i];
		for (int i = 0; i < virusCount; i++) {
			if ((chosen & 1 << i) > 0) {
				q.addLast(new int[] { virusCoords[i][0], virusCoords[i][1], 0 });
				// 현재 활성화할 바이러스들이 위치한 칸들을 방문 처리
				visited[virusCoords[i][0]] |= 1L << virusCoords[i][1];
			}
		}
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			// dist를 업데이트하는 것은 lab에서 현재 칸이 비활성화 바이러스 상태가 아니어야 한다.
			// lab에서 현재 칸의 값이 2일 때 활성화 바이러스 상태일 수도 있지만, 어차피 그 때의 cur[2] 값은 0이므로 dist를 업데이트하지
			// 않아도 문제 없다.
			if (lab[cur[0]][cur[1]] == 0) {
				dist = Math.max(dist, cur[2]);
				tempBlankCount--;
			}
			for (int i = 0; i < 4; i++) {
				nx = cur[0] + dx[i];
				ny = cur[1] + dy[i];
				// 현재 칸이 아직 방문하지 않은 칸(빈 칸 또는 비활성화 바이러스 칸)이라면, 바이러스가 확산할 수 있는 칸으로 간주
				if (0 <= nx && nx < labSize && 0 <= ny && ny < labSize && (visited[nx] & 1L << ny) == 0) {
					q.addLast(new int[] { nx, ny, cur[2] + 1 });
					visited[nx] |= 1L << ny;
				}
			}
		}
		if (tempBlankCount == 0)
			res = Math.min(res, dist);
	}

	private static void chooseViruses(int chosen, int count, int start) {
		if (count == activationCount) {
			solve(chosen);
			return;
		}
		for (int i = start; i <= virusCount - activationCount + count; i++)
			chooseViruses(chosen | 1 << i, count + 1, i + 1);
	}

	public static void main(String[] args) throws IOException {
		lab = new int[labSize = readInt()][labSize];
		activationCount = readInt();
		masks = new long[labSize];
		for (int i = 0; i < labSize; i++) {
			for (int j = 0; j < labSize; j++) {
				if ((lab[i][j] = System.in.read() & 15) == 2)
					virusCoords[virusCount++] = new int[] { i, j };
				// 벽인 칸은 방문할 필요도 없고 방문할 수도 없으므로 이미 방문한 칸으로 간주하고, 모든 비트가 채워진 것으로 방문할 수 있는 모든 칸에
				// 바이러스가 퍼졌음을 판단한다.
				// 비활성화 바이러스 칸 또한 방문할 수 있는 칸이다.
				// 단, 이 칸까지 방문하는데 걸리는 시간을 측정할지 말지 그 여부는 solve() 함수의 주석을 보자.
				// 0번째 비트는 가장 오른쪽에, 0번째 인덱스는 가장 왼쪽에 있지만 이 문제를 풀기 위해 굳이 그 위치를 맞춰줄 필요는 없다.
				else if (lab[i][j] == 1)
					masks[i] |= 1L << j;
				else
					blankCount++;
				System.in.read();
			}
		}
		visited = new long[labSize];
		chooseViruses(0, 0, 0);
		System.out.println(res == Integer.MAX_VALUE ? -1 : res);
	}
}