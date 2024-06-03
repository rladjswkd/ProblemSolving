import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final long INFINITY = 500 * 10000 + 1;
	private static long[] costs;

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

	private static boolean solve(int cityCount, int busCount, int startCity, List<int[]> edges) {
		costs = new long[cityCount];
		for (int i = 0; i < cityCount; i++)
			costs[i] = INFINITY;
		costs[startCity] = 0;
		for (int i = 0; i < cityCount; i++) {
			for (int[] edge : edges) {
				if (costs[edge[0]] == INFINITY)
					continue;
				if (costs[edge[0]] + edge[2] < costs[edge[1]])
					costs[edge[1]] = costs[edge[0]] + edge[2];
			}
		}
		for (int[] edge : edges)
			if (costs[edge[0]] != INFINITY && costs[edge[0]] + edge[2] < costs[edge[1]])
				return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		int cityCount = readInt(), busCount = readInt();
		List<int[]> edges = new ArrayList<>();
		StringBuilder sb;

		for (int i = 0; i < busCount; i++)
			edges.add(new int[] { readInt() - 1, readInt() - 1, readInt() });
		if (solve(cityCount, busCount, 0, edges)) {
			sb = new StringBuilder();
			for (int i = 1; i < cityCount; i++)
				sb.append(costs[i] == INFINITY ? -1 : costs[i]).append('\n');
			bw.write(sb.toString());
		} else
			bw.write("-1\n");
		bw.flush();
		br.close();
		bw.close();
	}
}