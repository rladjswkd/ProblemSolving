
/*
 * 만일 그래프에 단 하나의 사이클도 없다면, 해당 그래프는 ‘트리’ 라고 부른다.
 * 
 * 문제에서 주어지는 간선 정보는 왼쪽과 오른쪽 노드 중 무엇이 상위 노드고 무엇이 하위 노드인지 정해지지 않았다.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static List<List<Integer>> graph;
	private static int[] dp;
	private static boolean[] visited;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int currentRoot) {
		dp[currentRoot] = 1;
		visited[currentRoot] = true;
		for (int child : graph.get(currentRoot)) {
			if (visited[child])
				continue;
			if (dp[child] == 0)
				solve(child);
			dp[currentRoot] += dp[child];
		}
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), r = readInt(), q = readInt(), u, v;
		StringBuilder sb = new StringBuilder();

		graph = new ArrayList<>();
		dp = new int[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++)
			graph.add(new ArrayList<>());
		for (int i = 1; i < n; i++) {
			u = readInt() - 1;
			v = readInt() - 1;
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		solve(r - 1);
		for (int i = 0; i < q; i++)
			sb.append(dp[readInt() - 1]).append('\n');
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}