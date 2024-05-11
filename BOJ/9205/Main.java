/*
 * 아래 대상들 사이의 맨해튼 거리가 1000 이하라면 연결한 후, BFS 또는 DFS로 집에서 펜타포트 락 페스티벌 좌표까지 도달할 수 있는지 확인
 * 
 * - 집과 편의점, 펜타포트 락 페스티벌 좌표
 * - 편의점과 편의점, 펜타포트 락 페스티벌 좌표
 * 
 * 연결 방향
 * 집 - 편의점 : 집 -> 편의점 방향만 고려
 * 집 - 펜타포트 락 페스티벌 : 집 -> 펜타포트 락 페스티벌 방향만 고려
 * 편의점 - 편의점 : 편의점 <-> 편의점 양방향 고려 후, 방문 여부 체크해 방문한 곳엔 재방문하지 않기
 * 편의점 - 펜타포트 락 페스티벌 : 편의점 -> 펜타포트 락 페스티벌 방향만 고려
 * 
 * 그냥 모두 다 양방향 연결한 후 방문 여부 체크해도 성능차이 벌로 없을 것 같다.
 * 
 * 테스트 케이스 : 50
 * 그래프 생성: 최대 102 * 102
 * BFS/DFS 시간 복잡도: O(노드의 수 + 간선의 수) -> 노드의 수 최대 102, 간선의 수 최대 102 * 102
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur, sign = br.read();

		if (sign != '-')
			n = sign - 48;
		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == '-')
			n = -n;
		return n;
	}

	// BFS
	private static boolean solve(List<List<Integer>> graph) {
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[graph.size()];
		int cur, dest = graph.size() - 1;

		q.addLast(0);
		visited[0] = true;
		while (!q.isEmpty() && !visited[dest]) {
			cur = q.removeFirst();
			for (int neighbor : graph.get(cur)) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					q.addLast(neighbor);
				}
			}
		}
		return visited[dest];
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), n;
		int[][] places;
		List<List<Integer>> graph;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			graph = new ArrayList<>();
			n = readInt();
			places = new int[n + 2][];
			for (int i = 0; i < n + 2; i++) {
				places[i] = new int[] { readInt(), readInt() };
				graph.add(new ArrayList<>());
			}
			for (int i = 0; i < places.length; i++) {
				for (int j = i + 1; j < places.length; j++) {
					if (Math.abs(places[i][0] - places[j][0]) + Math.abs(places[i][1] -
							places[j][1]) <= 1000) {
						graph.get(i).add(j);
						graph.get(j).add(i);
					}
				}
			}
			sb.append(solve(graph) ? "happy\n" : "sad\n");
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}