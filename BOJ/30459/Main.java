import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int pileCount, poleCount, area;
	private static int[] piles, poles;
	private static double res;

	private static int read() throws IOException {
		int n = 0, c, s = System.in.read();

		if (s != 45)
			n = s & 15;
		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return s != 45 ? n : ~n + 1;
	}

	private static void findLowerBound(int i, int j) {
		int base = Math.abs(piles[i] - piles[j]), start = 0, end = poleCount - 1, mid, value = -2 * area / base;

		if (value == 0)
			return;
		while (start < end) {
			mid = (start + end) >>> 1;
			if (poles[mid] < value)
				start = mid + 1;
			else
				end = mid;
		}
		if (poles[start] >= value)
			res = Math.max(res, -poles[start] * base / 2.0);
	}

	public static void main(String[] args) throws IOException {
		piles = new int[pileCount = read()];
		poles = new int[poleCount = read()];
		area = read();
		for (int i = 0; i < pileCount; i++)
			piles[i] = read();
		for (int i = 0; i < poleCount; i++)
			poles[i] = -read();
		Arrays.sort(poles);
		res = -1;
		for (int i = 0; i < pileCount; i++)
			for (int j = i + 1; j < pileCount; j++)
				findLowerBound(i, j);
		if (res < 0)
			System.out.println(-1);
		else
			System.out.printf("%.1f\n", res);
	}
}