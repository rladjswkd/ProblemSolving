import java.io.IOException;

public class Main {
	// 조합을 나타내는 배열
	private static long[][] dp;

	private static long readLong() throws IOException {
		long n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static long countOnes(long v) {
		// lastBit는 오른쪽으로부터의 비트 인덱스를 나타낸다. 즉, lastBit가 x면 오른쪽으로부터 x + 1번째 비트에 해당한다.
		int counter = 0, lastBit = 0, rightZeroCount;
		long res = 0;

		if (v == 0)
			return 0;
		while ((v & 1L << lastBit) == 0)
			lastBit++;
		for (int i = lastBit; 1L << i <= v; i++)
			if ((v & 1L << i) > 0)
				counter++;
		// v에 포함된 1의 개수를 반영
		res += counter;
		while (counter > 0) {
			// 가장 오른쪽에 있는 값이 1인 비트를 0으로 바꾸기. lastBit는 현재 가장 오른쪽에 있는 값이 1인 비트의 인덱스이면서, 그 비트보다
			// 오른쪽에 있는 비트의 개수가 된다.
			counter--;
			rightZeroCount = lastBit++;
			for (int i = 0; i <= rightZeroCount; i++)
				res += (counter + i) * dp[rightZeroCount][i];
			while ((v & 1L << lastBit) == 0)
				lastBit++;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		long a = readLong(), b = readLong();

		dp = new long[54][54];
		for (int i = 0; i < 54; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= i; j++)
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
		}
		System.out.println(countOnes(b) - countOnes(a - 1));
	}
}