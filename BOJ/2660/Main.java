import java.io.IOException;

public class Main {
	private static int personCount, u, v;
	private static int[][] graph;
	private static final int INFINITY = 51;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int score, bestScore = INFINITY, counter = 0;
		StringBuilder sb = new StringBuilder();
		int[] scores;

		graph = new int[personCount = read()][personCount];
		scores = new int[personCount];
		for (int i = 0; i < personCount; i++) {
			for (int j = 0; j < i; j++)
				graph[i][j] = INFINITY;
			for (int j = i + 1; j < personCount; j++)
				graph[i][j] = INFINITY;
		}
		while ((u = read() - 1) != 130 && (v = read() - 1) != 130)
			graph[u][v] = graph[v][u] = 1;
		for (int via = 0; via < personCount; via++)
			for (int start = 0; start < personCount; start++)
				for (int end = 0; end < personCount; end++)
					graph[start][end] = Math.min(graph[start][end], graph[start][via] + graph[via][end]);
		for (int i = 0; i < personCount; i++) {
			score = 0;
			for (int j = 0; j < i; j++)
				score = Math.max(score, graph[i][j]);
			for (int j = i + 1; j < personCount; j++)
				score = Math.max(score, graph[i][j]);
			if (score < bestScore) {
				bestScore = score;
				counter = 1;
			} else if (score == bestScore)
				counter++;
			scores[i] = score;
		}
		sb.append(bestScore).append(' ').append(counter).append('\n');
		for (int i = 0; i < personCount; i++)
			if (scores[i] == bestScore)
				sb.append(i + 1).append(' ');
		System.out.println(sb.toString());
	}
}