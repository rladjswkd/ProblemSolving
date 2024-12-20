import java.io.IOException;

public class Main {
	private static int res;
	private static int[][] classroom;
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	// 0x0 ~ 0x1ffffff까지의 비트마스크 값(1로 설정된 비트가 선택된 학생의 번호)에 대해 방문 확인
	private static boolean[] visited;

	private static void solve(int sCount, int yCount, int mask) {
		int x, y, nx, ny, newMask;

		if (sCount + yCount == 7) {
			if (sCount >= 4)
				res++;
			return;
		}
		for (int cur = 0; cur < 25; cur++) {
			if ((mask & 1 << cur) > 0) {
				x = cur / 5;
				y = cur % 5;
				for (int i = 0; i < 4; i++) {
					nx = x + dx[i];
					ny = y + dy[i];
					newMask = mask | 1 << nx * 5 + ny;
					if (0 <= nx && nx < 5 && 0 <= ny && ny < 5 && !visited[newMask]) {
						visited[newMask] = true;
						if (classroom[nx][ny] == 'S')
							solve(sCount + 1, yCount, newMask);
						else
							solve(sCount, yCount + 1, newMask);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		res = 0;
		classroom = new int[5][5];
		visited = new boolean[1 << 25];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++)
				classroom[i][j] = System.in.read();
			System.in.read();
		}
		for (int i = 0; i < 25; i++) {
			// 사실상 0 ~ 24까지의 시작 지점을 순회하면서 서로 중복되거나 겹칠 일이 없기 때문에 방문 처리를 하지 않아도 문제 없다.
			// 코드의 통일성을 위해 그냥 해주자.
			visited[i] = true;
			if (classroom[i / 5][i % 5] == 'S')
				solve(1, 0, 1 << i);
			else
				solve(0, 1, 1 << i);
		}
		System.out.println(res);
	}
}