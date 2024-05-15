
/*
 * BFS
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int[] dl = { -1, 1, 0, 0, 0, 0 }, dr = { 0, 0, -1, 1, 0, 0 }, dc = { 0, 0, 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static String solve(char[][][] building, int[] start, int l, int r, int c) {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[l][r][c];
		int size, nl, nr, nc, dist = 0;
		int[] cur;

		q.addLast(start);
		visited[start[0]][start[1]][start[2]] = true;
		while (!q.isEmpty()) {
			size = q.size();
			dist++;
			while (size-- > 0) {
				cur = q.removeFirst();
				for (int i = 0; i < 6; i++) {
					nl = cur[0] + dl[i];
					nr = cur[1] + dr[i];
					nc = cur[2] + dc[i];
					if (0 <= nl && nl < l && 0 <= nr && nr < r && 0 <= nc && nc < c && !visited[nl][nr][nc]
							&& building[nl][nr][nc] != '#') {
						if (building[nl][nr][nc] == 'E')
							return String.format("Escaped in %d minute(s).\n", dist);
						q.addLast(new int[] { nl, nr, nc });
						visited[nl][nr][nc] = true;
					}
				}
			}
		}
		return "Trapped!\n";
	}

	public static void main(String[] args) throws IOException {
		int l = 0, r = 0, c = 0;
		int[] start = null;
		char[][][] building = null;
		StringBuilder sb = new StringBuilder();

		while ((l = readInt()) > 0 && (r = readInt()) > 0 && (c = readInt()) > 0) {
			building = new char[l][r][c];
			for (int i = 0; i < l; i++) {
				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						building[i][j][k] = (char) br.read();
						if (building[i][j][k] == 'S')
							start = new int[] { i, j, k };
					}
					br.read(); // '\n'
				}
				br.read(); // '\n'
			}
			sb.append(solve(building, start, l, r, c));
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}