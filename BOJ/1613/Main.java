import java.io.*;

public class Main {
	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), k = readInt(), s, a, b;
		boolean[][] graph = new boolean[n][n];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++)
			graph[i][i] = true;
		for (int i = 0; i < k; i++)
			graph[readInt() - 1][readInt() - 1] = true;
		for (int via = 0; via < n; via++)
			for (int start = 0; start < n; start++)
				for (int end = 0; end < n; end++)
					graph[start][end] = graph[start][end] || (graph[start][via] && graph[via][end]);
		s = readInt();
		while (s-- > 0) {
			a = readInt() - 1;
			b = readInt() - 1;
			if (graph[a][b])
				sb.append(-1);
			else if (graph[b][a])
				sb.append(1);
			else
				sb.append(0);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}