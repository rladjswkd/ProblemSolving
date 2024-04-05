import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/*
 * s1, s2를 문자열로 놓고 charAt()을 호출하면서 풀면 264ms
 * s1, s2를 입력시에 toCharArray()를 호출해 문자 배열로 놓고 인덱싱을 통해 풀면 244ms
 */
public class Main {
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] s1, s2;
		int res = 0;

		s1 = br.readLine().toCharArray();
		s2 = br.readLine().toCharArray();
		dp = new int[s1.length + 1][s2.length + 1];
		for (int i = 1; i <= s1.length; i++) {
			for (int j = 1; j <= s2.length; j++) {
				if (s1[i - 1] == s2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					if (res < dp[i][j])
						res = dp[i][j];
				}
			}
		}
		bw.write(res + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
}