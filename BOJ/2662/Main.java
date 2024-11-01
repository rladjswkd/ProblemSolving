import java.io.IOException;

public class Main {
	private static int funds, businessCount;
	private static int[][] profits, businessFunds;
	private static StringBuilder sb;

	private static int read() throws IOException {
		int n, c;

		// 문제 입력이 잘못됨. 공백이나 개행 등의 구분자가 여러 개 연달아 나오는 경우기 있나보다.
		// 따라서 아래의 while문으로 제거해주지 않으면 런타임 에러가 발생한다. 이런 문제가 꽤 있네...
		while ((c = System.in.read()) < 48)
			continue;
		n = c & 15;
		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void trace(int i, int x) {
		if (i == 1) {
			sb.append(businessFunds[i][x]);
			return;
		}
		trace(i - 1, x - businessFunds[i][x]);
		sb.append(' ').append(businessFunds[i][x]);
	}

	public static void main(String[] args) throws IOException {
		int x, currentProfit, newProfit, currentFunds;
		int[][] dp;

		funds = read();
		businessCount = read();
		// dp를 채워나가는 연산 과정의 수월함과 코드의 간결성을 위해 각 차원의 크기에 + 1
		profits = new int[businessCount + 1][funds + 1];
		for (int xi = 0; xi < funds; xi++) {
			x = read();
			for (int i = 1; i <= businessCount; i++)
				profits[i][x] = read();
		}
		// 기업별 투자금을 추적하기 위한 배열
		businessFunds = new int[businessCount + 1][funds + 1];
		// dp를 채워나가는 연산 과정의 수월함과 코드의 간결성을 위해 각 차원의 크기에 + 1
		dp = new int[businessCount + 1][funds + 1];
		for (int i = 1; i <= businessCount; i++) {
			for (x = 1; x <= funds; x++) {
				currentProfit = currentFunds = 0;
				// i 번째 회사에 part 만큼 투자할 때의 이익과 0 ~ i-1 번째 회사까지를 고려한 투자금 x - part에 대한 최대 이익을 더한
				// 값과 비교
				for (int part = 0; part <= x; part++) {
					if (currentProfit < (newProfit = profits[i][part] + dp[i - 1][x - part])) {
						currentProfit = newProfit;
						currentFunds = part;
					}
				}
				dp[i][x] = currentProfit;
				businessFunds[i][x] = currentFunds;
			}
		}
		sb = new StringBuilder().append(dp[businessCount][funds]).append('\n');
		trace(businessCount, funds);
		System.out.println(sb.toString());
	}
}