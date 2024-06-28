
/*
 * 위상 정렬
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static int[] degree;
	private static List<List<Integer>> orders;
	private static Deque<Integer> q;
	private static StringBuilder sb;
	private static int listSize;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve() {
		int cur;

		listSize = 0;
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			listSize++;
			for (int next : orders.get(cur))
				if (--degree[next] == 0)
					q.addLast(next);
			sb.append(cur + 1).append('\n');
		}
	}

	public static void main(String[] args) throws IOException {
		int singerCount = readInt(), pdCount = readInt(), orderCount, prev, post;

		degree = new int[singerCount];
		orders = new ArrayList<>();
		q = new ArrayDeque<>();
		sb = new StringBuilder();
		for (int i = 0; i < singerCount; i++)
			orders.add(new ArrayList<>());
		for (int i = 0; i < pdCount; i++) {
			orderCount = readInt();
			prev = -1;
			while (orderCount-- > 0) {
				post = readInt() - 1;
				if (prev != -1) {
					orders.get(prev).add(post);
					degree[post]++;
				}
				prev = post;
			}
		}
		for (int i = 0; i < singerCount; i++)
			if (degree[i] == 0)
				q.addLast(i);
		solve();
		if (listSize == singerCount)
			System.out.print(sb.toString());
		else
			System.out.println(0);
	}
}