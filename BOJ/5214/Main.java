import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int stationCount, tubeCount, res;
	private static int[][] tubes;
	private static List<List<Integer>> graph;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visitedStation = new boolean[stationCount];
		// 이미 확인한 하이퍼튜브에 대한 중복 확인도 제거하기 위한 배열
		boolean[] visitedTube = new boolean[tubeCount];
		int cur, size;

		q.addLast(0);
		visitedStation[0] = true;
		while (!q.isEmpty()) {
			size = q.size();
			res++;
			while (size-- > 0) {
				cur = q.removeFirst();
				// 연결된 튜브 모두를 탐색
				for (int tubeIdx : graph.get(cur)) {
					if (!visitedTube[tubeIdx]) {
						visitedTube[tubeIdx] = true;
						for (int neighbor : tubes[tubeIdx]) {
							if (!visitedStation[neighbor]) {
								if (neighbor == stationCount - 1) {
									res++;
									return;
								}
								visitedStation[neighbor] = true;
								q.addLast(neighbor);
							}
						}
					}
				}
			}
		}
		// 1 1 1
		// 1
		// 위와 같은 입력 때문에 방문 여부를 확인해 res를 업데이트
		if (!visitedStation[stationCount - 1])
			res = -1;
	}

	public static void main(String[] args) throws IOException {
		int linkCount;
		int[] tube;

		stationCount = readInt();
		linkCount = readInt();
		tubeCount = readInt();
		tubes = new int[tubeCount][linkCount];
		graph = new ArrayList<>();
		for (int i = 0; i < stationCount; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < tubeCount; i++) {
			tube = tubes[i];
			for (int j = 0; j < linkCount; j++)
				graph.get(tube[j] = readInt() - 1).add(i);
		}
		res = 0;
		solve();
		System.out.println(res);
	}
}