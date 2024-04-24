
/*
 * 
 * dp[i][j]의 값은 (1, 1)부터 (i, j)까지의 합을 나타낸다(0번째 행과 0번째 열은 계산의 편의를 위해 0으로 설정해둔다).
 * 
 * 	1	2	3	4
 * 	2	3	4	5
 * 	3	4	5	6
 * 	4	5	6	7
 * 
 * (2, 3)부터 (4, 4)까지 의 합을 구하는 경우엔 (1, 1)부터 (4, 4)까지의 합에서 (1, 1)부터 (1, 4)까지의 합을 빼고 (1, 1)부터 (4, 2)까지의 합을 뺀 뒤
 * 뺀 두 부분 합의 공통 부분인 (1, 1)부터 (1, 2)까지의 합을 한 번 더해주면 된다.
 * 즉, dp[4][4] - dp[1][4] - dp[4][2] + dp[1][2]와 같이 구할 수 있다.
 * 
 * 즉, 입력이 x1, y1, x2, y2와 같이 들어오므로, 다음과 같이 구할 수 있다.
 * dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]
 * 
 * 예를 하나 더 들어보자.
 * (1, 2)부터 (3, 3)까지의 합은 dp[3][3] - dp[0][3] - dp[3][1] + dp[0][1]이다.
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
		int tableSize = readInt(), testCount = readInt(), x1, y1, x2, y2;
		int[][] table = new int[tableSize][tableSize], dp = new int[tableSize + 1][tableSize + 1];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < tableSize; i++)
			for (int j = 0; j < tableSize; j++)
				table[i][j] = readInt();
		for (int i = 1; i <= tableSize; i++)
			for (int j = 1; j <= tableSize; j++)
				dp[i][j] = table[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
		while (testCount-- > 0) {
			x1 = readInt();
			y1 = readInt();
			x2 = readInt();
			y2 = readInt();
			sb.append(dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}