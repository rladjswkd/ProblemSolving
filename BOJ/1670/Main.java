
/*
 * 원탁에 앉아있다.
 * 
 * 두 숫자를 이을 때, 두 수 사이에 숫자가 0 또는 짝수로 있으면 되는건가
 * 
 * DP
 * 
 */
import java.io.IOException;

public class Main {
	private static int n;
	private static long[] dp;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int nDiv2, personCount;

		n = readInt();
		nDiv2 = n >>> 1;
		// 2명에 대한 인덱스 = 1, 4명에 대한 인덱스 = 2, ..., n명에 대한 인덱스 = n / 2
		dp = new long[nDiv2 + 1];
		// dp[0]은 곱셈에 대한 항등원으로써 1로 설정한다.
		dp[0] = dp[1] = 1;
		for (int i = 2; i < dp.length; i++) {
			// n명 중 0번 인덱스인 사람을 기준으로 악수할 수 있는 모든 사람들과 악수를 하게 하고, 그 둘을 잇는 선분을 기준으로 원탁의 사람들을 두
			// 그룹으로 나눈다.
			// 두 그룹의 사람들에 대해 dp 배열을 활용하기
			// 여떻게 연결하든 남은 인원 수는 직전 dp에서 활용한 인원 수를 넘을 수 없다.
			personCount = i << 1;
			for (int opponent = 1; opponent < personCount; opponent += 2)
				// dp[opponent - 0 - 1 >>> 1]: 0번 인덱스의 사람부터 opponent 사람 그 사이의 사람들이 악수하는 경우의 수
				// dp[personCount - opponent - 1 >>> 1]: opponent부터 0번 인덱스의 사람 그 사이의 사람들이 악수하는
				// 경우의 수
				// dp[i] += dp[opponent - 0 - 1 >>> 1] * dp[personCount - opponent - 1 >>> 1] %
				// 987654321L;
				// 위 라인과 같이 구하면 dp[i]에는 987654321으로 나눈 값이 계속 덧셈으로 쌓인다.
				// dp[i] = (int) ((dp[i] + dp[opponent - 0 - 1 >>> 1] * dp[personCount -
				// opponent - 1 >>> 1]) % 987654321L);
				// 위 라인과 같이 해도 틀린다. 피연산자의 범위 중 가장 큰 것으로 자동 형변환되는 게 안되나보다.
				dp[i] = (dp[i] + dp[opponent - 0 - 1 >>> 1] * dp[personCount - opponent - 1 >>> 1]) % 987654321;
		}
		System.out.println(dp[nDiv2]);
	}
}