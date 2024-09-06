import java.io.IOException;

public class Main {
	private static int[] seq;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int findLowerBound(int val) {
		int start = 0, end = 41, mid;

		while (start < end) {
			mid = (start + end) >>> 1;
			if (seq[mid] < val)
				start = mid + 1;
			else
				end = mid;
		}
		// val은 최대 10억이므로, 무조건 seq의 마지막 값보다 작다.
		return start;
	}

	public static void main(String[] args) throws IOException {
		int t = readInt();
		StringBuilder sb = new StringBuilder();

		seq = new int[42];
		seq[0] = 1;
		seq[1] = 2;
		// seq의 인덱스가 AVL 트리의 최대 높이를 나타낸다.
		// seq의 값은 최대 높이를 "인덱스 값"으로 갖는 AVL 트리의 노드 개수의 개수를 나타낸다.
		for (int i = 2; i < 42; i++)
			seq[i] = seq[i - 2] + seq[i - 1];
		for (int i = 1; i < 42; i++)
			seq[i] += seq[i - 1];
		while (t-- > 0) {
			sb.append(findLowerBound(readInt()) + 1).append('\n');
		}
		System.out.print(sb.toString());
	}
}