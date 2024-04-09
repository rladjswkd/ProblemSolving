import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

/*
 * 1. 상, 하, 좌, 우 중 한 칸이라도 값이 0인 치즈 칸이 있다면 큐에 모두 추가
 * 2. 현재 큐에 담긴 치즈 칸들에 대해 반복하면서 그 칸의 상, 하, 좌, 우 중 치즈인 칸들을 큐에 추가하고, 기존 큐의 사이즈만큼 반복했다면 시간 증가
 * 3. 큐가 빌 때까지 반복
 * 
 * 치즈에 뚫려있는 구멍은 값이 0이지만 공기가 들어있지 않음에 주의!!!
 * 영역을 넓혀가는 주체가 치즈가 아니라 공기다!!!!
 * 
 * 녹아내린 치즈 한 칸 마다 해당 치즈가 녹아서 공기가 들어갈 빈 공간이 열렸는지 확인하는 로직 필요
 */
public class Main {
	private static int[][] board;
	private static int cheese = 0;
	private static Deque<int[]> airQ = new ArrayDeque<>();

	private static void processInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		String line;

		input = br.readLine().split("\\s");
		board = new int[Integer.parseInt(input[0])][Integer.parseInt(input[1])];
		for (int i = 0; i < board.length; i++) {
			line = br.readLine();
			for (int j = 0; j < line.length(); j += 2) {
				board[i][j / 2] = line.charAt(j) - 48;
				if (board[i][j / 2] == 1)
					cheese++;
			}
		}
		br.close();
	}

	private static void removeCheeseEdge() {
		boolean[][] vis = new boolean[board.length][board[0].length];
		int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 }, cur;

		airQ.addLast(new int[] { 0, 0 });
		vis[0][0] = true;
		while (!airQ.isEmpty()) {
			cur = airQ.removeFirst();
			for (int i = 0; i < 4; i++) {
				if (cur[0] + dx[i] < 0 || cur[1] + dy[i] < 0
						|| cur[0] + dx[i] >= board.length || cur[1] + dy[i] >= board[0].length
						|| vis[cur[0] + dx[i]][cur[1] + dy[i]])
					continue;
				vis[cur[0] + dx[i]][cur[1] + dy[i]] = true;
				if (board[cur[0] + dx[i]][cur[1] + dy[i]] == 0)
					airQ.addLast(new int[] { cur[0] + dx[i], cur[1] + dy[i] });
				else {
					board[cur[0] + dx[i]][cur[1] + dy[i]] = 0;
					cheese--;
				}
			}
		}
	}

	private static int[] solve() {
		int time = 0, prevCheese = 0;

		while (cheese > 0) {
			prevCheese = cheese;
			removeCheeseEdge();
			time++;
		}
		return new int[] { time, prevCheese };
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] result;

		processInput();
		result = solve();
		bw.append(new StringBuilder().append(result[0]).append('\n').append(result[1]).append('\n').toString());
		bw.flush();
		bw.close();
	}
}