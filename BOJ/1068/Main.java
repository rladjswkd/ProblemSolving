/*
 * dfs
 * 
 * 삭제할 노드를 dfs로 탐색하고 삭제로 표현.
 * 그 후 dfs로 모든 리프노드를 탐색하고 개수 세기.
 * 
 * 참고) binary tree라는 말이 없다. 따라서 하위 노드의 수는 정해지지 않는다.
 * 
 * Node 타입을 정의해 구현할 때, 입력에 주의해야 할 것 같다.
 * 만약 상위 노드의 번호를 입력받을 때, Node[] tree의 모든 Node를 사전에 인스턴스화 해놓지 않는다면
 * 아직 입력받지 않은 번호의 노드를 상위 노드로 갖는 하위 노드가 주어졌을 때 NullPointerException이 발생할 수 있다.
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	static class Node {
		List<Integer> lowers;

		Node() {
			lowers = new ArrayList<>();
		}

		void addLower(int lowerVal) {
			lowers.add(lowerVal);
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static Node[] tree;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = br.read();

		if (sign != '-')
			n = sign - 48;
		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == '-')
			n = -n;
		return n;
	}

	private static boolean removeNode(Node node, int target) {
		boolean ret = false;

		if (node.lowers.remove(Integer.valueOf(target)))
			return true;
		for (int lower : node.lowers)
			if (removeNode(tree[lower], target)) {
				ret = true;
				break;
			}
		return ret;
	}

	private static int countLeafNodes(Node node) {
		int acc = 0;

		if (node.lowers.size() == 0)
			return 1;
		for (int lower : node.lowers)
			acc += countLeafNodes(tree[lower]);
		return acc;
	}

	public static void main(String[] args) throws IOException {
		int upper;
		Node root = new Node();

		tree = new Node[readInt()];
		// 여기서 tree의 모든 Node를 인스턴스화 해놓지 않고 85번 줄의 반복문에서 순회하며 i번째 Node를 초기화하면
		// 85번 줄의 반복문에서 입력을 받을 때 i 번째 노드가 i 이후의 노드 P의 하위 노드일 때, P가 인스턴스화 되지 않은 상황이므로
		// NullPointerException 발생 가능
		for (int i = 0; i < tree.length; i++)
			tree[i] = new Node();
		for (int i = 0; i < tree.length; i++) {
			upper = readInt();
			if (upper == -1)
				root.addLower(i);
			else
				tree[upper].addLower(i);
		}
		removeNode(root, readInt());
		if (root.lowers.isEmpty())
			bw.write("0\n");
		else
			bw.append(String.valueOf(countLeafNodes(root))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}