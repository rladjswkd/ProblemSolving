import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int logCount, queryCount;
	private static Log[] logs;
	private static int[] ds;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int find(int node) {
		int root = node, upper;

		while (ds[root] >= 0)
			root = ds[root];
		while (ds[node] >= 0) {
			upper = ds[node];
			ds[node] = root;
			node = upper;
		}
		return root;
	}

	private static void union(int u, int v) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return;
		if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		Log cur;
		StringBuilder sb = new StringBuilder();

		logs = new Log[logCount = readInt()];
		queryCount = readInt();
		ds = new int[logCount];
		for (int i = 0; i < logCount; i++) {
			ds[i] = -1;
			logs[i] = new Log(readInt(), readInt(), readInt(), i);
		}
		Arrays.sort(logs);
		cur = logs[0];
		for (int i = 1; i < logCount; i++) {
			if (logs[i].xFrom <= cur.xTo)
				union(cur.index, logs[i].index);
			if (cur.xTo < logs[i].xTo)
				cur = logs[i];
		}
		for (int i = 0; i < queryCount; i++)
			if (find(readInt() - 1) == find(readInt() - 1))
				sb.append(1).append('\n');
			else
				sb.append(0).append('\n');
		System.out.print(sb.toString());
	}

	static class Log implements Comparable<Log> {
		int xFrom, xTo, y, index;

		Log(int xFrom, int xTo, int y, int index) {
			this.xFrom = xFrom;
			this.xTo = xTo;
			this.y = y;
			this.index = index;
		}

		@Override
		public int compareTo(Log o) {
			return this.xFrom - o.xFrom;
		}
	}
}