import java.io.IOException;

public class Main {
	private static int n;
	private static int[][] a;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}
	
	private static long readLong() throws IOException {
		long n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
 }

	private static int[][] multiply(int[][] l, int[][] r) {
		int[][] res = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++)
					res[i][j] += l[i][k] * r[k][j];
				res[i][j] %= 1000;
			}
		}
		return res;			
	}

	public static void main(String[] args) throws IOException {
		long b;
		int[][] res;
		StringBuilder sb = new StringBuilder();

		a =	new int[n = readInt()][n];
		res = new int[n][n];
		b = readLong();
		for (int i = 0; i < n; i++) {
			// res는 단위 행렬로 초기화
			res[i][i] = 1;
			for (int j = 0; j < n; j++)
				a[i][j] = readInt() % 1000;
		}
		while (b > 0) {
			if ((b & 1) == 1)
				res = multiply(res, a);
			b >>>= 1;
			a = multiply(a, a);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				sb.append(res[i][j]).append(' ');
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}