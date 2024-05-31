import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int INFINITY = 400 * 10000 + 1;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int v = readInt(), e = readInt(), res = INFINITY;
		int[][] costs = new int[v][v];

		for (int i = 0; i < v; i++)
			Arrays.fill(costs[i], INFINITY);
		for (int i = 0; i < e; i++)
			costs[readInt() - 1][readInt() - 1] = readInt();
		for (int via = 0; via < v; via++)
			for (int start = 0; start < v; start++)
				for (int end = 0; end < v; end++)
					costs[start][end] = Math.min(costs[start][end], costs[start][via] + costs[via][end]);
		for (int i = 0; i < v; i++)
			for (int j = i + 1; j < v; j++)
				res = Math.min(res, costs[i][j] + costs[j][i]);
		bw.append(String.valueOf(res == INFINITY ? -1 : res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}