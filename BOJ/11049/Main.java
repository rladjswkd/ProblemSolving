
/*
 * dp[i][j]를 i ~ j 행렬의 곱을 구하는 방법 충 최소 연산 횟수를 저장하는데 사용하는 방식
 * 
 * 연산 순서를 생각해보자.
 * 
 * 		0		1		2		3		4		5
 * 0			11	12	13	14	15
 * 1					7		8		9		10
 * 2							4		5		6
 * 3									2		3
 * 4											1
 * 5
 * 
 * 이 중 dp[2][5]를 예로 들면 (234)(5), (2)(345), (23)(45) 중 최솟값을 선택해야 한다.
 * -> 즉, 연산 하나로 끝나는 것이 아니다. 반복문이 필요하다.
 * 
 * dp[1][5]도 살펴보면
 * 9, 6 중 최솟값으로 설정한 후, 8+1과 기존 값을 비교해 최솟값으로 업데이트하고,
 * 7+3과 이 값을 비교해 최솟값으로 업데이트해야 한다.
 * 
 * dp[1][4] 등 처음과 끝이 0과 n-1이 아닌 경우에도 모두 동일한 연산이 필요하다.
 */
import java.io.IOException;

public class Main {
	private static int n;
	private static int[][] matrices, dp;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int lowest;
		// ii는 matrices[i], jj는 matrices[j], ju는 matrices[j - 1]을 나타낸다.
		int[] cur, ii, jj;

		n = readInt();
		matrices = new int[n][];
		for (int i = 0; i < n; i++)
			matrices[i] = new int[] { readInt(), readInt() };
		// 마지막 행은 쓰이지 않으므로 제거
		// 첫 번째 열도 쓰이지 않지만, matrices와 인덱스를 맞추는 역할을 한다.
		// 첫 번째 열에 불필요한 메모리를 낭비하고 싶지 않다면 new int[n - 1][n - 1]로 선언 후,
		// matrices에 dp의 인덱스인 i, j로 접근할 때 적잘한 덧셈 연산으로 인덱스를 맞춰주면 된다.
		dp = new int[n - 1][n];
		for (int i = n - 2; i >= 0; i--) {
			// dp[i][i + 1]일 때.
			ii = matrices[i];
			(cur = dp[i])[i + 1] = ii[0] * ii[1] * matrices[i + 1][1];
			for (int j = i + 2; j < n; j++) {
				jj = matrices[j];
				// (i)(i+1...j)와 (i...j-1)(j) 중 작은 값으로 lowest를 초기화
				lowest = Math.min(cur[j - 1] + ii[0] * matrices[j - 1][1] * jj[1], dp[i + 1][j] + ii[0] * ii[1] * jj[1]);
				// b는 boundary를 나타낸다.
				for (int b = i + 1; b < j - 1; b++)
					lowest = Math.min(lowest, cur[b] + dp[b + 1][j] + ii[0] * matrices[b][1] * jj[1]);
				dp[i][j] = lowest;
			}
		}
		System.out.println(dp[0][n - 1]);
	}
}