import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n, m, res = Integer.MIN_VALUE, safeArea;
	private static int[][] lab;
	private static List<int[]> space = new ArrayList<>();
	private static List<int[]> virus = new ArrayList<>();
	private static Deque<int[]> q = new ArrayDeque<>();

	private static void processInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		String r;

		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		lab = new int[n][m];
		for (int i = 0; i < n; i++) {
			r = br.readLine();
			for (int j = 0; j < r.length(); j += 2) {
				lab[i][j / 2] = r.charAt(j) - 48;
				if (lab[i][j / 2] == 0)
					space.add(new int[] { i, j / 2 });
				if (lab[i][j / 2] == 2)
					virus.add(new int[] { i, j / 2 });
			}
		}
		safeArea = space.size() - 3;
		br.close();
	}

	private static void calculateSafeArea() {
		int safeAreaBackup = safeArea;
		int[] cur, dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 }, next;
		boolean[][] vis = new boolean[n][m];

		for (int[] p : virus)
			q.addLast(p);
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			for (int i = 0; i < 4; i++) {
				next = new int[] { cur[0] + dx[i], cur[1] + dy[i] };
				if (next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >= m
						|| lab[next[0]][next[1]] != 0 || vis[next[0]][next[1]])
					continue;
				vis[next[0]][next[1]] = true;
				q.addLast(next);
				safeArea--;
			}
		}
		if (res < safeArea)
			res = safeArea;
		safeArea = safeAreaBackup;
		q.clear();
	}

	private static void search(int cnt, int idx) {
		if (cnt == 3) {
			calculateSafeArea();
			return;
		}
		for (int i = idx; i < space.size(); i++) {
			if (lab[space.get(i)[0]][space.get(i)[1]] != 0)
				continue;
			lab[space.get(i)[0]][space.get(i)[1]] = 1;
			search(cnt + 1, i + 1);
			lab[space.get(i)[0]][space.get(i)[1]] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		processInput();
		search(0, 0);
		bw.write(res + "\n");
		bw.flush();
		bw.close();
	}
}