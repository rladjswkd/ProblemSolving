
/*
 * 차이점 1. words 변수에 입력 문자열들을 그대로 저장하지 않고 문자열을 구성하는 알파벳들의 인덱스를 이용해 비트를 마스킹한 int 값들을 저장
 * 차이점 2. 직전에 선택한 알파벳 비트 마스크인 prev 대신 그 다음 선택할 후보 알파벳 비트 마스크가 시작하는 인덱스인 start 사용
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] words;
	private static int res = 0;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int start, int mask, int remainingCount) {
		int wordCount = 0;

		if (remainingCount == 0) {
			for (int word : words)
				if ((mask & word) == word)
					wordCount++;
			if (res < wordCount)
				res = wordCount;
			return;
		}
		for (int i = start; i < 26; i++)
			if ((mask & 1 << i) == 0)
				solve(i, mask | 1 << i, remainingCount - 1);
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), k = readInt() - 5;
		char[] line;

		words = new int[n];
		for (int i = 0; i < n; i++) {
			line = br.readLine().toCharArray();
			for (char ch : line)
				words[i] |= 1 << ch - 65;
		}
		solve(0, 0 | 1 | (1 << 'n' - 'a') | (1 << 't' - 'a') | (1 << 'i' - 'a') | (1 << 'c' - 'a'), k);
		bw.write(new StringBuilder().append(res).append('\n').toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
