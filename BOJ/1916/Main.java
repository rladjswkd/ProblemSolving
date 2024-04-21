import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

// 2번째 시도 코드 + cur이 targetCity면 break하는 로직 또는
// 4번째 시도 코드(= 3번째 시도 코드 + cur이 targetCity면 break하는 로직)이 시도한 방법중엔 최선이다.
// 메모리를 조금이라도 아끼고 싶다면 아래 방법을, 시간을 조금이라도 더 빠르게 하고 싶다면 위 방법을 사용하자.
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int INFINITY = Integer.MAX_VALUE;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int cityCount = readInt(), busCount = readInt(), startCity, targetCity, alt;
		int[] dist = new int[cityCount], cur;
		List<List<int[]>> graph = new ArrayList<>();
		PriorityQueue<int[]> pq = new PriorityQueue<>(cityCount, (a, b) -> a[1] - b[1]);

		for (int i = 0; i < cityCount; i++) {
			dist[i] = INFINITY;
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < busCount; i++)
			graph.get(readInt() - 1).add(new int[] { readInt() - 1, readInt() });
		startCity = readInt() - 1;
		targetCity = readInt() - 1;
		dist[startCity] = 0;
		pq.add(new int[] { startCity, 0 });
		while (!pq.isEmpty()) {
			cur = pq.poll();
			if (dist[cur[0]] < cur[1])
				continue;
			for (int[] neighbor : graph.get(cur[0])) {
				alt = dist[cur[0]] + neighbor[1];
				if (alt < dist[neighbor[0]]) {
					dist[neighbor[0]] = alt;
					pq.add(new int[] { neighbor[0], alt });
				}
			}
		}
		bw.append(String.valueOf(dist[targetCity])).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}