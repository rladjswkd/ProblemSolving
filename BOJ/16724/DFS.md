```java
import java.io.IOException;

public class Main {
	private static int h, w, res = 0;
	private static int[][] map;
	private static int[][] state;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int solve(int i, int j) {
		int ni = i, nj = j;

		state[i][j] = 1;
		switch (map[i][j]) {
			case 'U':
				ni--;
				break;
			case 'D':
				ni++;
				break;
			case 'L':
				nj--;
				break;
			case 'R':
				nj++;
				break;
			default:
				break;
		}
		// 밖으로 벗어나는 방향이 주어지지 않으므로 ni, nj는 항상 유효한 인덱스다. 방문 체크만 해주면 된다.
		if (state[ni][nj] == 0)
			return (state[i][j] = solve(ni, nj));
		// state[ni][nj]가 1 또는 2면 dfs 경로에 포함된 모든 칸의 state를 2로 설정한다.
		// state[ni][nj]가 1이면 i, j 칸에 세이프 존을 생성한다.
		else if (state[ni][nj] == 1)
			res++;
		return (state[i][j] = 2);
	}

	public static void main(String[] args) throws IOException {
		map = new int[h = readInt()][w = readInt()];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++)
				map[i][j] = System.in.read();
			System.in.read();
		}
		state = new int[h][w];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				if (state[i][j] == 0)
					solve(i, j);
		System.out.println(res);
	}
}
```