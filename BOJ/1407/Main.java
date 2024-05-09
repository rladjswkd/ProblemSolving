
/*
 * 1. 주어진 범위에서 홀수 개수 카운팅
 * 2. 주어진 짝수를 2로 나누면 절반은 홀수 절반은 짝수이므로 여기서 다시 홀수 개수 카운팅
 * 3. 반복
 * 
 * 절반씩 날라가니까 범위가 1 ~ 10^15로 주어진다 해도 시간 복잡도는 O(log_2(10^15) = 49...
 * 
 * 예)
 * 5 9
 * 
 * 5 6 7 8 9 -> 2^0 * 3
 * 3 4 -> 2^1 * 1
 * 2 -> 2^2 * 0
 * 1 -> 2^3 * 1
 * => 8 + 0 + 2 + 3 = 13
 * 
 * 홀수 개수(1, 2번은 숫자 개수가 홀수, 3, 4번은 숫자 개수가 짝수다)
 * 1. 시작, 끝이 모두 홀수
 * 	5, 9 -> 숫자 5개 홀수 3개 -> 숫자 개수 / 2 + 1
 * 2. 시작, 끝이 모두 짝수
 * 	4, 8 -> 숫자 5개 홀수 2개 -> 숫자 개수 / 2
 * 3. 시작은 짝수, 끝은 홀수
 * 	4, 9 -> 숫자 6개 홀수 3개 -> 숫자 개수 / 2
 * 4. 시작은 홀수, 끝은 짝수
 * 	5, 8 -> 숫자 4개 홀수 2개 -> 숫자 개수 / 2
 * 
 * 만약 a = b = 홀수라 하면, 1.에 따라 홀수 개수가 1개이므로 유효하다.
 * 만약 a = b = 짝수라 하면, 2.에 따라 홀수 개수가 0개이므로 유효하다.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static long readLong() throws IOException {
		long n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		long a = readLong(), b = readLong(), res = 0, pow = 0;

		while (a <= b) {
			if (a % 2 == 1 && b % 2 == 1)
				res += (1L << pow) * ((b - a + 1) / 2 + 1);
			else
				res += (1L << pow) * ((b - a + 1) / 2);
			pow++;
			if (a % 2 == 1)
				a++;
			if (b % 2 == 1)
				b--;
			a /= 2;
			b /= 2;
		}
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}