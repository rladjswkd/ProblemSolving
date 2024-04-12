import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

// 세그먼트 트리
public class Main {
	private static int[] arr;
	private static int[][] tree;
	private static int[] res;
	// sum, update를 위한 트리 초기화 구현 연습
	// private static void initTree(int node, int start, int end) {
	// if (start == end)
	// tree[node] = arr[start];
	// else {
	// initTree(node * 2, start, (start + end) / 2);
	// initTree(node * 2 + 1, (start + end) / 2 + 1, end);
	// tree[node] = tree[node * 2] + tree[node * 2 + 1];
	// }
	// }

	// sum 구현 연습
	// private static int calculateRangeSum(int node, int start, int end, int left,
	// int right) {
	// if (end < left || right < start)
	// return 0;
	// if (left <= start && end <= right)
	// return tree[node];
	// else
	// return calculateRangeSum(node * 2, start, (start + end) / 2, left, right)
	// + calculateRangeSum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
	// }

	// update 구현 연습
	// private static void updateTree(int node, int start, int end, int index, int
	// diff) {
	// if (index < start || end < index)
	// return;
	// tree[node] += diff;
	// if (start < end) {
	// updateTree(node * 2, start, (start + end) / 2, index, diff);
	// updateTree(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
	// }
	// }

	private static void initTree(int node, int start, int end) {
		if (start == end)
			tree[node] = new int[] { arr[start], arr[start] };
		else {
			initTree(node * 2, start, (start + end) / 2);
			initTree(node * 2 + 1, (start + end) / 2 + 1, end);
			tree[node] = new int[] { Math.min(tree[node * 2][0], tree[node * 2 + 1][0]),
					Math.max(tree[node * 2][1], tree[node * 2 + 1][1]) };
		}
	}

	private static void findMinMax(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return;
		if (left <= start && end <= right) {
			if (tree[node][0] < res[0])
				res[0] = tree[node][0];
			if (res[1] < tree[node][1])
				res[1] = tree[node][1];
		} else {
			findMinMax(node * 2, start, (start + end) / 2, left, right);
			findMinMax(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split("\\s");
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(input[1]);

		arr = new int[Integer.parseInt(input[0])];
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(br.readLine());
		tree = new int[1 << (int) Math.ceil(Math.log(arr.length) / Math.log(2)) +
				1][];
		initTree(1, 0, arr.length - 1);
		res = new int[2];
		for (int i = 0; i < tc; i++) {
			res[0] = Integer.MAX_VALUE;
			res[1] = Integer.MIN_VALUE;
			input = br.readLine().split("\\s");
			findMinMax(1, 0, arr.length - 1, Integer.parseInt(input[0]) - 1,
					Integer.parseInt(input[1]) - 1);
			sb.append(res[0]).append(' ').append(res[1]).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	/*
	 * 위의 코드는 1224ms지만, 입력을 아래와 같이 처리하면 488ms로 빨라진다.
	 * 단, 알고리즘의 차이가 아니므로 무시하자.
	 */
	// public static void main(String[] args) throws IOException {
	// BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	// StringBuilder sb = new StringBuilder();
	// int numCount = read(), queryCount = read();

	// arr = new int[numCount];
	// for (int i = 0; i < arr.length; i++)
	// arr[i] = read();
	// tree = new int[1 << (int) Math.ceil(Math.log(arr.length) / Math.log(2)) +
	// 1][];
	// initTree(1, 0, arr.length - 1);
	// res = new int[2];
	// for (int i = 0; i < queryCount; i++) {
	// res[0] = Integer.MAX_VALUE;
	// res[1] = Integer.MIN_VALUE;
	// findMinMax(1, 0, arr.length - 1, read() - 1, read() - 1);
	// sb.append(res[0]).append(' ').append(res[1]).append('\n');
	// }
	// bw.write(sb.toString());
	// bw.flush();
	// bw.close();
	// }

	// static int read() throws IOException {
	// int c, n = System.in.read() & 15;
	// while ((c = System.in.read()) > 32)
	// n = (n << 3) + (n << 1) + (c & 15);
	// return n;
	// }/
}