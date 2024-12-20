
/*
 * N개의 수 중 i번째를 N_i라고 하면,
 * N_1 = M * Q_1 + R
 * N_2 = M * Q_2 + R
 * ...
 * N_i = M * Q_i + R
 * ...
 * N_N = M * Q_N + R
 * 
 * (N_2 - N_1) = M * (Q_2 - Q_1)
 * (N_3 - N_2) = M * (Q_3 - Q_2)
 * ...
 * 
 * 이렇게 두 수를 뺀 수들로 구성된 수열에 대해 최소공배수를 구한 뒤, 그 값의 약수들을 1을 제외하고 출력
 * 
 * java8 기준 첫 번째 아이디어는 1344ms, 두 번째 아이디어는 68ms가 나온다.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int getGCD(int a, int b) {
		int c;

		while (b > 0) {
			c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), gcd;
		int[] numbers = new int[n];
		List<Integer> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++)
			numbers[i] = readInt();
		// 2 <= N
		gcd = Math.abs(numbers[1] - numbers[0]);
		for (int i = 2; i < n; i++)
			gcd = getGCD(gcd, Math.abs(numbers[i] - numbers[i - 1]));
		for (int i = 2; i <= Math.sqrt(gcd); i++)
			if (gcd % i == 0) {
				res.add(i);
				if (i != gcd / i)
					res.add(gcd / i);
			}
		Collections.sort(res);
		for (int divisor : res)
			sb.append(divisor).append(' ');
		sb.append(gcd).append('\n');
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}