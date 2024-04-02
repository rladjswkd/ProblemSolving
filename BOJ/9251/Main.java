import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/*
 * 		C	A	P	C	A	K	(s2)
 * A	0	1	0	0	1	0
 * C	1	1	1	2	2	2
 * A	0	2	2	2	3	3
 * Y	0	2	2	2	3	3
 * K	0 2	2	2	3	4
 * P	0	2	3	3	3	4
 * (s1)
 * 
 * 위와 같이 두 문자열을 이용해 이차원 배열을 생성한 후, 0번째 행과 0번째 열은 일치하면 1, 그렇지 않으면 0으로 미리 초기화한다.
 * 이후의 점화식은 아래와 같다.
 * dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + (s2.charAt(i) == s2.charAt(j) ? 1 : 0)
 */
/*
 * 땡! 위 방식처럼 하면 AAAAA, AAAAA가 입력으로 들어왔을 때 8이 반환된다.
 * 		A	A	A	A	A
 * A	1	1	1	1	1
 * A	1	2	3	4	5
 * A	1	3	4	5	6
 * A	1	4	5	6	7
 * A	1	5	6	7	8
 */
/*
 * 1. 애초에 배열을 (s1.length() + 1) * (s2.length() + 1) 사이즈로 생성한다. -> 두 문자열 중 하나라도 한 글자만 들어오면 같은 글자가 있으면 1, 아니면 0으로 초기화하고 끝나므로 올바르게 동작하지 않는다.
 * 		0행과 0열은 모두 0으로 초기화된 상태에서 1행과 1열부터 탐색한다.
 * 2. i, j 번째 문자가 같으면 i - 1, j - 1 번째 dp 값에 1을 더한 값으로 dp[i][j]를 설정하고, 문자가 다르면 dp[i - 1][j], dp[i][j - 1] 중 큰 값으로 설정한다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s1 = br.readLine(), s2 = br.readLine();
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) != s2.charAt(j - 1))
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				else
					dp[i][j] = dp[i - 1][j - 1] + 1;
			}
		}
		br.close();
		bw.write(dp[s1.length()][s2.length()] + "\n");
		bw.flush();
		bw.close();
	}
}