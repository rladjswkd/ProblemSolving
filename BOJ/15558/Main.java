import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int n, k, res;
	private static int[][] lines;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] line;
		Deque<int[]> q = new ArrayDeque<>();
		boolean[][] visited;
		int start = 0, number, index, size;

		lines = new int[2][n = readInt()];
		k = readInt();
		line = lines[0];
		for (int i = 0; i < n; i++)
			line[i] = System.in.read() & 15;
		System.in.read();
		line = lines[1];
		for (int i = 0; i < n; i++)
			line[i] = System.in.read() & 15;
		System.in.read();
		visited = new boolean[2][n];
		res = 0;
		q.add(new int[] { 0, 0 });
		visited[0][0] = true;
		// line 재활용
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				line = q.removeFirst();
				number = line[0];
				index = line[1];
				// 현재 칸이 사라진 칸일 경우
				if (index < start)
					continue;
				// 게임을 클리어할 수 있는 경우
				if (index + 1 >= n || index + k >= n) {
					res = 1;
					break;
				}
				// 게임을 클리어할 수 있을 지 아직 판단할 수 없을 때
				// 한 칸 전진이 가능할 때
				if (!visited[number][index + 1] && lines[number][index + 1] == 1) {
					visited[number][index + 1] = true;
					q.addLast(new int[] { number, index + 1 });
				}
				// 한 칸 후퇴가 가능할 때
				if (index - 1 >= start && !visited[number][index - 1] && lines[number][index - 1] == 1) {
					visited[number][index - 1] = true;
					q.addLast(new int[] { number, index - 1 });
				}
				// 반대편 줄에서 k 칸 전진이 가능할 때
				if (!visited[number ^ 1][index + k] && lines[number ^ 1][index + k] == 1) {
					visited[number ^ 1][index + k] = true;
					q.addLast(new int[] { number ^ 1, index + k });
				}
			}
			start++;
		}
		System.out.println(res);
	}
}