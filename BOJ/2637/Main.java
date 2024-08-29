import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int partCount, relCount, basicPartCount;
	private static List<List<int[]>> graph;
	private static int[] degree;
	private static int[][] dp;
	private static Deque<Integer> q;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		int cur;

		while (!q.isEmpty()) {
			cur = q.removeFirst();
			for (int[] part : graph.get(cur)) {
				for (int i = 0; i < basicPartCount; i++)
					dp[part[0]][i] += dp[cur][i] * part[1];
				if (--degree[part[0]] == 0)
					q.addLast(part[0]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int u, basicIdx;
		boolean[] interParts;
		int[] mapper;
		StringBuilder sb = new StringBuilder();

		basicPartCount = partCount = readInt();
		relCount = readInt();
		graph = new ArrayList<>();
		degree = new int[partCount];
		interParts = new boolean[partCount];
		for (int i = 0; i < partCount; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < relCount; i++) {
			u = readInt() - 1;
			graph.get(readInt() - 1).add(new int[] { u, readInt() });
			degree[u]++;
			if (!interParts[u]) {
				basicPartCount--;
				interParts[u] = true;
			}
		}
		dp = new int[partCount][basicPartCount];
		mapper = new int[basicPartCount];
		basicIdx = 0;
		q = new ArrayDeque<>();
		for (int i = 0; i < partCount; i++) {
			if (degree[i] == 0) {
				mapper[basicIdx] = i + 1;
				dp[i][basicIdx++] = 1;
				q.addLast(i);
			}
		}
		solve();
		for (int i = 0; i < basicPartCount; i++)
			sb.append(mapper[i]).append(' ').append(dp[partCount - 1][i]).append('\n');
		System.out.print(sb.toString());
	}
}