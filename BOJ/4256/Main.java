import java.io.IOException;

public class Main {
	private static int n, length;
	private static int[] pre, in, preIdx, inIdx, post;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while ((n = System.in.read()) < 48 || 57 < n)
			continue;
		n &= 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int preStart, int preEnd, int inStart, int inEnd) {
		int root, inRoot, preLeftStart, preRightStart;

		if (preStart < preEnd) {
			root = pre[preStart];
			inRoot = inIdx[root];
			preLeftStart = preStart + 1;
			preRightStart = preLeftStart + (inRoot - inStart);
			// post는 왼쪽 서브트리, 오른쪽 서브트리, 루트 순서로 탐색한다.
			// 왼쪽 서브트리 탐색
			solve(preStart + 1, preRightStart, inStart, inRoot);
			// 오른쪽 서브트리 탐색
			solve(preRightStart, preEnd, inRoot + 1, inEnd);
			// 루트 탐색
			post[length++] = root;
		}
	}

	public static void main(String[] args) throws IOException {
		int t = readInt();
		StringBuilder sb = new StringBuilder();
		String[] line;

		pre = new int[1000];
		in = new int[1000];
		preIdx = new int[1000];
		inIdx = new int[1000];
		post = new int[1000];
		while (t-- > 0) {
			n = readInt();
			for (int i = 0; i < n; i++)
				preIdx[pre[i] = readInt() - 1] = i;
			for (int i = 0; i < n; i++)
				inIdx[in[i] = readInt() - 1] = i;
			length = 0;
			solve(0, n, 0, n);
			for (int i = 0; i < n; i++)
				sb.append(post[i] + 1).append(' ');
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}