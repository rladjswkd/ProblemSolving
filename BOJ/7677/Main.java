import java.io.IOException;

public class Main {
	private static int read() throws IOException {
		int n = 0, c, s = System.in.read();

		if (s != 45)
			n = s & 15;
		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return s != 45 ? n : ~n + 1;
	}

	private static int[][] multiply(int[][] l, int[][] r) {
		int[][] res = new int[2][2];

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 2; k++)
					res[i][j] = (res[i][j] + l[i][k] * r[k][j]) % 10000;
		return res;
	}
	public static void main(String[] args) throws IOException {
		int n;
		StringBuilder sb = new StringBuilder();
		int[][] a = new int[2][2], res = new int[2][2];

		while ((n = read()) != -1) {
			if (n == 0) {
				sb.append("0\n");
				continue;
			}
			n--;
			a[1][1] = res[0][1] = res[1][0] = 0;
			a[0][0] = a[0][1] = a[1][0] = res[0][0] = res[1][1] = 1;
			while (n > 0) {
				if ((n & 1) == 1)
					res = multiply(res, a);
				n >>>= 1;
				a = multiply(a, a);
			}
			sb.append(res[0][0]).append('\n');
		}
		System.out.print(sb.toString());
	}
}