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
	private static StringBuilder sb = new StringBuilder();
	// 모든 노드가 한쪽으로 치우친 형태의 그래프라고 하더라도, 비용은 최대 100,000이고 노드는 최대 100개이므로
	// 비용의 총합은 최대 10,000,000이다.
	private static final int INFINITY = Integer.MAX_VALUE;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int findLowestCostCity(int[] costs, boolean[] processed) {
		int lowestCostCity = -1, lowestCost = INFINITY;

		for (int i = 0; i < costs.length; i++) {
			if (!processed[i] && costs[i] < lowestCost) {
				lowestCostCity = i;
				lowestCost = costs[i];
			}
		}
		return lowestCostCity;
	}

	private static void solve(List<List<int[]>> graph, int cityCount, int startCity) {
		boolean[] processed = new boolean[cityCount];
		int[] costs = new int[cityCount];
		// int[] prev = new int[cityCount]; 경로를 출력하지 않으므로 필요하지 않다.
		int newCost, currentCity;

		for (int i = 0; i < cityCount; i++)
			costs[i] = INFINITY;
		costs[startCity] = 0;
		while ((currentCity = findLowestCostCity(costs, processed)) != -1) {
			currentCity = findLowestCostCity(costs, processed);
			for (int[] neighbors : graph.get(currentCity))
				if (!processed[neighbors[0]] && (newCost = costs[currentCity] + neighbors[1]) < costs[neighbors[0]])
					costs[neighbors[0]] = newCost;
			processed[currentCity] = true;
		}
		if (costs[0] == INFINITY)
			sb.append(0);
		else
			sb.append(costs[0]);
		for (int i = 1; i < costs.length; i++) {
			sb.append(' ');
			if (costs[i] == INFINITY)
				sb.append(0);
			else
				sb.append(costs[i]);
		}
		sb.append('\n');
	}

	public static void main(String[] args) throws IOException {
		int cityCount = readInt(), busCount = readInt();
		List<List<int[]>> graph = new ArrayList<>();

		for (int i = 0; i < cityCount; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < busCount; i++)
			graph.get(readInt() - 1).add(new int[] { readInt() - 1, readInt() });
		for (int i = 0; i < cityCount; i++)
			solve(graph, cityCount, i);
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}