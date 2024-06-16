
/*
 * 시작점에서 BFS -> 맞닥뜨리는 검은 방들을 따로 저장
 * 앞선 과정에서 저장해놓은 각각의 검은 방들을 흰 방으로 바꾸고 시작점으로 취급하여 BFS -> 다시 맞닥뜨리는 검은 방들을 따로 저장
 * 끝 방에 도달할 때까지 반복하며 반복 횟수를 반환
 * 
 * "윗줄 맨 왼쪽 방은 시작방으로서 항상 흰 방이고, 아랫줄 맨 오른쪽 방은 끝방으로서 역시 흰 방이다.""
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

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int solve(int[][] rooms, int n, final int[] dx, final int[] dy) {
		Deque<int[]> whites = new ArrayDeque<>();
		Deque<int[]> blacks = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];
		int[] cur;
		int nx, ny, res = 0;

		// 아래 반복문의 로직을 위해 시작점은 맨 처음엔 검은 방으로 취급
		blacks.addLast(new int[] { 0, 0 });
		visited[0][0] = true;
		while (true) {
			// 흰 방으로 바꿀 검은 방들을 바꿔주기
			while (!blacks.isEmpty())
				whites.addLast(blacks.removeFirst());
			// 바꿀 수 있는 검은 방들을 흰 방으로 바꾼 후, 방문할 수 있는 모든 지점을 방문하기
			while (!whites.isEmpty() && !visited[n - 1][n - 1]) {
				cur = whites.removeFirst();
				for (int i = 0; i < 4; i++) {
					nx = cur[0] + dx[i];
					ny = cur[1] + dy[i];
					if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) {
						if (rooms[nx][ny] == 1) {
							whites.addLast(new int[] { nx, ny });
							visited[nx][ny] = true;
						} else {
							rooms[nx][ny] = 1;
							blacks.addLast(new int[] { nx, ny });
							visited[nx][ny] = true;
						}
					}
				}
			}
			if (visited[n - 1][n - 1])
				break;
			// 목적지에 도달하기 위해 흰 방으로 바꿔야 하는 검은 방의 수
			res++;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int[][] rooms = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				rooms[i][j] = br.read() - 48;
			// '\n'
			br.read();
		}
		bw.append(String.valueOf(solve(rooms, n, new int[] { -1, 1, 0, 0 }, new int[] { 0, 0, -1, 1 }))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}