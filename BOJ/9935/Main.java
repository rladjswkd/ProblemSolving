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

	private static String makeString(Deque<Character> stack) {
		StringBuilder sb = new StringBuilder(stack.size());

		if (stack.isEmpty())
			return "FRULA\n";
		while (!stack.isEmpty())
			sb.append(stack.removeLast());
		return sb.append('\n').toString();
	}

	public static void main(String[] args) throws IOException {
		Deque<Character> l = new ArrayDeque<>(), r = new ArrayDeque<>();
		char[] input = br.readLine().toCharArray(), explosion = br.readLine().toCharArray();
		int count;

		for (char ch : input)
			l.addLast(ch);
		while (!l.isEmpty()) {
			r.addLast(l.removeLast());
			if (r.size() >= explosion.length && r.getLast() == explosion[0]) {
				count = 0;
				// r에 담긴 문자들을 순서대로 하나씩 explosion과 비교해, 같으면 l로 다시 넘기고 count 1 증가
				while (count < explosion.length && explosion[count] == r.getLast()) {
					count++;
					l.addLast(r.removeLast());
				}
				// count가 explosion.length와 값이 같다면, 즉 폭발 문자열을 찾았다면 l로 옮긴 count 만큼의 문자를 제거
				if (count == explosion.length)
					while (count-- > 0)
						l.removeLast();
				// 폭발 문자열과 하나라도 다르다면 r을 원상복구
				else
					while (count-- > 0)
						r.addLast(l.removeLast());
			}
		}
		bw.write(makeString(r));
		bw.flush();
		br.close();
		bw.close();
	}
}