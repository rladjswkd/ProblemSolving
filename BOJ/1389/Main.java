import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int[] res;
	private static boolean[][] vis;
	private static Map<Integer, List<Integer>> mapper = new HashMap<>();

	private static int solve() {
		int count, inter, size, ret = 0;
		Deque<Integer> q = new ArrayDeque<>();

		for (int user = 0; user < res.length; user++) {
			count = 0;
			q.addLast(user);
			while (!q.isEmpty()) {
				size = q.size();
				count++;
				while (size-- > 0) {
					inter = q.removeFirst();
					for (int friend : mapper.get(inter)) {
						if (user != friend && !vis[user][friend]) {
							res[user] += count;
							vis[user][friend] = true;
							q.addLast(friend);
						}
					}
				}
			}
		}
		for (int i = 1; i < res.length; i++)
			if (res[i] < res[ret])
				ret = i;
		return ret + 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int userCount, linkCount, u, v;

		userCount = readInt();
		linkCount = readInt();
		res = new int[userCount];
		vis = new boolean[userCount][userCount];
		while (linkCount-- > 0) {
			u = readInt() - 1;
			v = readInt() - 1;
			if (!mapper.containsKey(u))
				mapper.put(u, new ArrayList<>());
			if (!mapper.containsKey(v))
				mapper.put(v, new ArrayList<>());
			mapper.get(u).add(v);
			mapper.get(v).add(u);
		}
		bw.append(String.valueOf(solve())).append('\n');
		bw.flush();
		bw.close();
	}

	static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}