import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/*
 * https://www.youtube.com/watch?v=wU6udHRIkcc
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure#Finding_set_representatives
 * 
 * 배열로 disjoint set을 표현한다.
 * 배열의 각 원소는 자신이 포함된 그래프의 루트 노드를 표현한다.
 * 만약 현재 원소가 루트 노드라면 원소는 음수로 표현하며, 그 값은 자신이 포함된 그래프의 노드 개수를 표현한다.
 * 
 * 아래 주석처리한 코드는 통과하는데 이 코드는 왜 통과가 안될까 -> find 함수에서 휴먼 에러
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static StringBuilder sb = new StringBuilder();
	// disjoint set
	private static int[] ds;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int find(int node) {
		int root = node, upper;

		// 경로 압축
		// while (ds[root] > 0) 이걸 >=이 아닌 >으로 해서 97퍼센트에서 계속 틀렸다.
		while (ds[root] >= 0)
			root = ds[root];
		// while (ds[node] > 0) {
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
		// u를 포함한 그래프의 사이즈가 더 클 때(더 많은 노드를 포함할 때)
		if (ds[uRoot] < ds[vRoot]) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		}
		// v를 포함한 그래프의 사이즈가 더 클 때
		else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
	}

	public static void main(String[] args) throws IOException {
		int len = readInt(), count = readInt(), c, a, b;

		ds = new int[len + 1];
		for (int i = 0; i < ds.length; i++)
			ds[i] = -1;
		for (int i = 0; i < count; i++) {
			c = readInt();
			a = readInt();
			b = readInt();
			if (c == 0)
				union(a, b);
			else if (find(a) == find(b))
				sb.append("YES\n");
			else
				sb.append("NO\n");
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}