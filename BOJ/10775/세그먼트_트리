
/*
 * i번째 비행기를 "1번부터 g_i번 게이트 중 하나에 도킹"
 * 한 비행기가 공항 내 어느 게이트에도 도킹할 수 없다면 그 시점부터 공항은 폐쇄
 * 
 * 주어진 g_i에 따라 가능한 뒤에 도킹하는 것이 좋지 않을까?
 * 근데 가능한 뒤에 도킹하는 것을 순차 탐색으로 찾으면 시간초과. 10^5 * 10^5
 * 예를 들어 입력이 아래와 같다면 시간 초과!
 * 10000
 * 10000
 * 10000
 * 10000
 * 10000
 * ...
 * 10000
 * 
 * -> 10000 * (10000 * 10001 / 2)
 * 
 * boolean 배열을 사용해 비행기를 도킹한 게이트는 true, 아닌 게이트는 false로 두고 가장 뒤의 false를 이분 탐색으로 찾기?
 * -> 예제 1번만 해도 1번 비행기를 4번 게이트, 2번 게이트를 1번 게이트에 도킹하면 배열이 true, false, false, true가 되는데 이는 정렬된 형태가 아니다..
 * 따라서 이분 탐색을 사용할 수 없다.
 * 
 * 어쨌든 탐색 시간을 O(n)보다 줄여야 하는데 방법을 생각해보자.
 * 세그먼트 트리? -> 각 범위 내에서 선택할 수 있는 가장 뒤 게이트 저장?
 * 세그먼트 트리에서는 true, false, false, true 와 같은 정렬되지 않은 상황에서 문제가 없을까?
 * 
 * 오 가능할 듯.
 * 도킹된 게이트를 나타내는 노드는 -1로 값을 설정하고, 그렇지 않은 노드는 자기 자신의 인덱스를 가리키게 한 후, 범위 내 검색시 무조건 큰 값을 반환하게 하면 될듯
 * 
 */
import java.io.IOException;

public class Main {
	private static int g, p;
	private static int[] tree;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	// start, end는 인덱스 값이므로 실제 게이트 번호로 취급하려면 1을 더해줘야 한다.
	private static void makeTree(int node, int start, int end) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = lChild + 1;

		if (start == end)
			tree[node] = start;
		else {
			makeTree(lChild, start, mid);
			makeTree(rChild, mid + 1, end);
			tree[node] = Math.max(tree[lChild], tree[rChild]);
		}
	}

	// 1 ~ g_i번 게이트 범위 내에서 선택할 수 있는 게이트 중 번호가 가장 큰 것을 반환하는 메서드
	private static int findLastAvailableGate(int node, int start, int end, int left, int right) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = lChild + 1;

		// 범위를 벗어났을 땐 반환값이 선택되면 안되므로 -1을 반환한다.
		if (right < start || end < left)
			return -1;
		if (left <= start && end <= right)
			return tree[node];
		else
			return Math.max(findLastAvailableGate(lChild, start, mid, left, right),
					findLastAvailableGate(rChild, mid + 1, end, left, right));
	}

	private static void setGateOccupied(int node, int start, int end, int target) {
		int mid = (start + end) >>> 1, lChild = node << 1, rChild = lChild + 1;

		if (target < start || end < target)
			return;
		if (start == end)
			tree[node] = -1;
		else {
			setGateOccupied(lChild, start, mid, target);
			setGateOccupied(rChild, mid + 1, end, target);
			tree[node] = Math.max(tree[lChild], tree[rChild]);
		}
	}

	public static void main(String[] args) throws IOException {
		int index, gate, res = 0;

		g = readInt();
		p = readInt();
		tree = new int[1 << (int) Math.ceil(Math.log(g) / Math.log(2)) + 1];
		makeTree(1, 0, g - 1);

		for (index = 0; index < p; index++) {
			gate = findLastAvailableGate(1, 0, g - 1, 0, readInt() - 1);
			if (gate >= 0) {
				res++;
				setGateOccupied(1, 0, g - 1, gate);
			} else {
				index++;
				break;
			}
		}
		// 남은 입력 처리
		for (; index < p; index++)
			readInt();
		System.out.println(res);
	}
}