import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static List<List<int[]>> rel;
	private static int res;
	private static boolean[] visited;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int player, int total) {
		if (player == 11) {
			res = Math.max(res, total);
			return;
		}
		for (int[] ability : rel.get(player)) {
			if (!visited[ability[0]]) {
				visited[ability[0]] = true;
				solve(player + 1, total + ability[1]);
				visited[ability[0]] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int c = readInt(), stat;
		StringBuilder sb = new StringBuilder();

		visited = new boolean[11];
		while (c-- > 0) {
			rel = new ArrayList<>();
			for (int i = 0; i < 11; i++)
				rel.add(new ArrayList<>());
			for (int i = 0; i < 11; i++)
				for (int j = 0; j < 11; j++)
					if ((stat = readInt()) > 0)
						rel.get(i).add(new int[] { j, stat });
			res = 0;
			solve(0, 0);
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}