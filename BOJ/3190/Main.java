import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.HashMap;

public class Main {
	private static int[][] board;
	private static Map<Integer, Integer> mapper = new HashMap<>();
	private static Deque<int[]> snake = new ArrayDeque<>();
	private static final int APPLE_MASK = 1;
	private static final int BODY_MASK = 2;
	private static final int HEAD_MASK = 4;

	private static void processInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int count = Integer.parseInt(br.readLine());
		String[] input;

		board = new int[n][n];
		while (count-- > 0) {
			input = br.readLine().split("\\s");
			board[Integer.parseInt(input[0]) - 1][Integer.parseInt(input[1]) - 1] = APPLE_MASK;
		}
		count = Integer.parseInt(br.readLine());
		while (count-- > 0) {
			input = br.readLine().split("\\s");
			mapper.put(Integer.parseInt(input[0]), ("D".equals(input[1]) ? 1 : -1)); // D면 1, L이면 -1. 이후 방향 전환에 활용할 값.
		}
		br.close();
	}

	private static boolean isDead() {
		int[] head = snake.getLast();

		return (head[0] < 0 || head[0] >= board.length || head[1] < 0 || head[1] >= board[0].length)
				|| (board[head[0]][head[1]] & BODY_MASK) > 0;
	}

	private static int solve() {
		int timer = 0, dirIdx = 0;
		int[] head, dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 }; // 동 남 서 북

		snake.addLast(new int[] { 0, 0 }); // 행 인덱스, 열 인덱스
		board[0][0] |= HEAD_MASK;
		while (true) {
			timer++;
			head = snake.getLast();
			// 이동한 칸에 사과가 있던 없던 일단 새로운 head를 추가. 기존의 head는 body가 된다.
			// "몸을 늘려서 이동을 먼저 하고 그 다음에 꼬리를 줄이는 것"이므로 일단 새로운 머리를 추가하는 게 논리상으로도 맞다.
			snake.addLast(new int[] { head[0] + dx[dirIdx], head[1] + dy[dirIdx] });
			if (isDead())
				break;
			board[head[0] + dx[dirIdx]][head[1] + dy[dirIdx]] |= HEAD_MASK;
			board[head[0]][head[1]] ^= (BODY_MASK | HEAD_MASK);
			if ((board[head[0] + dx[dirIdx]][head[1] + dy[dirIdx]] & APPLE_MASK) == 0) {
				// 이동한 칸에 사과가 없다면 snake에 새로운 head를 추가하고 꼬리를 제거. 이렇게 하면 snake가 한 칸씩 이동한 효과가 있다.
				board[snake.getFirst()[0]][snake.getFirst()[1]] ^= BODY_MASK;
				snake.removeFirst();
			} else
				// 이동한 칸에 사과가 있다면 board에서 제거하기
				board[head[0] + dx[dirIdx]][head[1] + dy[dirIdx]] ^= APPLE_MASK;

			// 방향 전환 확인
			if (mapper.containsKey(timer))
				dirIdx = (dirIdx + mapper.get(timer) + 4) % 4;
		}
		return timer;
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		processInput();
		bw.append(String.valueOf(solve())).append('\n');
		bw.flush();
		bw.close();
	}
}
