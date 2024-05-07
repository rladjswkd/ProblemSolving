import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
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

	private static int solve(Map<Integer, Integer> ladders, Map<Integer, Integer> snakes) {
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		int cur, next, size, step = 0;

		q.addLast(1);
		visited[1] = true;
		while (!q.isEmpty() && !visited[100]) {
			size = q.size();
			step++;
			while (size-- > 0 && !visited[100]) {
				cur = q.removeFirst();
				for (int i = 1; i <= 6 && !visited[100]; i++) {
					if (100 < (next = cur + i))
						break;
					if (visited[next])
						continue;
					visited[next] = true;
					if (ladders.containsKey(next)) {
						visited[ladders.get(next)] = true;
						q.addLast(ladders.get(next));
					} else if (snakes.containsKey(next)) {
						visited[snakes.get(next)] = true;
						q.addLast(snakes.get(next));
					} else
						q.addLast(next);
				}
			}
		}
		return step;
	}

	public static void main(String[] args) throws IOException {
		int ladderCount = readInt(), snakeCount = readInt();
		Map<Integer, Integer> ladders = new HashMap<>(ladderCount), snakes = new HashMap<>(snakeCount);

		for (int i = 0; i < ladderCount; i++)
			ladders.put(readInt(), readInt());
		for (int i = 0; i < snakeCount; i++)
			snakes.put(readInt(), readInt());
		bw.append(String.valueOf(solve(ladders, snakes))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}