
/*
 * 
 * 길이 1
 * 0 - 0선택
 * 1 - 1선택
 * 
 * 길이 2
 * 10 - 이전 1 선택 + 지금 0 선택
 * 01 - 이전 0 선택 + 지금 1 선택
 * 
 * 길이 3
 * 010 - 이전 1 선택 + 지금 0 선택
 * 011 - 이전 1 선택 + 지금 1 선택
 * 101 - 이전 0 선택 + 지금 1 선택
 * 100 - 이전 0 선택 + 지금 0선택
 * 
 * 길이가 홀수일 때 이 값을 a라고 하면, dp테이블에서 dp[a]의 값은 dp[a - 1]과 같다.
 * -> a - 1은 홀수이고 0이 하나 많든 1이 하나 많든 균형이 맞지 않을텐데, 짝수인 a에선 이 균형을 맞추는 방법 말고는 길이를 늘릴 방법이 없다.
 * 
 * 반대로 길이가 짝수일 때 이 값을 b라고 하면 dp[b]의 값은 dp[b - 1]의 2배와 같다.
 * -> dp[b - 1]이 모든 문자열의 0과 1의 개수를 균형을 맞춰놓은 상태이므로, 여기에 0과 1을 모두 이어붙일 수 있다.
 * 
 * 	1		2		3		4		5		6		7		8		9		10	...
 * 	2		2		4		4		8		8		16	16	32	32
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine()), res = 1;

		for (int i = 1; i <= n; i += 2)
			res = res * 2 % 16769023;
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}