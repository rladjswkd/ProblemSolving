import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] arr;
	private static long[] tree;

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
			tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % 1_000_000_007;
		}
	}

	/*
	 * 만약 arr[index]가 0이라면 담당 범위 내에 arr[index]를 포함하는 모든 노드들은 값이 0일 것이다.
	 * 따라서 arr[index]를 나누고 newVal을 새로 곱해주는 방식으로 처리하면 런타임 오류가 발생한다.
	 * 
	 * 주석처리한 updateTree 코드는 구간 합 구하기 방식을 구간 곱 구하기 방식으로 바꾸어 적용해본 코드다.
	 * 하지만 이 코드엔 문제가 있다.
	 * 
	 * 만약 arr[index]가 0이 아니어서 tree[node] /= arr[index]를 수행한다 할 때,
	 * 원래 tree[node]의 값이 1_000_000_007보다 커서 이로 나눈 나머지만 저장되어있는 상태라면,
	 * 이 값을 arr[index]로 나누는 것이 올바른 동작이 아니게 된다.
	 * 
	 * 따라서 모듈로 연산 때문에 start == end이면서 arr[index]를 표현하는 노드를 찾아 "내려가면서" 각 노드를 업데이트하는
	 * 방식을 적용하면 안된다.
	 * 먼저 start == end 이면서 arr[index]를 표현하는 노드를 "먼저" 찾은 다음 " 올라오면서" 구간 곱에 모듈로 연산을
	 * 적용한 값으로 노드를 업데이트 해줘야 한다.
	 * 
	 */
	// private static void updateTree(int node, int start, int end, int index, int
	// newVal) {
	// if (index < start || end < index)
	// return;
	// // 현재 노드 업데이트하는 부분
	// if (arr[index] != 0)
	// tree[node] /= arr[index];
	// else
	// tree[node] = 1;
	// tree[node] = tree[node] * newVal % 1_000_000_007;
	// // 현재 노드 업데이트하는 부분 끝
	// if (start != end) {
	// updateTree(node * 2, start, (start + end) / 2, index, newVal);
	// updateTree(node * 2 + 1, (start + end) / 2 + 1, end, index, newVal);
	// }
	// }

	private static void updateTree(int node, int start, int end, int index, int newVal) {
		if (index < start || end < index)
			return;
		// arr[index]를 표현하는 세그먼트 트리 노드
		if (start == end) {
			tree[node] = newVal;
			return;
		}
		updateTree(node * 2, start, (start + end) / 2, index, newVal);
		updateTree(node * 2 + 1, (start + end) / 2 + 1, end, index, newVal);
		tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % 1_000_000_007;
	}

	private static long multiply(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return 1; // 결과에 영향을 미치지 않아야 함.
		if (left <= start && end <= right)
			return tree[node];
		else {
			return (multiply(node * 2, start, (start + end) / 2, left, right)
					* multiply(node * 2 + 1, (start + end) / 2 + 1, end, left, right)) % 1_000_000_007;
		}
	}

	public static void main(String[] args) throws IOException {
		int len = readInt(), count = readInt() + readInt(), a, b, c;
		StringBuilder sb = new StringBuilder();

		arr = new int[len];
		for (int i = 0; i < len; i++)
			arr[i] = readInt();
		tree = new long[1 << ((int) Math.ceil(Math.log(len) / Math.log(2)) + 1)];
		makeTree(1, 0, len - 1);
		for (int i = 0; i < count; i++) {
			a = readInt();
			b = readInt();
			c = readInt();
			if (a == 1) {
				updateTree(1, 0, len - 1, b - 1, c);
				arr[b - 1] = c;
			} else
				sb.append(multiply(1, 0, len - 1, b - 1, c - 1)).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}