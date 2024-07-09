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

	// 1 2 3 4 5 5 5 5 5 5 5 6 이 대상 범위고 k가 7이라면, k번째 숫자는 숫자 5여야 한다.
	// 각 숫자보다 작은 수의 개수: 0, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 11이다.
	// -> 자신보다 작은 수의 개수가 k - 1인 6과 같거나 6보다 큰 첫 번째 인덱스를 찾아 같으면 해당 인덱스를, 크다면 그 직전 인덱스를
	// 반환하면 된다. -> lower bound

	// 문제 조건에 1 <= k <= j - i + 1이 주어졌으므로, k번째 숫자는 무조건 존재한다.

	// 1 2 3 4 5 5 5 5 5 5 5 5 에 k가 7이라면?
	// 0 1 2 3 4 4 4 4 4 4 4 4 -> 마지막 숫자 반환
	// 근데 start, end가 배열의 범위가 아니라 그냥 [-200, 200] 범위의 정수이므로, 마지막 숫자라는 게 없다.
	// -> start - 1을 반환하면 안되는게, 그 숫자가 주어진 배열의 범위 내에 있는지 알 수 없다. -> lower bound 방식으로
	// 하면 안될듯
	private static int findKth(int left, int right, int k) {
		int start = -200, end = 200, mid;

		while (start <= end) {
			// >>>가 아닌 >>를 써야한다. (start + end) / 2 가 음수일 수 있기 때문이다.
			mid = (start + end) >> 1;
			if (countSmaller(1, 0, arrLength - 1, left, right, mid) < k)
				start = mid + 1;
			else
				end = mid - 1;
		}
		return end;
	}

	// left, right 범위 내에서 x보다 작은 수의 개수를 세서 반환
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