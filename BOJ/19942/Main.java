import java.io.IOException;

public class Main {
	private static int n, targetProtein, targetFat, targetCarbohydrate, targetVitamin, resCost, resMask;
	private static int[][] table;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int idx, int mask, int protein, int fat, int carbohydrate, int vitamin, int cost) {
		int i1, i2;
		boolean update = false;

		if (idx == n) {
			if (cost < resCost)
				update = true;
			else if (cost == resCost) {
				i1 = i2 = 0;
				while (i1 < n && i2 < n) {
					while (i1 < n && (mask & 1 << i1) == 0)
						i1++;
					while (i2 < n && (resMask & 1 << i2) == 0)
						i2++;
					if (i1 == i2) {
						i1++;
						i2++;
					} else
						break;
				}
				if ((i1 < n && i2 < n && i1 < i2) || i1 == n)
					update = true;
			}
			if (protein >= targetProtein && fat >= targetFat && carbohydrate >= targetCarbohydrate
					&& vitamin >= targetVitamin && update) {
				resCost = cost;
				resMask = mask;
			}
			return;
		}
		solve(idx + 1, mask | 1 << idx, protein + table[idx][0], fat + table[idx][1], carbohydrate + table[idx][2],
				vitamin + table[idx][3], cost + table[idx][4]);
		solve(idx + 1, mask, protein, fat, carbohydrate, vitamin, cost);
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		table = new int[n = read()][5];
		targetProtein = read();
		targetFat = read();
		targetCarbohydrate = read();
		targetVitamin = read();
		resCost = 7501;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < 5; j++)
				table[i][j] = read();
		solve(0, 0, 0, 0, 0, 0, 0);
		if (resCost != 7501) {
			sb.append(resCost).append('\n');
			for (int i = 0; i < n; i++)
				if ((resMask & 1 << i) > 0)
					sb.append(i + 1).append(' ');
		} else
			sb.append(-1);
		System.out.println(sb.toString());
	}
}