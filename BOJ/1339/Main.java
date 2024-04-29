/*
 * GCF
 * ACDEB
 * 
 * A 9
 * C 8
 * 
 * GCF
 * DEB
 * 
 * 길이가 같은 여러 문자열이 있을 때, 어떻게 숫자를 부여할까?
 * 
 * 각 문자열의 0번 인덱스 문자 중에서 1번 이상의 인덱스 중 가장 먼저 등장하는 것부터 순서대로 큰 값을 부여.
 * ABC
 * BCA
 * -> 두 문자열을 함께 순서대로 탐색하며 A와 B 중 먼저 나오는 것에 큰 값 부여
 * 즉, 여기선 A에 큰 값, B에 그 다음 값 부여
 * 
 * AB, BA와 같이 우선순위가 같은 경우 또는 GCF, DEB와 같이 어떤 문자도 1번 인덱스부터 나오지 않은 경우에는 알파벳 순서 상 먼저 나오는 것에 큰 값 부여
 * 
 * 이 로직대로 값을 부여하면
 * A = 9, C = 8, D = 7, G = 6, E = 5, B = 4, F = 3
 * 683
 * 98754
 */

import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine()), val = 9, res = 0;
		int[] alphabet = new int[26];
		char[] word;

		for (int i = 0; i < n; i++) {
			word = br.readLine().toCharArray();
			for (int j = 0; j < word.length; j++)
				alphabet[word[j] - 65] += (int) Math.pow(10, word.length - j - 1);
		}
		Arrays.sort(alphabet);
		for (int i = alphabet.length - 1; i >= 0; i--) {
			if (alphabet[i] == 0)
				break;
			res += alphabet[i] * val--;
		}
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}