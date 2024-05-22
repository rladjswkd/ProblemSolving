import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[][] items = new int[10000][];

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static StringBuilder solve(int a, int b) {
		Deque<Integer> q = new ArrayDeque<>();
		int value, newValue;
		Deque<Character> cmdStack = new ArrayDeque<>();
		StringBuilder res = new StringBuilder();

		// items[i]는 int[2] 타입이며, 0번 인덱스는 prev, 1번 인덱스는 적용한 명령어를 나타낸다.
		// prev의 역할은 두 가지 - 1. 방문 여부 체크(-1이면 현재 값을 방문하지 않음) 2. 현재 값 이전의 값을 추적(출력을 위함)
		for (int i = 0; i < 10000; i++)
			items[i] = new int[] { -1, 0 };
		q.addLast(a);
		items[a][0] = a;
		while (!q.isEmpty() && items[b][0] == -1) {
			value = q.removeFirst();
			newValue = value * 2 % 10000;
			if (items[newValue][0] == -1) {
				items[newValue][0] = value;
				items[newValue][1] = 'D';
				q.addLast(newValue);
			}
			newValue = (value + 10000 - 1) % 10000;
			if (items[newValue][0] == -1) {
				items[newValue][0] = value;
				items[newValue][1] = 'S';
				q.addLast(newValue);
			}
			newValue = (value % 1000) * 10 + (value / 1000);
			if (items[newValue][0] == -1) {
				items[newValue][0] = value;
				items[newValue][1] = 'L';
				q.addLast(newValue);
			}
			newValue = (value % 10) * 1000 + (value / 10);
			if (items[newValue][0] == -1) {
				items[newValue][0] = value;
				items[newValue][1] = 'R';
				q.addLast(newValue);
			}
		}
		value = b;
		while (value != a) {
			cmdStack.addLast((char) items[value][1]);
			value = items[value][0];
		}
		while (!cmdStack.isEmpty())
			res.append(cmdStack.removeLast());
		return res.append('\n');
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), a, b;
		StringBuilder res = new StringBuilder();
		while (t-- > 0) {
			a = readInt();
			b = readInt();
			res.append(solve(a, b));
		}
		bw.write(res.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}