import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/*
 * 현재 W를 고르기 위해선 이전에 W가 n개 이하로 선택됐어야 한다.
 * 현재 H를 고르기 위해선 이전에 W가 H보다 많이 선택됐어야 한다.
 * 
 * 맨 처음은 무조건 W만 가능하고, 마지막은 H만 가능하다.
 * 2차원 배열로 풀 수 있고, dp[i][j]는 i개의 W와 j개의 H로 구성할 수 있는 문자열의 개수다.
 * 예를 들어 
 * dp[3][0]: WWW 하나만 가능하므로 값이 1이다.
 * dp[3][1]: WWWH, WWHW, WHWW가 가능하므로 값이 3이다.
 * 이 때, dp[3][1] = dp[3][0] + dp[2][1]이므로, 이 둘을 보자.
 * 
 * dp[3][0]: WWW
 * dp[2][1]: WWH, WHW
 * 
 * 즉, dp[3][1]은 dp[3][0]의 모든 경우의 뒤에 H를 붙인 것과 dp[2][1]의 모든 경우의 뒤에 W를 붙인 것으로 구성된다.
 * 
 * dp[3][2]도 보자.
 * dp[3][1]: WWWH, WWHW, WHWW
 * dp[2][2]: WWHH, WHWH
 * 
 * 따라서 dp[3][2]는 dp[3][1]의 모든 경우의 뒤에 H를 붙인 WWWHH, WWHWH, WHWWH와 dp[2][2]의 모든 경우의 뒤에 W를 붙인 WWHHW, WHWHW로 5개가 가능하다.
 * 
 * 마지막으로 dp[3][3]은 dp[3][2] + dp[2][3]인데, dp[2][3]은 불가능하므로 값이 0이다. 따라서 dp[3][2]의 모든 경우의 뒤에 H를 붙인 5개가 가능하다.
 * 
 * W/H	0		1		2		3		4		5		6		7		8		9
 * 0		0		0		0		0		0		0		0		0		0		0
 * 1		1		1		0		0		0		0		0		0		0		0
 * 2		1		2		2		0		0		0		0		0		0		0
 * 3		1		3		5		5		0		0		0		0		0		0
 * 4		1		4		9		14	14	0		0		0		0		0
 * 5		1		5		14	28	42	42	0		0		0		0
 * 6		1		6		20	48	90	132	132	0		0		0
 * 7		1
 * 8		1
 * 9		1
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n;
		long[][] dp = new long[31][31];
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < 31; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= i; j++)
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
		}
		while ((n = Integer.parseInt(br.readLine())) != 0)
			sb.append(dp[n][n]).append('\n');
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}