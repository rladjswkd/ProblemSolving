
/*
 * disjoint-set
 * 
 * 연결하는 두 점의 root 중 작은 점을 root로 설정
 * 연결하는 두 점의 root가 같으면 사이클 생성!
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int find(int[] ds, int node) {
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

	private static boolean union(int[] ds, int u, int v) {
		int uRoot = find(ds, u), vRoot = find(ds, v);

		if (uRoot == vRoot)
			return true;
		if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		int pointCount = readInt(), roundCount = readInt(), res = 0;
		int[] ds = new int[pointCount];

		for (int i = 0; i < pointCount; i++)
			ds[i] = -1;
		for (int i = 0; i < roundCount; i++)
			if (union(ds, readInt(), readInt()) && res == 0)
				res = i + 1;
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}