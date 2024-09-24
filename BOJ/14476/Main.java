import java.io.IOException;

public class Main {
	private static int n;
	private static int[] integers;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int calGCD(int a, int b) {
		int c;

		while (b > 0) {
			c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		// lefts[i]: 0 ~ i 번째 값들의 최대공약수, rights[i] = i ~ n - 1 번째 값들의 최대공약수
		int[] lefts, rights;
		int gcd = 0, k = -1, cur;

		integers = new int[n = read()];
		lefts = new int[n];
		rights = new int[n];
		for (int i = 0; i < n; i++)
			integers[i] = read();
		lefts[0] = integers[0];
		rights[n - 1] = integers[n - 1];
		for (int i = 1; i < n; i++) {
			lefts[i] = calGCD(lefts[i - 1], integers[i]);
			rights[n - 1 - i] = calGCD(rights[n - i], integers[n - i - 1]);
		}
		// 0번째 정수를 제외할 때. 4 <= n이므로 rights[1]은 항상 유효.
		if (integers[0] % rights[1] != 0) {
			gcd = rights[1];
			k = integers[0];
		}
		// n-1 번째 정수를 제외할 때
		if (integers[n - 1] % lefts[n - 2] != 0 && gcd < lefts[n - 2]) {
			gcd = lefts[n - 2];
			k = integers[n - 1];
		}
		// 1 ~ n-2번째 정수를 제외할 때
		for (int i = 1; i <= n - 2; i++) {
			cur = calGCD(lefts[i - 1], rights[i + 1]);
			if (integers[i] % cur != 0 && gcd < cur) {
				gcd = cur;
				k = integers[i];
			}
		}
		if (gcd == 0)
			System.out.println(-1);
		else
			System.out.printf("%d %d\n", gcd, k);
	}
}