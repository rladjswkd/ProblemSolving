import java.io.IOException;

public class Main {
	private static long read() throws IOException {
		long n = System.in.read() & 15, cur;

		while (48L <= (cur = System.in.read()) && cur <= 57L)
			n = (n << 3L) + (n << 1L) + (cur & 15L);
		return n;
	}

	private static int[][] multiply(int[][] l, int[][] r) {
		int[][] res = new int[2][2];

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 2; k++)
					res[i][j] = (int)((res[i][j] + (long)l[i][k] * r[k][j]) % 1_000_000_007);
		return res;
	}

	public static void main(String[] args) throws IOException {
		long n = read();
		int[][] res = new int[][]{{1, 0}, {0, 1}};
		int[][] a = new int[][]{{1, 1}, {1, 0}};

		// 홀수라면 1을 빼 더해질 가장 큰 짝수번째의 인덱스로 만든다.
		if ((n & 1) == 1)
			n--;
		while (n > 0) {
			if ((n & 1) == 1)
				res = multiply(res, a);
			n >>>= 1;
			a = multiply(a, a);
		}
		System.out.println(res[0][0] - 1);
	}
}