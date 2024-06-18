/*
 * int[][][] dp
 * 
 * 문자열 두 개의 LCS를 구할 땐 2차원 배열을 사용했다는 것에 기반해, 3차원 배열을 사용하는 것을 고려해보자.
 * 
 * abcdefghijklmn - 레벨 1 for문
 * bdefg - 레벨 2 for문
 * efg - 레벨 3 for문
 * 
 * a b e
 * a b f
 * a b g
 * 
 * a d e
 * a d f
 * a d g
 * 
 * a e e
 * a e f
 * a e g
 * 
 * a f e
 * a f f
 * a f g
 * 
 * ...
 * 
 * e e e -> dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static char[] first, second, third;
	private static int[][][] lcs;

	public static void main(String[] args) throws IOException {
		first = br.readLine().toCharArray();
		second = br.readLine().toCharArray();
		third = br.readLine().toCharArray();
		lcs = new int[first.length + 1][second.length + 1][third.length + 1];
		for (int i = 1; i <= first.length; i++) {
			for (int j = 1; j <= second.length; j++) {
				for (int k = 1; k <= third.length; k++) {
					if (first[i - 1] == second[j - 1] && second[j - 1] == third[k - 1])
						lcs[i][j][k] = lcs[i - 1][j - 1][k - 1] + 1;
					else
						lcs[i][j][k] = Math.max(Math.max(lcs[i][j][k - 1], lcs[i][j - 1][k]), lcs[i - 1][j][k]);
				}
			}
		}
		System.out.println(lcs[first.length][second.length][third.length]);
		br.close();
	}
}