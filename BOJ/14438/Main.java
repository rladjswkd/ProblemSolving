
/*
 * 세그먼트 트리
 */
import java.io.IOException;

public class Main {
	// 리프 노드 개수가 N(주어진 수열의 길이) 이상이면서 최소인 "완전 이진 트리"
	private static int[] seq, tree;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void makeTree(int node, int start, int end) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = (node << 1) + 1;

		if (start == end)
			tree[node] = seq[start];
		else {
			makeTree(lChild, start, mid);
			makeTree(rChild, mid + 1, end);
			tree[node] = Math.min(tree[lChild], tree[rChild]);
		}
	}

	private static void updateTree(int node, int start, int end, int target, int value) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = (node << 1) + 1;

		if (target < start || end < target)
			return;
		if (start == end)
			tree[node] = value;
		else {
			// 왼쪽 서브트리와 오른쪽 서브트리 중 하나라도 값의 업데이트가 일어났는지 확인해(boolean 반환 등을 이용)
			// 업데이트가 일어났을 때만 tree[node]를 업데이트하게 해도 된다.
			updateTree(lChild, start, mid, target, value);
			updateTree(rChild, mid + 1, end, target, value);
			tree[node] = Math.min(tree[lChild], tree[rChild]);
		}
	}

	private static int searchTree(int node, int start, int end, int left, int right) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = (node << 1) + 1;

		if (right < start || end < left)
			return Integer.MAX_VALUE;
		if (left <= start && end <= right)
			return tree[node];
		return Math.min(searchTree(lChild, start, mid, left, right), searchTree(rChild, mid + 1, end, left, right));
	}

	public static void main(String[] args) throws IOException {
		int seqLength = readInt(), queryCount;
		StringBuilder sb = new StringBuilder();

		seq = new int[seqLength];
		tree = new int[1 << (int) Math.ceil(Math.log(seqLength) / Math.log(2)) + 1];
		for (int i = 0; i < seqLength; i++)
			seq[i] = readInt();
		makeTree(1, 0, seqLength - 1);
		queryCount = readInt();
		for (int i = 0; i < queryCount; i++) {
			if (readInt() == 1)
				updateTree(1, 0, seqLength - 1, readInt() - 1, readInt());
			else
				sb.append(searchTree(1, 0, seqLength - 1, readInt() - 1, readInt() - 1)).append('\n');
		}
		System.out.print(sb.toString());
	}
}