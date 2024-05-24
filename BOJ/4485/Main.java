
/*
 * 다익스트라
 * 
 * 동굴의 각 칸의 값은 그 칸의 상, 하, 좌, 우 칸에서 그 칸으로 이동하는 경로의 가중치
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int INFINITY = 9 * (125 + 124) + 1;
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int findLowestCostCell(int[] costs, boolean[] processed) {
		int lowestCostCell = -1, lowestCost = INFINITY;

		for (int i = 0; i < costs.length; i++) {
			if (!processed[i] && costs[i] < lowestCost) {
				lowestCost = costs[i];
				lowestCostCell = i;
			}
		}
		return lowestCostCell;
	}

	private static int solve(int n, int[][] cave) {
		int[] costs = new int[n * n];
		boolean[] processed = new boolean[n * n];
		int cur, nx, ny;

		for (int i = 0; i < n * n; i++)
			costs[i] = INFINITY;
		costs[0] = cave[0][0];
		while ((cur = findLowestCostCell(costs, processed)) != -1) {
			for (int i = 0; i < 4; i++) {
				nx = cur / n + dx[i];
				ny = cur % n + dy[i];
				if (nx < 0 || n <= nx || ny < 0 || n <= ny)
					continue;
				if (!processed[nx * n + ny] && costs[cur] + cave[nx][ny] < costs[nx * n + ny])
					costs[nx * n + ny] = costs[cur] + cave[nx][ny];
			}
			processed[cur] = true;
		}
		return costs[n * n - 1];
	}

	public static void main(String[] args) throws IOException {
		int n, order = 0;
		int[][] cave;
		StringBuilder sb = new StringBuilder();

		while ((n = readInt()) != 0) {
			cave = new int[n][n];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					cave[i][j] = readInt();
			sb.append(String.format("Problem %d: %d\n", ++order, solve(n, cave)));
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}