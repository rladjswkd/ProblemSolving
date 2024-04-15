import java.io.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] arr;
	private static int[] tree;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void makeTree(int node, int start, int end) {
		if (start == end)
			tree[node] = arr[start];
		else {
			makeTree(node * 2, start, (start + end) / 2);
			makeTree(node * 2 + 1, (start + end) / 2 + 1, end);
			tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
		}
	}

	private static int findMin(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return Integer.MAX_VALUE;
		if (left <= start && end <= right)
			return tree[node];
		else {
			return Math.min(findMin(node * 2, start, (start + end) / 2, left, right),
					findMin(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
		}
	}

	public static void main(String[] args) throws IOException {
		int length = readInt(), count = readInt();
		StringBuilder sb = new StringBuilder();

		arr = new int[length];
		for (int i = 0; i < length; i++)
			arr[i] = readInt();
		tree = new int[1 << (int) Math.ceil(Math.log(length) / Math.log(2)) + 1];
		makeTree(1, 0, length - 1);
		for (int i = 0; i < count; i++)
			sb.append(findMin(1, 0, length - 1, readInt() - 1, readInt() - 1)).append('\n');
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}