import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int INFINITY = 20_000_001;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int findLowestCostVillage(int[] costs, boolean[] processed) {
		int lowestCost = Integer.MAX_VALUE, lowestCostVillage = -1;

		for (int i = 0; i < costs.length; i++) {
			if (!processed[i] && costs[i] < lowestCost) {
				lowestCost = costs[i];
				lowestCostVillage = i;
			}
		}
		return lowestCostVillage;
	}

	private static int findShortestPath(List<List<int[]>> graph, int villageCount, int from, int to) {
		boolean[] processed = new boolean[villageCount];
		int[] costs = new int[villageCount];
		int cur, newCost;

		for (int i = 0; i < costs.length; i++)
			costs[i] = INFINITY;
		costs[from] = 0;
		while ((cur = findLowestCostVillage(costs, processed)) != -1) {
			for (int[] neighbor : graph.get(cur)) {
				newCost = neighbor[1] + costs[cur];
				if (!processed[neighbor[0]] && newCost < costs[neighbor[0]])
					costs[neighbor[0]] = newCost;
			}
			processed[cur] = true;
		}
		return costs[to];
	}

	public static void main(String[] args) throws IOException {
		int villageCount = readInt(), roadCount = readInt(), targetCity = readInt() - 1, res = 0;
		List<List<int[]>> graph = new ArrayList<>();

		for (int i = 0; i < villageCount; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < roadCount; i++)
			graph.get(readInt() - 1).add(new int[] { readInt() - 1, readInt() });
		for (int i = 0; i < villageCount; i++)
			res = Math.max(res,
					findShortestPath(graph, villageCount, i, targetCity) + findShortestPath(graph, villageCount, targetCity, i));
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}