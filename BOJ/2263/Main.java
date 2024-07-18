import java.io.IOException;

public class Main {
	private static int n, preIdx = 0;
	private static int[] inOrder, postOrder, preOrder, mapper;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	// 프리오더 순서로 이진 트리를 방문하는 함수
	private static void solve(int inStart, int inEnd, int postStart, int postEnd) {
		int rootNode, inRoot, postLeftEnd;

		// 종료 조건. 올바른 코드면 둘 중 하나만 확인해도 된다. 어차피 둘의 범위가 같기 때문이다.
		if (inStart > inEnd && postStart > postEnd)
			return;
		// 루트 먼저 방문
		preOrder[preIdx++] = rootNode = postOrder[postEnd];
		inRoot = mapper[rootNode];
		/*
		 * inOrder의 왼쪽 서브트리: [inStart, inRoot - 1]
		 * postOrder의 왼쪽 서브트리: [postStart, postStart + (inRoot - inStart - 1)]
		 * inOrder의 오른쪽 서브트리: [inRoot + 1, inEnd]
		 * postOrder의 오른쪽 서브트리: [postStart + (inRoot - inStart - 1) + 1, postEnd - 1]
		 */
		postLeftEnd = postStart + (inRoot - inStart - 1);
		// 왼쪽 서브트리 방문
		solve(inStart, inRoot - 1, postStart, postLeftEnd);
		// 오른쪽 서브트리 방문
		solve(inRoot + 1, inEnd, postLeftEnd + 1, postEnd - 1);
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		inOrder = new int[n = readInt()];
		postOrder = new int[n];
		preOrder = new int[n];
		mapper = new int[n + 1];
		for (int i = 0; i < n; i++)
			mapper[inOrder[i] = readInt()] = i;
		for (int i = 0; i < n; i++)
			postOrder[i] = readInt();
		solve(0, n - 1, 0, n - 1);
		for (int node : preOrder)
			sb.append(node).append(' ');
		System.out.println(sb.toString());
	}
}