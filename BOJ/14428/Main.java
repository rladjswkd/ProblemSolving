
/*
 * 세그먼트 트리
 * 
 * 인덱스를 저장하고 수열 인덱싱을 활용하므로 searchTree에서 현재 start, end 범위가 검색 범위가 아닐 때 Integer.MAX_VALUE를 반환하면 안된다.
 * 애초에 가장 큰 값을 담고 있는 인덱스를 저장해놓고 그 인덱스를 반환하면 어떨까?
 * -> 그 인덱스의 값이 최솟값으로 바뀌면 문제가 생긴다. 따라서 값이 바뀐 인덱스가 가장 큰 값의 인덱스인지 확인하고, 그렇다면 가장 큰 값을 담고 있는 인덱스를 다시 찾아서 업데이트해줘야 한다.
 * 
 * 그냥 -1 반환하고 걸러주자.
 */
import java.io.IOException;

public class Main {
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
			tree[node] = start;
		else {
			makeTree(lChild, start, mid);
			makeTree(rChild, mid + 1, end);
			// tree에는 범위 내에서 가장 작은 값의 인덱스를 저장하고, 그런 값이 여러 개면 가장 작은 인덱스를 저장한다.
			if (seq[tree[lChild]] < seq[tree[rChild]])
				tree[node] = tree[lChild];
			else if (seq[tree[lChild]] > seq[tree[rChild]])
				tree[node] = tree[rChild];
			else
				tree[node] = Math.min(tree[lChild], tree[rChild]);
		}
	}

	private static void updateTree(int node, int start, int end, int target, int value) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = (node << 1) + 1;

		if (target < start || end < target)
			return;
		// 원래 수열의 인덱스를 저장하고, 그 값은 수열 인덱싱을 통해 확인하므로 수열 자체도 업데이트해줘야 한다.
		if (start == end) {
			tree[node] = start;
			seq[start] = value;
		} else {
			updateTree(lChild, start, mid, target, value);
			updateTree(rChild, mid + 1, end, target, value);
			if (seq[tree[lChild]] < seq[tree[rChild]])
				tree[node] = tree[lChild];
			else if (seq[tree[lChild]] > seq[tree[rChild]])
				tree[node] = tree[rChild];
			else
				tree[node] = Math.min(tree[lChild], tree[rChild]);
		}
	}

	private static int searchTree(int node, int start, int end, int left, int right) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = (node << 1) + 1, lResult, rResult;

		if (right < start || end < left)
			return -1;
		if (left <= start && end <= right)
			return tree[node];
		// start, end가 left, right에 포함되거나 두 범위가 겹치는 경우에만 아래 코드가 실행된다.
		// -> lResult와 rResult가 모두 -1을 반환할 일은 없다.
		lResult = searchTree(lChild, start, mid, left, right);
		rResult = searchTree(rChild, mid + 1, end, left, right);
		if (lResult == -1)
			return rResult;
		if (rResult == -1)
			return lResult;
		if (seq[lResult] < seq[rResult])
			return lResult;
		else if (seq[lResult] > seq[rResult])
			return rResult;
		else
			return Math.min(lResult, rResult);
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
				sb.append(searchTree(1, 0, seqLength - 1, readInt() - 1, readInt() - 1) + 1).append('\n');
		}
		System.out.print(sb.toString());
	}
}