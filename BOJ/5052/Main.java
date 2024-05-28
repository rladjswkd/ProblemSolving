
/*
 * root가 있고, 그 아래로 첫 번째 자리의 번호를 저장하고, 그 아래로는 두 번째 자리의 번호를 저장하는 방식을 반복하다 마지막 자리의 번호를 저장하고는 null 등 번호의 끝을 알리는 값을 저장하는 자료 구조를 사용하면 될듯
 * 번호는 총 0 ~ 9까지 가능
 * 
 * 이러한 상황에 사용할 자료구조가 Trie다.
 * 
 * 사전 순으로 정렬하여 사용하자.
 * 
 * 참고로, 전화번호를 입력받아 사전순으로 정렬한 후(같은 부분 문자열을 포함한다면 길이가 짧은 게 먼저 오게 하기 위함) HashSet을 활용하는 방법은
 * 전화번호의 일부분이 HashSet에 포함되어있는지 확인하기 위해 계속해서 부분 문자열을 생성해야 하므로 비효율적이다.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static class Node {
		Node[] nextNumbers;
		// value가 null이면 root이후로 이 Node까지의 구성은 입력받은 전화번호 중 하나가 아니다(즉, 몇몇 전화번호의 일부분일 뿐)
		char[] value;

		Node() {
			nextNumbers = new Node[10];
			value = null;
		}
	}

	static class Trie {
		Node root;

		Trie() {
			root = new Node();
		}

		boolean checkConsistency(List<String> phoneNumbers) {
			char[] phoneNumber;

			for (String str : phoneNumbers) {
				phoneNumber = str.toCharArray();
				if (!isConsistent(phoneNumber))
					return false;
				add(phoneNumber);
			}
			return true;
		}

		void add(char[] phoneNumber) {
			Node current = root;

			for (int i = 0; i < phoneNumber.length; i++) {
				if (current.nextNumbers[phoneNumber[i] - 48] == null)
					current.nextNumbers[phoneNumber[i] - 48] = new Node();
				current = current.nextNumbers[phoneNumber[i] - 48];
			}
			// 이 문제에선 이후 phoneNumber의 값이 바뀔 일이 없으니 얖은 복사로 충분
			current.value = phoneNumber;
		}

		private boolean isConsistent(char[] phoneNumber) {
			Node current = root;

			for (int i = 0; i < phoneNumber.length && current.nextNumbers[phoneNumber[i] - 48] != null; i++) {
				current = current.nextNumbers[phoneNumber[i] - 48];
				if (current.value != null)
					return false;
			}
			return true;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int t = Integer.parseInt(br.readLine()), n;
		List<String> phoneNumbers;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			phoneNumbers = new ArrayList<>();
			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++)
				phoneNumbers.add(br.readLine());
			Collections.sort(phoneNumbers);
			if (new Trie().checkConsistency(phoneNumbers))
				sb.append("YES\n");
			else
				sb.append("NO\n");
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}