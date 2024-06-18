
/*
 * 세그먼트 트리
 */
import java.io.IOException;

public class Main {
	private static int[] seq;
	private static long[] tree;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static long calcRangeSum(int node, int start, int end, int left, int right) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = lChild + 1;

		if (right < start || end < left)
			return 0;
		else if (left <= start && end <= right)
			return tree[node];
		else
			return calcRangeSum(lChild, start, mid, left, right) + calcRangeSum(rChild, mid + 1, end, left, right);
	}

	private static void modify(int node, int start, int end, int target, int diff) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = lChild + 1;

		if (target < start || end < target)
			return;
		tree[node] -= diff;
		if (start != end) {
			modify(lChild, start, mid, target, diff);
			modify(rChild, mid + 1, end, target, diff);
		}
	}

	public static void main(String[] args) throws IOException {
		int cmdCount, arg1, arg2;
		StringBuilder sb = new StringBuilder();

		seq = new int[readInt()];
		tree = new long[1 << (int) Math.ceil(Math.log(seq.length) / Math.log(2)) + 1];
		// 초반의 트리는 모든 노드의 값이 0이라고 문제에 주어졌으므로 트리 노드들의 값을 연산하지 않는다.
		cmdCount = readInt();
		for (int i = 0; i < cmdCount; i++) {
			if (readInt() == 0) {
				arg1 = readInt() - 1;
				arg2 = readInt() - 1;
				sb.append(calcRangeSum(1, 0, seq.length - 1, Math.min(arg1, arg2), Math.max(arg1, arg2))).append('\n');
			} else {
				arg1 = readInt() - 1;
				arg2 = readInt();
				modify(1, 0, seq.length - 1, arg1, seq[arg1] - arg2);
				seq[arg1] = arg2;
			}
		}
		System.out.print(sb.toString());
	}
}