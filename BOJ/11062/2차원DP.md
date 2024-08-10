```java
import java.io.IOException;

public class Main {
	private static int n;
	private static int[] cards;
	private static int[][] dp;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	// turn이 짝수면 근우, 홀수면 명우 차례
	// acc: cards[start]부터 cards[end]까지의 합
	private static int calculateHighestScore(int turn, int start, int end, int acc) {
		int left, right;

		// 근우 턴
		if (turn % 2 == 0) {
			// 남아있는 카드가 한 장일 때
			if (start == end)
				return dp[start][end] = cards[start];
			// 남아있는 카드 배열에 대해 이미 연산을 했을 때
			if (dp[start][end] != 0)
				// 둘 중 큰 값을 반환
				return dp[start][end];
			// 남아있는 카드 배열에 대해 아직 연산을 하지 않았을 때
			else
				// 왼쪽 카드를 선택한 경우와 오른쪽 카드를 선택한 경우 중 최대값을 선택
				return dp[start][end] = Math.max(
						cards[start] + calculateHighestScore(turn + 1, start + 1, end, acc - cards[start]),
						cards[end] + calculateHighestScore(turn + 1, start, end - 1, acc - cards[end]));
		}
		// 명우 턴
		else {
			// 남아있는 카드가 한 장일 때
			if (start == end) {
				dp[start][end] = cards[start];
				// 명우가 선택한 카드의 점수는 근우에게 더해지지 않아야 하므로 0을 반환.
				return 0;
			}
			// 남아있는 카드 배열에 대해 이미 연산을 했을 때
			if (dp[start][end] != 0)
				// 명우에게 우선권이 있으므로 근우의 점수로는 둘 중 작은 값을 반환
				return acc - dp[start][end];
			// 남아있는 카드 배열에 대해 아직 연산을 하지 않았을 때
			else {
				// 명우가 cards[start]를 골랐을 때, 나머지 카드에 대해 근우가 선택한 최대값
				left = calculateHighestScore(turn + 1, start + 1, end, acc - cards[start]);
				// 명우가 cards[end]를 골랐을 때, 나머지 카드에 대해 근우가 선택한 최대값
				right = calculateHighestScore(turn + 1, start, end - 1, acc - cards[end]);
				// 왼쪽 카드를 선택한 경우와 오른쪽 카드를 선택한 경우 중 최대값을 선택
				dp[start][end] = Math.max(cards[start] + (acc - cards[start] - left), cards[end] + (acc - cards[end] - right));
				// 명우에게 우선권이 있으므로 근우의 점수로는 둘 중 작은 값을 반환
				return acc - dp[start][end];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), acc;
		StringBuilder sb = new StringBuilder();

		cards = new int[1000];
		// i번째 카드에 대해 첫 번째 카드부터의 누적합을 저장할 배열
		while (t-- > 0) {
			acc = 0;
			n = readInt();
			dp = new int[n][n];
			for (int i = 0; i < n; i++)
				acc += (cards[i] = readInt());
			sb.append(calculateHighestScore(0, 0, n - 1, acc)).append('\n');
		}
		System.out.print(sb.toString());
	}
}
```
