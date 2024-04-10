import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

// 세그먼트 트리 설명: https://www.acmicpc.net/blog/view/9#comments
public class Main {
	private static long[] arr;
	private static long[] tree;

	private static void fillTree(int node, int start, int end) {
		if (start == end)
			tree[node] = arr[start];
		else {
			fillTree(2 * node, start, (start + end) / 2);
			fillTree(2 * node + 1, (start + end) / 2 + 1, end);
			tree[node] = tree[2 * node] + tree[2 * node + 1];
		}
	}

	private static void updateTree(int node, int start, int end, int targetIdx, long diff) {
		if (targetIdx < start || end < targetIdx)
			return;
		tree[node] += diff;
		if (start != end) {
			updateTree(node * 2, start, (start + end) / 2, targetIdx, diff);
			updateTree(node * 2 + 1, (start + end) / 2 + 1, end, targetIdx, diff);
		}
	}

	private static long calculateRangeSum(int node, int start, int end, int left, int right) {
		// [start, end]가 [left, right] 와 하나도 겹치지 않는 경우 : 0 반환
		if (end < left || right < start)
			return 0;
		// [start, end]가 [left, right]에 포함되는 경우 : 자기 자신의 트리 노드 값 반환
		if (left <= start && end <= right)
			return tree[node];
		// [start, end]가 [left, right]를 포함하는 경우 : 구간을 다시 반으로 나눠 탐색
		// [start, end]가 [left, right]와 일부 겹치는 경우 : 구간을 다시 반으로 나눠 탐색
		return calculateRangeSum(node * 2, start, (start + end) / 2, left, right)
				+ calculateRangeSum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
	}

	private static void processOp1(int b, long c) {
		updateTree(1, 0, arr.length - 1, b, c - arr[b]);
		// arr[b] 값과 c 값의 차이를 이용해 tree를 업데이트하므로, 이후 다시 arr[b]의 값을 업데이트하는 연산을 수행할 때, 기존의
		// 연산이 적용되어있어야 하므로, 무조건 수행해줘야한다.
		arr[b] = c;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split("\\s");
		int op = Integer.parseInt(input[1]) + Integer.parseInt(input[2]);
		StringBuilder sb = new StringBuilder();

		arr = new long[Integer.parseInt(input[0])];
		for (int i = 0; i < arr.length; i++)
			arr[i] = Long.parseLong(br.readLine());
		// arr의 원소의 개수로 완전 이진 트리를 만들 때 그 트리의 높이가 tree의 크기가 된다.
		tree = new long[1 << ((int) Math.ceil(Math.log(arr.length) / Math.log(2))) + 1];
		fillTree(1, 0, arr.length - 1);
		for (int i = 0; i < op; i++) {
			input = br.readLine().split("\\s");
			if (input[0].charAt(0) == '1')
				processOp1(Integer.parseInt(input[1]) - 1, Long.parseLong(input[2]));
			else if (input[0].charAt(0) == '2')
				sb.append(
						calculateRangeSum(1, 0, arr.length - 1, Integer.parseInt(input[1]) - 1, Integer.parseInt(input[2]) - 1))
						.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}