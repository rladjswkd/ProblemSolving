
/*
 * 세그먼트 트리
 */
import java.io.IOException;

public class Main {
	private static int[] seq;
	// tree[i][0]: 서브트리 내 짝수의 개수 tree[i][1]: 서브트리 내 홀수의 개수
	private static int[][] tree;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void makeTree(int node, int start, int end) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = lChild + 1;

		if (start == end) {
			if ((seq[start] & 1) == 0)
				tree[node][0] = 1;
			else
				tree[node][1] = 1;
		} else {
			makeTree(lChild, start, mid);
			makeTree(rChild, mid + 1, end);
			tree[node][0] = tree[lChild][0] + tree[rChild][0];
			tree[node][1] = tree[lChild][1] + tree[rChild][1];
		}
	}

	private static void updateTree(int node, int start, int end, int target, int value) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = lChild + 1;

		if (target < start || end < target)
			return;
		if (start == end) {
			seq[target] = value;
			tree[node][0] = tree[node][1] = 0;
			if ((value & 1) == 0)
				tree[node][0] = 1;
			else
				tree[node][1] = 1;
		} else {
			updateTree(lChild, start, mid, target, value);
			updateTree(rChild, mid + 1, end, target, value);
			tree[node][0] = tree[lChild][0] + tree[rChild][0];
			tree[node][1] = tree[lChild][1] + tree[rChild][1];
		}
	}

	private static int searchEven(int node, int start, int end, int left, int right) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = lChild + 1;

		if (right < start || end < left)
			return 0;
		if (left <= start && end <= right)
			return tree[node][0];
		return searchEven(lChild, start, mid, left, right) + searchEven(rChild, mid + 1, end, left, right);
	}

	private static int searchOdd(int node, int start, int end, int left, int right) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = lChild + 1;

		if (right < start || end < left)
			return 0;
		if (left <= start && end <= right)
			return tree[node][1];
		return searchOdd(lChild, start, mid, left, right) + searchOdd(rChild, mid + 1, end, left, right);
	}

	public static void main(String[] args) throws IOException {
		int seqLength = readInt(), queryCount, queryType;
		StringBuilder sb = new StringBuilder();

		seq = new int[seqLength];
		tree = new int[1 << (int) Math.ceil(Math.log(seqLength) / Math.log(2)) + 1][2];
		for (int i = 0; i < seqLength; i++)
			seq[i] = readInt();
		makeTree(1, 0, seqLength - 1);
		queryCount = readInt();
		for (int i = 0; i < queryCount; i++) {
			queryType = readInt();
			if (queryType == 1)
				updateTree(1, 0, seqLength - 1, readInt() - 1, readInt());
			else if (queryType == 2)
				sb.append(searchEven(1, 0, seqLength - 1, readInt() - 1, readInt() - 1)).append('\n');
			else if (queryType == 3)
				sb.append(searchOdd(1, 0, seqLength - 1, readInt() - 1, readInt() - 1)).append('\n');
		}
		System.out.print(sb.toString());
	}
}