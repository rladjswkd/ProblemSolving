import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

class Main {
	private static int arrLength, queryCount, treeSize;
	private static int[] arr;
	private static List<List<Integer>> tree;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			n = ~n + 1;
		return n;
	}

	private static void merge(int lc, int rc, List<Integer> res) {
		List<Integer> lSub = tree.get(lc), rSub = tree.get(rc);
		int lIdx = 0, rIdx = 0;

		while (lIdx < lSub.size() && rIdx < rSub.size()) {
			if (lSub.get(lIdx) < rSub.get(rIdx))
				res.add(lSub.get(lIdx++));
			else
				res.add(rSub.get(rIdx++));
		}
		while (lIdx < lSub.size())
			res.add(lSub.get(lIdx++));
		while (rIdx < rSub.size())
			res.add(rSub.get(rIdx++));
	}

	private static void makeTree(int node, int start, int end) {
		int mid = (start + end) >>> 1, lc = node << 1, rc = lc + 1;

		if (start == end) {
			tree.get(node).add(arr[start]);
			return;
		}
		makeTree(lc, start, mid);
		makeTree(rc, mid + 1, end);
		merge(lc, rc, tree.get(node));
	}

	private static int findLowerBound(List<Integer> l, int value) {
		int start = 0, end = l.size() - 1, mid;

		while (start < end) {
			mid = (start + end) >>> 1;
			if (l.get(mid) < value)
				start = mid + 1;
			else
				end = mid;
		}
		return l.get(start) < value ? l.size() : start;
	}

	private static int findKth(int left, int right, int k) {
		int start = -1_000_000_000, end = 1_000_000_000, mid;

		while (start <= end) {
			mid = (start + end) >> 1;
			if (countSmaller(1, 0, arrLength - 1, left, right, mid) < k)
				start = mid + 1;
			else
				end = mid - 1;
		}
		return end;
	}

	private static int countSmaller(int node, int start, int end, int left, int right, int x) {
		int mid = (start + end) >>> 1, lc = node << 1, rc = lc + 1;
		if (right < start || end < left)
			return 0;
		if (left <= start && end <= right)
			return findLowerBound(tree.get(node), x);
		return countSmaller(lc, start, mid, left, right, x) + countSmaller(rc, mid + 1, end, left, right, x);
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		arr = new int[arrLength = readInt()];
		queryCount = readInt();
		for (int i = 0; i < arrLength; i++)
			arr[i] = readInt();
		tree = new ArrayList<>(treeSize = (1 << (int) Math.ceil(Math.log(arrLength) / Math.log(2)) + 1));
		for (int i = 0; i < treeSize; i++)
			tree.add(new ArrayList<>());
		makeTree(1, 0, arrLength - 1);
		while (queryCount-- > 0)
			sb.append(findKth(readInt() - 1, readInt() - 1, readInt())).append('\n');
		System.out.print(sb.toString());
	}
}