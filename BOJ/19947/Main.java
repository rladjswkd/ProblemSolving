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
		int h = readInt(), y = readInt();
		int[] dp = new int[y + 1];

		dp[0] = h;
		for (int i = 0; i < y; i++) {
			if (i + 5 <= y && dp[i + 5] < dp[i] * 1.35)
				dp[i + 5] = (int) (dp[i] * 1.35);
			if (i + 3 <= y && dp[i + 3] < dp[i] * 1.2)
				dp[i + 3] = (int) (dp[i] * 1.2);
			if (dp[i + 1] < dp[i] * 1.05)
				dp[i + 1] = (int) (dp[i] * 1.05);
		}
		bw.append(String.valueOf(dp[y])).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}