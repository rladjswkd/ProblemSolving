import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/*
 * 1차 시도: 백트래킹 - 현재 기울인 횟수, 빨간 공의 위치, 파란 공의 위치, 보드 정보, 기울인 방향 정보
 */
public class Main {
	private static char[][] board;
	private static int res = Integer.MAX_VALUE;
	private static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	/*
	 * 1. 빨간 공이 구멍에 빠졌지만, 파란 공은 구멍에 빠지지 않은 경우 - res와 count 중 작은 값으로 res를 업데이트
	 * 2. 빨간 공과 파란 공이 모두 구멍에 빠진 경우 - res를 업데이트하지 않고 리턴.
	 * 기울였을 때의 결과를 한 번의 재귀 호출로 판단해야한다.
	 * 그렇지 않고 한 칸씩 이동할 때마다 함수를 한 번 호출하면 리턴이 소용없다.
	 * 기울였을 때의 결과가 유용하지 않은 경우 바로 다음 방향으로 기울이는 코드가 실행되어야 하기 때문이다.
	 * 3. 빨간 공은 구멍에 빠지지 않고, 파란 공만 구멍에 빠진 경우 - res를 업데이트하지 않고 리턴.
	 * 4. 빨간 공과 파란 공 모두 벽이나 다른 공에 진로가 막힌 경우 - 자신과 자신의 역방향(상-하, 좌-우)을 제외한 다른 방향으로
	 * 기울이기
	 * 0(상) - 2(좌), 3(우)
	 * 1(하) - 2, 3
	 * 2 - 0, 1
	 * 3 - 0, 1
	 * 현재 방향으로 기울이기 전 상태를 A라고 하면, A에서도 현재 방향을 제외한 나머지 방향들을 확인할텐데,
	 * 현재 방향의 역방향으로 기울여봐야 A 상태로 돌아가는 것이고, 나머지 방향에 대한 중복 체크를 할 뿐이다.
	 */
	private static void solve(int count, int dir, Point red, Point blue) {
		int rx = red.x, ry = red.y, bx = blue.x, by = blue.y;

		// count가 10 초과면 리턴
		if (count > 10)
			return;
		// 빨간 공과 파란 공 중 하나라도 '#' 바로 직전 칸에 위치하거나, 'O'에 위치할 때까지 이동시키기
		while (board[rx][ry] != 'O' && board[bx][by] != 'O'
				&& board[rx + dx[dir]][ry + dy[dir]] != '#' && board[bx + dx[dir]][by + dy[dir]] != '#') {
			rx += dx[dir];
			ry += dy[dir];
			bx += dx[dir];
			by += dy[dir];
		}
		// 빨간 공이 'O'에 위치하지 않은 경우에 이동.
		// 파란 공이 'O'에 위치했다면, 빨간 공과 파란 공의 위치가 같아지거나 빨간 공의 위치가 '#'의 직전 칸일 때까지
		// 파란 공이 'O'에 위치하지 않았다면, 빨간 공의 위치가 파란 공의 직전 칸이거나 '#'의 직전 칸일 때까지
		while (board[rx][ry] != 'O' && board[rx + dx[dir]][ry + dy[dir]] != '#'
				&& ((board[bx][by] == 'O' && (rx != bx || ry != by))
						|| (board[bx][by] != 'O' && (rx + dx[dir] != bx || ry + dy[dir] != by)))) {
			rx += dx[dir];
			ry += dy[dir];
		}
		// 파란 공이 'O'에 위치하지 않은 경우에 이동.
		// 빨간 공이 'O'에 위치했다면, 파란 공과 빨간 공의 위치가 같아지거나 파란 공의 위치가 '#'의 직전 칸일 때까지
		// 빨간 공이 'O'에 위치하지 않았다면, 파란 공의 위치가 빨간 공의 직전 칸이거나 '#'의 직전 칸일 때까지
		while (board[bx][by] != 'O' && board[bx + dx[dir]][by + dy[dir]] != '#'
				&& ((board[rx][ry] == 'O' && (bx != rx || by != ry))
						|| (board[rx][ry] != 'O' && (bx + dx[dir] != rx || by + dy[dir] != ry)))) {
			bx += dx[dir];
			by += dy[dir];
		}
		// 1. 빨간 공이 구멍에 빠졌지만, 파란 공은 구멍에 빠지지 않은 경우
		if (board[rx][ry] == 'O' && board[bx][by] != 'O') {
			if (count < res)
				res = count;
			return;
		}
		// 2. 빨간 공과 파란 공이 모두 구멍에 빠진 경우 + 3. 빨간 공은 구멍에 빠지지 않고, 파란 공만 구멍에 빠진 경우
		if (board[bx][by] == 'O')
			return;
		// 4. 빨간 공과 파란 공 모두 벽이나 다른 공에 진로가 막힌 경우
		if (dir < 2) {
			solve(count + 1, 2, new Point(rx, ry), new Point(bx, by));
			solve(count + 1, 3, new Point(rx, ry), new Point(bx, by));
		} else {
			solve(count + 1, 0, new Point(rx, ry), new Point(bx, by));
			solve(count + 1, 1, new Point(rx, ry), new Point(bx, by));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split("\\s");
		Point r = new Point(), b = new Point();

		board = new char[Integer.parseInt(input[0])][Integer.parseInt(input[1])];
		for (int i = 0; i < board.length; i++) {
			board[i] = br.readLine().toCharArray();
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 'R') {
					r.setPoint(i, j);
					board[i][j] = '.';
				} else if (board[i][j] == 'B') {
					b.setPoint(i, j);
					board[i][j] = '.';
				}
			}
		}
		for (int dir = 0; dir < 4; dir++)
			// solve(1, dir, r, b);
			solve(1, dir, r, b);
		if (res > 10)
			res = -1;
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}

	static class Point {
		int x, y;

		Point() {
			this(0, 0);
		}

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Point setPoint(int x, int y) {
			this.x = x;
			this.y = y;
			return this;
		}
	}
}