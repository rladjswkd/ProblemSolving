import java.io.IOException;

public class Main {
	private static long read() throws IOException {
		long n = System.in.read() & 15;
		int cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int[][] multiply(int[][] l, int[][] r) {
		int[][] res = new int[2][2];

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 2; k++)
					res[i][j] = (int)((res[i][j] + (long)l[i][k] * r[k][j]) % 1_000_000);
		return res;
	}

	public static void main(String[] args) throws IOException {
		long n = read() - 1;
		int[][] a = new int[][]{{1, 1}, {1, 0}};
		int[][] res = new int[][]{{1, 0}, {0, 1}};

		while (n > 0) {
			if ((n & 1) == 1)
				res = multiply(res, a);
			n >>>= 1;
			a = multiply(a, a);
		}
		System.out.println(res[0][0]);
	}
}