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

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int towerCount = readInt(), currentTower;
		Deque<int[]> stack = new ArrayDeque<>(towerCount);
		StringBuilder sb = new StringBuilder();

		sb.append('0');
		stack.addLast(new int[] { readInt(), 1 });
		for (int i = 1; i < towerCount; i++) {
			currentTower = readInt();
			// 모든 탑은 높이가 서로 다르다. 그럼에도 불구하고 만약 같은 게 있다면 그냥 제거하는 로직으로 구현했다.
			while (!stack.isEmpty() && stack.peekLast()[0] <= currentTower)
				stack.removeLast();
			if (!stack.isEmpty())
				sb.append(' ').append(stack.peekLast()[1]);
			else
				sb.append(' ').append(0);
			stack.addLast(new int[] { currentTower, i + 1 });
		}
		bw.write(sb.append('\n').toString());
		bw.flush();
		br.close();
		bw.close();
	}
}