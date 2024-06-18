
/*
 * 1: 1
 * 2: 12
 * 3: 121
 * 4: 1213
 * 5: 12131
 * 6: 121312
 * 7: 1213121
 * 8: 12131213(X) 12131231
 * 9: 121312312
 * 10: 12131231213
 * ...
 * 13: 1213123121312
 * 14: 12131231213121
 * 15: 1213123121312
 * 
 * 규칙이 있나...?
 * 
 * 1: 1, 2, 3
 * 2: 12, 13, 21, 23, 31, 32
 * 3: 121, 131, 231, 321, 132, 232, 232, 312, 123, 213, 313, 323
 * 
 * ???
 * 
 * 백트래킹?
 * -> 매번 현재 수열이 좋은 수열인지 판단하기 위해 연산하는 것은 과하다
 * 
 * 모두를 확인할 필요는 없다!!
 * 
 * 예를 들어 N = 4일 때의 답이 1213이다. 그럼 N = 5일 때의 답은 어떻게 구할까?
 * 1213 + {1, 2, 3 중 하나}일 것이다.
 * 이때, 1213은 이미 "좋은 수열"이므로 새로 추가될 숫자를 포함하는 부분 수열이 좋은 수열이기만 하면 된다.
 * 
 * 즉, 더 작은 값을 구성할 1부터 2, 3 순서대로 값을 뒤에 이어붙였을 때 좋은 수열인지 확인하고, 그렇다면 넘긴다.
 * 
 * 부분 수열의 길이가 홀수 -> 무조건 좋은 수열인가? 현재 추가한 수가 그 앞의 수와 같지만 않다면 무조건 좋은 수열이다.
 * 따라서, 좋은 수열인지 확인할 땐 짝수 길이(2, 4, 6, ...)로 확인한다.
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] seq;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static boolean check(int newOne, int seqLength) {
		int subLength = 2, p1, p2;

		seq[seqLength - 1] = newOne;
		while (subLength <= seqLength) {
			p1 = seqLength - subLength;
			p2 = seqLength - (subLength >>> 1);
			while (p2 < seqLength && seq[p1] == seq[p2]) {
				p1++;
				p2++;
			}
			if (p2 == seqLength)
				return false;
			subLength += 2;
		}
		return true;
	}

	private static boolean solve(int seqLength) {
		if (seq.length == seqLength)
			return true;
		if ((check(1, seqLength + 1) && solve(seqLength + 1)) ||
				(check(2, seqLength + 1) && solve(seqLength + 1)) ||
				(check(3, seqLength + 1) && solve(seqLength + 1)))
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		seq = new int[readInt()];
		seq[0] = 1;
		solve(1);
		for (int i : seq)
			sb.append(i);
		bw.write(sb.append('\n').toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
