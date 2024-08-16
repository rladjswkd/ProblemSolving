import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int n, order;
	private static List<int[]> graph;
	private static int[][] levels;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return sign == 45 ? ~n + 1 : n;
	}

	// in-order 방식으로 트리 순회
	private static void solve(int node, int d) {
		int l, r;
		int[] level = levels[d];

		// 왼쪽 하위 노드
		if ((l = graph.get(node)[0]) != -2)
			solve(l, d + 1);
		// 루트
		level[0] = Math.min(level[0], order);
		level[1] = Math.max(level[1], order);
		order++;
		// 오른쪽 하위 노드
		if ((r = graph.get(node)[1]) != -2)
			solve(r, d + 1);
	}

	public static void main(String[] args) throws IOException {
		int[] node;
		int d = 0, interval = 0, cur, root;

		graph = new ArrayList<>(n = readInt());
		// 트리의 최대 깊이는 노드의 개수인 n이다.
		levels = new int[n][2];
		// root에 모든 노드의 번호 (0 ~ n - 1)을 다 더한 후 왼쪽, 오른쪽 하위 노드로 등장하는 노드들의 번호를 뺸다.
		// 그러면 남는 값이 루트 노드의 번호가 된다.
		root = (n - 1) * n / 2;
		// 노드의 하위 노드 정보는 노드 순서대로 주어지지 않을 수도 있다.
		for (int i = 0; i < n; i++)
			graph.add(new int[] { 0, 0 });
		for (int i = 0; i < n; i++) {
			node = graph.get(readInt() - 1);
			// 왼쪽 하위 노드와 오른쪽 하위 노드가 없다면 그 값은 -2가 저장된다.
			if ((cur = node[0] = readInt() - 1) != -2)
				root -= cur;
			if ((cur = node[1] = readInt() - 1) != -2)
				root -= cur;
			// levels[i][0]은 같은 레벨에서 가장 빨리 방문한 노드의 순서를 저장한다.
			// levels[i][1]은 같은 레베렝서 가장 나중에 방문한 노드의 순서를 저장한다.
			levels[i][0] = 10001;
		}
		order = 0;
		// 문제에서의 행과 열을 0-base로 활용한다.
		solve(root, 0);
		// levels[i][0]이 10001이라면 해당 레벨부터는 노드가 존재하지 않는 것.
		// cur 재활용
		for (int i = 0; i < n && levels[i][0] < 10001; i++) {
			if (interval < (cur = levels[i][1] - levels[i][0])) {
				d = i;
				interval = cur;
			}
		}
		System.out.println(new StringBuilder().append(d + 1).append(' ').append(interval + 1));
	}
}