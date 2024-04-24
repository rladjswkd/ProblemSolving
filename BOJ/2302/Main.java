
/*
 * 
 * 좌석이 1개
 * 1
 * 
 * 좌석이 2개
 * 12, 21
 * 
 * 좌석이 3개
 * 123, 213, 132
 * 
 * 좌석이 4개
 * 1234, 2134, 1324, 1243, 2143
 * 
 * ... 좌석이 n개일 때 = 좌석이 n - 1개일 때의 모든 경우들 뒤에 n을 붙인 경우 + 좌석이 n - 2개일 때의 모든 경우들 뒤에 n-1, n-2 순서로 붙인 경우
 * -> 좌석이 4개일 때를 예로 들면, 좌석이 3개일 때(123, 213, 132) + 4 -> 1234, 2134, 1324 와 좌석이 2개일 때(12, 21) + 43 -> 1243, 2143
 * 
 * 일반화하면, n 번째 사람이 n - 1 좌석에 앉을지 n 좌석에 앉을지를 모두 고려하는 것이다.
 * 
 * 고정석을 기준으로 좌석들을 그룹화한 다음, 각 그룹의 수를 인덱스로 하는 피보나치 수열 값을 모두 곱해주면 된다.
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
		int seatCount = readInt(), fixedCount = readInt(), fixedSeat, leftSeat = 1, res = 1;
		int[] dp = new int[41];

		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= seatCount; i++)
			dp[i] = dp[i - 1] + dp[i - 2];
		while (fixedCount-- > 0) {
			fixedSeat = readInt();
			res *= dp[fixedSeat - leftSeat];
			leftSeat = fixedSeat + 1;
		}
		fixedSeat = seatCount + 1;
		res *= dp[fixedSeat - leftSeat];
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}