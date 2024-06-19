
/*
 * 지금 입력받은 두 사람이 포함된 네트워크에 친구가 몇 명 포함되는지를 출력하는 문제.
 * 
 * disjoint-set
 * 
 * 각 테스트 케이스 별 인원 수가 주어지지 않는다 -> 이름을 파싱해서 인원 수를 직접 파악해야 한다.
 * 마찬가지로 int[] ds를 초기화하기 위해선 인원 수를 먼저 파악해야 하므로, 이름을 파싱하는 과정 이후에 연결 정보를 통해 union을 할 수 있다.
 * -> 이름을 파싱하는 과정에서 연결 정보를 별도의 자료 구조에 저장해줘야 한다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] ds;
	private static Map<String, Integer> mapper;
	private static int[][] graph;

	private static int readInt() throws IOException {
		int n = br.read() & 15, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void initialize(int f) throws IOException {
		StringBuilder builder;
		String name1, name2;
		int ch;

		for (int i = 0; i < f; i++) {
			builder = new StringBuilder();
			while ((ch = br.read()) != ' ')
				builder.append((char) ch);
			name1 = builder.toString();
			builder = new StringBuilder();
			while ((ch = br.read()) != '\n')
				builder.append((char) ch);
			name2 = builder.toString();
			mapper.put(name1, mapper.getOrDefault(name1, mapper.size()));
			mapper.put(name2, mapper.getOrDefault(name2, mapper.size()));
			graph[i] = new int[] { mapper.get(name1), mapper.get(name2) };
		}
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

	private static int union(int u, int v) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return ~ds[uRoot] + 1;
		if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
			return ~ds[uRoot] + 1;
		}
		ds[vRoot] += ds[uRoot];
		ds[uRoot] = vRoot;
		return ~ds[vRoot] + 1;
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), f;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			f = readInt();
			mapper = new HashMap<>();
			graph = new int[f][];
			initialize(f);
			ds = new int[mapper.size()];
			for (int i = 0; i < ds.length; i++)
				ds[i] = -1;
			for (int[] rel : graph)
				sb.append(union(rel[0], rel[1])).append('\n');
		}
		System.out.print(sb.toString());
		br.close();
	}
}