import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static int solve(char[] s, char[] t) {
		int f = 0, r = t.length - 1, swap;

		while (s.length < Math.abs(r - f) + 1) {
			// trailing A 제거
			if (t[r] == 'A') {
				// f와 r로 표현하는 문자열이 뒤집어지지 않은 상태
				if (f < r)
					r--;
				// 뒤집어진 상태
				else
					r++;
			}
			// trailing B 제거하고 문자열 뒤집기
			else if (t[r] == 'B') {
				swap = f;
				// f와 r로 표현하는 문자열이 뒤집어지지 않은 상태
				if (f < r)
					f = r - 1;
				// 뒤집어진 상태
				else
					f = r + 1;
				r = swap;
			}
			// A나 B가 아닌 문자
			else
				return 0;
		}
		if (f < r) {
			for (char ch : s)
				if (ch != t[f++])
					return 0;
		} else {
			for (char ch : s)
				if (ch != t[f--])
					return 0;
		}
		return 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		bw.write(solve(br.readLine().toCharArray(), br.readLine().toCharArray()) + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
}