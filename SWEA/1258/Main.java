import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

class Solution {
	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("sample_input.txt"));
		System.setIn(new FileInputStream("input.txt"));
		System.setOut(new PrintStream("result.txt"));

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(), len, nx, ny;
		int[] start, cur = { 0, 0 }, dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
		int[][] arr;
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][] visited;
		List<int[]> res;

		for (int testCase = 1; testCase <= T; testCase++) {
			len = sc.nextInt();
			arr = new int[len][len];
			visited = new boolean[len][len];
			res = new ArrayList<>();
			for (int i = 0; i < len; i++)
				for (int j = 0; j < len; j++)
					arr[i][j] = sc.nextInt();
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					if (visited[i][j] || arr[i][j] == 0)
						continue;
					visited[i][j] = true;
					start = new int[] { i, j };
					q.addLast(start);
					while (!q.isEmpty()) {
						cur = q.removeFirst();
						for (int k = 0; k < 4; k++) {
							nx = cur[0] + dx[k];
							ny = cur[1] + dy[k];
							if ((nx < 0 || len <= nx || ny < 0 || len <= ny) || visited[nx][ny] || arr[nx][ny] == 0)
								continue;
							visited[nx][ny] = true;
							q.addLast(new int[] { nx, ny });
						}
					}
					res.add(new int[] { cur[0] - start[0] + 1, cur[1] - start[1] + 1 });
				}
			}
			res.sort((a, b) -> {
				int sizeDiff = a[0] * a[1] - b[0] * b[1];

				if (sizeDiff == 0)
					return a[0] - b[0];
				return sizeDiff;
			});
			System.out.printf("#%d %d", testCase, res.size());
			for (int[] each : res)
				System.out.printf(" %d %d", each[0], each[1]);
			System.out.println();
		}
		sc.close();
	}
}