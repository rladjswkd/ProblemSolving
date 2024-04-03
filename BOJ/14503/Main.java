/*
 * 아래 방법은 스택을 사용해 dfs를 구현한 방식이지만, 사실상 계속해서 "현재" 칸만 체크하고 그 칸에서 더이상 이동하지 못하면 종료하므로
 * 스택의 top만 알면 된다. -> 스택이 필요없고 그냥 변수만 있으면 된다.
 * 따라서 주석처리한 코드 아래의 코드가 더 효율적이다.
 */
// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.InputStreamReader;
// import java.io.OutputStreamWriter;
// import java.io.IOException;
// import java.util.Deque;
// import java.util.ArrayDeque;

// public class Main {
// 	private static int n, m;
// 	private static int[][] room;

// 	private static int dfs(int[] p, int[] dx, int[] dy) {
// 		int size, res = 0, back;
// 		Deque<int[]> st = new ArrayDeque<>();

// 		st.addLast(p);
// 		while (!st.isEmpty()) {
// 			p = st.removeLast();
// 			// 현재 칸을 청소하지 않았다면 청소하기
// 			if (room[p[0]][p[1]] == 0) {
// 				room[p[0]][p[1]] = 2;
// 				res++;
// 			}
// 			size = st.size();
// 			// 현재 칸의 주변 4칸 확인. 북(0) 동(1) 남(2) 서(3) 서 -> 남, 남 -> 동, 동 -> 북, 북 -> 서.
// 			// (dir - 1 + 4) % 4 -> (3 + dir) % 4
// 			for (int i = 0; i < 4; i++) {
// 				p[2] = (3 + p[2]) % 4;
// 				// 가장자리는 모두 벽이므로 사실상 범위 체크는 하지 않아도 된다.
// 				if (0 < p[0] + dx[p[2]] && p[0] + dx[p[2]] < n - 1 && 0 < p[1] + dy[p[2]] && p[1] + dy[p[2]] < m - 1
// 						&& room[p[0] + dx[p[2]]][p[1] + dy[p[2]]] == 0) {
// 					st.addLast(new int[] { p[0] + dx[p[2]], p[1] + dy[p[2]], p[2] });
// 					break;
// 				}
// 			}
// 			// 현재 칸의 주변 4칸을 확인하기 전의 st.size()와 현재 st.size()가 같다면 주변 4칸 중 청소하지 않은 빈 칸이 없는 것이다
// 			// 만약 위 주석의 조건이 성립하면, 주변 4칸을 확인하기 전의 dir과 확인한 후의 dir은 같다.
// 			// 후진 방향은 0 -> 2, 1 -> 3, 2 -> 0, 3 -> 1이므로 (dir + 2) % 4
// 			if (size != st.size())
// 				continue;
// 			back = (p[2] + 2) % 4;
// 			if (room[p[0] + dx[back]][p[1] + dy[back]] == 1)
// 				break;
// 			st.addLast(new int[] { p[0] + dx[back], p[1] + dy[back], p[2] });
// 		}
// 		return res;
// 	}

// 	public static void main(String[] args) throws IOException {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
// 		String line;
// 		String[] input;
// 		int[] point;

// 		input = br.readLine().split(" ");
// 		n = Integer.parseInt(input[0]);
// 		m = Integer.parseInt(input[1]);
// 		input = br.readLine().split(" ");
// 		point = new int[] { Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]) };
// 		room = new int[n][m];
// 		for (int i = 0; i < n; i++) {
// 			line = br.readLine();
// 			for (int j = 0; j < line.length(); j += 2)
// 				room[i][j / 2] = line.charAt(j) - 48;
// 		}
// 		// dx, dy는 문제에 주어진대로 0, 1, 2, 3 각각 북, 동, 남, 서
// 		bw.write(dfs(point, new int[] { -1, 0, 1, 0 }, new int[] { 0, 1, 0, -1 }) + "\n");
// 		bw.flush();
// 		br.close();
// 		bw.close();
// 	}
// }
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static int n, m;
	private static int[][] room;

	private static int dfs(int[] p, int[] dx, int[] dy) {
		int res = 0, back;
		int[] prev;

		while (true) {
			prev = new int[] { p[0], p[1], p[2] };
			if (room[p[0]][p[1]] == 0) {
				room[p[0]][p[1]] = 2;
				res++;
			}
			for (int i = 0; i < 4; i++) {
				p[2] = (3 + p[2]) % 4;
				if (room[p[0] + dx[p[2]]][p[1] + dy[p[2]]] == 0) {
					p = new int[] { p[0] + dx[p[2]], p[1] + dy[p[2]], p[2] };
					break;
				}
			}
			if (prev[0] != p[0] || prev[1] != p[1] || prev[2] != p[2])
				continue;
			back = (p[2] + 2) % 4;
			if (room[p[0] + dx[back]][p[1] + dy[back]] == 1)
				break;
			p = new int[] { p[0] + dx[back], p[1] + dy[back], p[2] };
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line;
		String[] input;
		int[] point;

		input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		input = br.readLine().split(" ");
		point = new int[] { Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]) };
		room = new int[n][m];
		for (int i = 0; i < n; i++) {
			line = br.readLine();
			for (int j = 0; j < line.length(); j += 2)
				room[i][j / 2] = line.charAt(j) - 48;
		}
		bw.write(dfs(point, new int[] { -1, 0, 1, 0 }, new int[] { 0, 1, 0, -1 }) + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
}