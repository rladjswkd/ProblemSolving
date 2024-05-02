
/* 
 * 이동 거리		가능한 경우의 수
 * 1						1
 * 2						1+1
 * 
 * 3						1+1+1
 * 4						1+2+1
 * 
 * 5						1+2+1+1
 * 6						1+2+2+1
 * 
 * 7						1+2+2+1+1
 * 8						1+2+2+2+1
 * 9						1+2+3+2+1
 * 
 * 10						1+2+3+2+1+1
 * 11						1+2+3+2+2+1
 * 12						1+2+3+3+2+1
 * 
 * 13						1+2+3+3+2+2+1
 * 14						1+2+3+3+3+2+1
 * 15						1+2+3+3+3+2+1
 * 16						1+2+3+4+3+2+1
 * 
 * 17						1+2+3+4+3+2+1+1
 * ...
 * 24						1+2+3+4+4+4+3+2+1
 * 25						1+2+3+4+5+4+3+2+1
 * 26						1+2+3+4+5+4+3+2+1+1
 *
 * 경우의 수를 보면 이동 거리가 제곱수(4, 9, 16, ...)일 때 이동 거리의 제곱근을 중심으로 좌우 대칭인 걸 알 수 있다.
 * 또한 제곱수 자신을 포함해 위로 제곱근 값 만큼 이동 횟수가 같고, 제곱수 다음 수부터 제곱근 값 만큼 이동 횟수가 같다.
 * 	이동거리 4를 포함해 위로 4의 제곱근인 2칸만큼이면 이동거리 3, 4에 해당한다.
 * 	이 둘의 이동 횟수는 3으로 같다.
 * 	다음으로 이동거리 5부터 2칸만큼이면 이동거리 5, 6에 해당하며, 이 둘의 이동횟수는 4로 같다.
 * 
 * 위로 제곱근 값 만큼의 이동 거리들은 이동 횟수가 (제곱근 값 * 2 - 1) 만큼이고
 * 아래로 제곱근 값 만큼의 이동거리들은 이도 횟수가 (제곱근 값 * 2)다.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

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
		int t = readInt(), d, sqrtOfS;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			d = -(readInt()) + readInt();
			sqrtOfS = (int) Math.ceil(Math.sqrt(d));
			// d와 같거나 큰 제곱수 s와 d의 차이가 sqrt(s) - 1보다 작거나 같다면, 정답은 sqrt(s) * 2 - 1
			if ((int) Math.pow(sqrtOfS, 2) - d < sqrtOfS)
				sb.append(sqrtOfS * 2 - 1).append('\n');
			// 그렇지 않다면 정답은 s의 이전 제곱수의 제곱근 * 2 이므로
			// (sqrt(s) - 1) * 2
			else
				sb.append((sqrtOfS - 1) * 2).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}