
/*
 * DP
 * 
 * 현재 라인의 현재 칸(세 칸 중 하나)을 선택할 때의 가능한 최댓값과 최솟값
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
		int n = readInt(), val;
		int[][] dp = new int[2][3], prev = new int[2][3];

		// prev만 채워두면 n이 1로 주어질 때, 반복문이 실행되지 않고 모든 원소가 0으로 채워진 dp를 활용해 결과를 출력하게 된다.
		dp[0][0] = dp[1][0] = prev[0][0] = prev[1][0] = readInt();
		dp[0][1] = dp[1][1] = prev[0][1] = prev[1][1] = readInt();
		dp[0][2] = dp[1][2] = prev[0][2] = prev[1][2] = readInt();
		for (int i = 1; i < n; i++) {
			val = readInt();
			dp[0][0] = val + Math.max(prev[0][0], prev[0][1]);
			dp[1][0] = val + Math.min(prev[1][0], prev[1][1]);
			val = readInt();
			dp[0][1] = val + Math.max(Math.max(prev[0][0], prev[0][1]), prev[0][2]);
			dp[1][1] = val + Math.min(Math.min(prev[1][0], prev[1][1]), prev[1][2]);
			val = readInt();
			dp[0][2] = val + Math.max(prev[0][1], prev[0][2]);
			dp[1][2] = val + Math.min(prev[1][1], prev[1][2]);
			prev[0][0] = dp[0][0];
			prev[0][1] = dp[0][1];
			prev[0][2] = dp[0][2];
			prev[1][0] = dp[1][0];
			prev[1][1] = dp[1][1];
			prev[1][2] = dp[1][2];
		}
		bw.write(new StringBuilder()
				.append(Math.max(Math.max(dp[0][0], dp[0][1]), dp[0][2])).append(' ')
				.append(Math.min(Math.min(dp[1][0], dp[1][1]), dp[1][2])).append('\n')
				.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}