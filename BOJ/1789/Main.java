/*
 * 가장 작은 자연수부터 하나씩 S에서 빼기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static long readLong() throws IOException {
		long n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		long s = readLong(), n = 1, total = 0;

		for (; total + n <= s; n++)
			total += n;
		bw.append(String.valueOf(n - 1)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}