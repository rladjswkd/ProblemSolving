
/*
 * 
 * 0th	1	3	2	3	2	2
 * 1st	4	5	5	5	4	
 * 2nd	9	0	0	9
 * 3rd	9	0	9
 * 4th	9	9
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int[] count = { 3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1 };

	public static void main(String[] args) throws IOException {
		String a = br.readLine(), b = br.readLine();
		int[][] dp = new int[(a.length() << 1) - 1][a.length() << 1];

		for (int i = 0; i < a.length(); i++) {
			dp[0][i << 1] = count[a.charAt(i) - 65];
			dp[0][(i << 1) + 1] = count[b.charAt(i) - 65];
		}
		for (int i = 1; i < dp.length; i++)
			for (int j = 0; j < dp[0].length - i; j++)
				dp[i][j] = (dp[i - 1][j] + dp[i - 1][j + 1]) % 10;
		bw.append((char) (dp[dp.length - 1][0] + 48)).append((char) (dp[dp.length - 1][1] + 48)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}