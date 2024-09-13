import java.io.IOException;

public class Main {
	private static int n;
	private static int[][] points;
	private static long res;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return sign == 45 ? ~n + 1 : n;
	}

	// count1: 부호 그대로 더할 점의 개수
	// count2: 부호를 반전시켜 더할 점의 개수
	// idx: 현재 확인할 점의 인덱스
	private static void solve(int x, int y, int count1, int count2, int idx) {
		if (idx == n) {
			res = Math.min(res, (long) x * x + (long) y * y);
			return;
		}
		if (count1 < n / 2)
			solve(x + points[idx][0], y + points[idx][1], count1 + 1, count2, idx + 1);
		if (count2 < n / 2)
			solve(x - points[idx][0], y - points[idx][1], count1, count2 + 1, idx + 1);
	}

	public static void main(String[] args) throws IOException {
		int t = readInt();
		StringBuilder sb = new StringBuilder();

		points = new int[20][2];
		while (t-- > 0) {
			n = readInt();
			for (int i = 0; i < n; i++) {
				points[i][0] = readInt();
				points[i][1] = readInt();
			}
			res = 4_000_000_000_000L;
			solve(0, 0, 0, 0, 0);
			sb.append(Math.sqrt(res)).append('\n');
		}
		System.out.print(sb.toString());
	}
}