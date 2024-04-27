import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur, sign = br.read();

		if (sign != '-')
			n = sign & 15;
		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == '-')
			n = -n;
		return n;
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), max;
		int[] arr;
		int[] dp;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < t; i++) {
			arr = new int[readInt()];
			for (int j = 0; j < arr.length; j++)
				arr[j] = readInt();
			dp = new int[arr.length];
			max = dp[0] = arr[0];
			for (int j = 1; j < dp.length; j++) {
				dp[j] = Math.max(dp[j - 1] + arr[j], arr[j]);
				if (max < dp[j])
					max = dp[j];
			}
			sb.append(max).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}