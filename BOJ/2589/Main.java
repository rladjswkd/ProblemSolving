
/*
 * 육지 칸의 최대 개수: 2500
 * BFS로 탐색할 육지의 개수: 2500 미만
 * 
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
	private static char[][] map;
	private static int[][] dist;
	private static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	private static Deque<int[]> stack = new ArrayDeque<>();

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void printDist() {
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist[0].length; j++)
				System.out.printf("%c ", dist[i][j] == -1 ? 'X' : 'O');
			System.out.println();
		}
		System.out.println();
	}

	private static int solve(int x, int y) {
		int[] cur;
		int nx, ny, res = 0, size;
		// 시작 위치를 0으로 두고, 방문하지 않은 위치를 이와 구분하기 위해 -1로 설정
		for (int i = 0; i < dist.length; i++)
			for (int j = 0; j < dist[0].length; j++)
				dist[i][j] = -1;
		stack.addLast(new int[] { x, y });
		dist[x][y] = 0;
		while (!stack.isEmpty()) {
			size = stack.size();
			res++;
			while (size-- > 0) {
				cur = stack.removeFirst();
				for (int i = 0; i < 4; i++) {
					nx = cur[0] + dx[i];
					ny = cur[1] + dy[i];
					if (0 <= nx && nx < map.length && 0 <= ny && ny < map[0].length && map[nx][ny] == 'L' && dist[nx][ny] == -1) {
						dist[nx][ny] = res;
						stack.addLast(new int[] { nx, ny });
					}
				}
			}
		}
		return res - 1; // 가장 마지막 L이 스택에 들어있을 때 49번 줄의 while 문이 실행되고 51번 줄에서 res가 1 증가하므로 1을 빼줘야 하낟.
	}

	public static void main(String[] args) throws IOException {
		int h = readInt(), w = readInt(), res = 0;

		map = new char[h][w];
		dist = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++)
				map[i][j] = (char) br.read();
			br.read(); // '\n'
		}
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				if (map[i][j] == 'L')
					res = Math.max(res, solve(i, j));
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}