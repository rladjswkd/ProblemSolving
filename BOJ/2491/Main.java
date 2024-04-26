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
		int asc = 1, desc = 1;
		int[] arr = new int[readInt()];
		int[][] dp = new int[arr.length][2];

		for (int i = 0; i < arr.length; i++)
			arr[i] = readInt();
		dp[0][0] = dp[0][1] = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] < arr[i]) {
				dp[i][0] = dp[i - 1][0] + 1;
				dp[i][1] = 1;
			} else if (arr[i - 1] > arr[i]) {
				dp[i][1] = dp[i - 1][1] + 1;
				dp[i][0] = 1;
			} else {
				dp[i][0] = dp[i - 1][0] + 1;
				dp[i][1] = dp[i - 1][1] + 1;
			}
		}
		for (int i = 0; i < dp.length; i++) {
			if (asc < dp[i][0])
				asc = dp[i][0];
			if (desc < dp[i][1])
				desc = dp[i][1];
		}
		asc = Math.max(asc, desc);
		bw.append(String.valueOf(asc)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}