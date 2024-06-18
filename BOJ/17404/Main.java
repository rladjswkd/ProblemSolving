
/*
 * 1번 집에서 선택할 색을 고정하고, dp 연산 과정에서 무조건 1번 집은 고정한 색을 선택하도록 유도
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	// 고정할 첫 번째 집의 색을 제외한 첫 번째 집을 나머지 색으로 칠할 때 필요한 비용을 아래 값으로 초기화.
	// 고정한 색을 선택할 수 있는 경우라면 무조건 고정한 색을 선택하게 된다.
	private static final int INFINITY = 1001;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		// res는 집을 칠하는 최대 비용 + 1
		int n = readInt(), res = 1000 * 1000 + 1;
		int[][] costs = new int[n][];
		int[] dp = new int[3], prev = new int[3];

		for (int i = 0; i < n; i++)
			costs[i] = new int[] { readInt(), readInt(), readInt() };
		for (int firstHouseColor = 0; firstHouseColor < 3; firstHouseColor++) {
			// 현재 firstHouseColor만 주어진 비용을 유지하고 나머지 두 색은 비용을 INFINITY로 설정
			prev[firstHouseColor] = costs[0][firstHouseColor];
			prev[(firstHouseColor + 1) % 3] = prev[(firstHouseColor + 2) % 3] = INFINITY;
			// 두 번째 집부터 마지막 집까지 주어진 조건에 맞게 최소 비용으로 색을 선택
			for (int i = 1; i < n; i++) {
				dp[0] = Math.min(prev[1], prev[2]) + costs[i][0];
				dp[1] = Math.min(prev[0], prev[2]) + costs[i][1];
				dp[2] = Math.min(prev[0], prev[1]) + costs[i][2];
				prev[0] = dp[0];
				prev[1] = dp[1];
				prev[2] = dp[2];
			}
			// 첫 번째 집에서 선택한 색을 제외한 나머지 두 색만을 고려해서 결과를 업데이트
			if (dp[(firstHouseColor + 1) % 3] < res || dp[(firstHouseColor + 2) % 3] < res)
				res = Math.min(dp[(firstHouseColor + 1) % 3], dp[(firstHouseColor + 2) % 3]);
		}
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}