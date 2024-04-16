import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/*
 * 하향식 DP(재귀 + 메모이제이션) + dfs
 * 	메모이제이션은 그냥 큰 문제부터 풀며 푼 결과를 저장해놓는 기법을 말한다.
 * 큰 문제부터 풀고, 작은 문제들을 푼다.
 * 큰 문제를 푸는 과정에 이미 풀어낸 작은 문제는 다시 풀지 않고 메모이제이션을 활용해 기존에 푼 결과값을 반환한다.
 * 
 * dp의 현재 칸은 "현재 칸에서 목표 지점까지 가는 경로의 수"를 나타낸다.
 * 
 * 상향식으로는 풀 수 없나..?
 * 
 * priority queue + bfs -> DAG(directed acyclic graph) 찾아보기
 * 
 * 
 * 
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int h, w;
	private static int[][] board, dp;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int backtrack(int x, int y) {
		// 오른쪽 하단에 도착하면 해당 경로의 모든 칸에 1을 더해준다.
		if (x == h - 1 && y == w - 1)
			return 1;
		// 만약 현재 칸이 이전에 탐색한 칸이면 그 칸의 dp값을 반환한다.
		if (dp[x][y] > -1)
			return dp[x][y];
		// 일단 탐색한 칸은 0으로 초기화한다.
		dp[x][y] = 0;
		// 상하좌우 확인
		if (x > 0 && board[x][y] > board[x - 1][y])
			dp[x][y] += backtrack(x - 1, y);
		if (x < board.length - 1 && board[x][y] > board[x + 1][y])
			dp[x][y] += backtrack(x + 1, y);
		if (y > 0 && board[x][y] > board[x][y - 1])
			dp[x][y] += backtrack(x, y - 1);
		if (y < board[0].length - 1 && board[x][y] > board[x][y + 1])
			dp[x][y] += backtrack(x, y + 1);
		return dp[x][y];
	}

	public static void main(String[] args) throws IOException {
		h = readInt();
		w = readInt();
		board = new int[h][w];
		dp = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				board[i][j] = readInt();
				dp[i][j] = -1;
			}
		}
		bw.append(String.valueOf(backtrack(0, 0))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}