import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/*
 * 		0				C				A				P				C				A				K
 * 0	[0,0,0]	[0,0,0]	[0,0,0]	[0,0,0]	[0,0,0]	[0,0,0]	[0,0,0]
 * A	[0,0,0]	[0,0,0]	[0,1,1]	[0,1,1]	[0,1,1]	[0,4,1]	[0,4,1]
 * C	[0,0,0]	[1,0,1]	[1,0,1]	[1,0,1]	[1,3,2]	[1,3,2]	[1,3,2]
 * A	[0,0,0]	[1,0,1]	[2,1,2]	[2,1,2]	[2,1,2]	[2,4,3]	[2,4,3]
 * Y	[0,0,0]	[1,0,1]	[2,1,2]	[2,1,2]	[2,1,2]	[2,4,3]	[2,4,3]	
 * K	[0,0,0]	[1,0,1]	[2,1,2]	[2,1,2]	[2,1,2]	[2,4,3]	[4,5,4]
 * P	[0,0,0]	[1,0,1]	[2,1,2]	[2,1,2]	[2,1,2]	[2,4,3]	[4,5,4]		 
 */
public class Main {
	private static String print(int[][][] dp, char[] s1, char[] s2) {
		int[] prev = dp[dp.length - 1][dp[0].length - 1];
		char[] str = new char[prev[4]];
		int idx = str.length - 1;

		while (idx >= 0) {
			str[idx--] = s1[prev[2] - 1];
			prev = dp[prev[0]][prev[1]];
		}
		return String.valueOf(str);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] s1 = br.readLine().toCharArray(), s2 = br.readLine().toCharArray();
		int[][][] dp = new int[s1.length + 1][s2.length + 1][5];
		StringBuilder sb;
		// {이전 i, 이전 j, 현재 i, 현재 j, 값}
		for (int i = 1; i <= s1.length; i++) {
			for (int j = 1; j <= s2.length; j++) {
				if (s1[i - 1] == s2[j - 1])
					dp[i][j] = new int[] { i - 1, j - 1, i, j, dp[i - 1][j - 1][4] + 1 };
				else if (dp[i - 1][j][4] < dp[i][j - 1][4])
					dp[i][j] = new int[] {
							dp[i][j - 1][0], dp[i][j - 1][1],
							dp[i][j - 1][2], dp[i][j - 1][3],
							dp[i][j - 1][4] };
				else
					dp[i][j] = new int[] {
							dp[i - 1][j][0], dp[i - 1][j][1],
							dp[i - 1][j][2], dp[i - 1][j][3],
							dp[i - 1][j][4] };
			}
		}
		sb = new StringBuilder().append(dp[s1.length][s2.length][4]).append('\n');
		if (dp[s1.length][s2.length][4] > 0)
			sb.append(print(dp, s1, s2)).append('\n');
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}