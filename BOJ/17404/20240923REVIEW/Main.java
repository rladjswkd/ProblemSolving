import java.io.IOException;

public class Main {
	private static int houseCount;
	private static int[][] costs;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] prev, cur, swap;
		int res = Integer.MAX_VALUE;

		costs = new int[houseCount = read()][];
		for (int i = 0; i < houseCount; i++)
			costs[i] = new int[] { read(), read(), read() };
		// 레퍼런스 3개를 이용해 dp 배열을 생성하지 않고도 효율적으로 계산할 수 있다.
		prev = new int[3];
		cur = new int[3];
		swap = new int[3];
		// color: 1번 집에 칠해질 색
		for (int color = 0; color < 3; color++) {
			// 1번 집에 대해 color 의 비용만 입력값을 활용하고, 나머지는 최댓값 + 1을 활용
			cur[color] = costs[0][color];
			cur[(color + 1) % 3] = cur[(color + 2) % 3] = 1_000_001;
			// 2번 집부터 각각의 색에 대해 최소 누적 비용 계산
			for (int i = 1; i < houseCount; i++) {
				swap = prev;
				prev = cur;
				cur = swap;
				cur[0] = costs[i][0] + Math.min(prev[1], prev[2]);
				cur[1] = costs[i][1] + Math.min(prev[0], prev[2]);
				cur[2] = costs[i][2] + Math.min(prev[0], prev[1]);
			}
			// color와 다른 두 색 중 최솟값 선택
			res = Math.min(res, Math.min(cur[(color + 1) % 3], cur[(color + 2) % 3]));
		}
		System.out.println(res);
	}
}