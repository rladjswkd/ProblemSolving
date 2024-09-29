import java.io.IOException;

public class Main {
	private static int[] diceValues, cellValues;
	private static int[][] nextCells;
	private static int res;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	// idx: 현재 주사위 값의 인덱스
	// score: 현재까지 이동한 경로에 따른 점수
	// pos: aabbccdd 형식으로 aa, bb, cc, dd 각각 1, 2, 3, 4 번 말이 위치한 칸의 인덱스를 나타낸다.
	private static void solve(int idx, int score, int pos) {
		int p1 = pos / 1000000, p2 = pos % 1000000 / 10000, p3 = pos % 10000 / 100, p4 = pos % 100, np;
		long bits = 0;
		int[] nc;

		if (idx == 10) {
			res = Math.max(res, score);
			return;
		}
		nc = nextCells[diceValues[idx]];
		// p1이 도착 칸에 위치하지 않고, 이동할 칸이 도착 칸이 아닐 땐 이동한 칸에 다른 말이 없으며, 같은 칸에 위치한 첫 번째 말일 때
		if (p1 != 32 && ((np = nc[p1]) == 32 || (np != 32 && p2 != np && p3 != np && p4 != np)) && (bits & 1L << p1) == 0) {
			solve(idx + 1, score + cellValues[np], np * 1000000 + p2 * 10000 + p3 * 100 + p4);
			bits |= 1L << p1;
		}
		// p2가 도착 칸에 위치하지 않고, 이동할 칸이 도착 칸이 아닐 땐 이동한 칸에 다른 말이 없으며, 같은 칸에 위치한 첫 번째 말일 때
		if (p2 != 32 && ((np = nc[p2]) == 32 || (np != 32 && p1 != np && p3 != np && p4 != np)) && (bits & 1L << p2) == 0) {
			solve(idx + 1, score + cellValues[np], p1 * 1000000 + np * 10000 + p3 * 100 + p4);
			bits |= 1L << p2;
		}
		// p3가 도착 칸에 위치하지 않고, 이동할 칸이 도착 칸이 아닐 땐 이동한 칸에 다른 말이 없으며, 같은 칸에 위치한 첫 번째 말일 때
		if (p3 != 32 && ((np = nc[p3]) == 32 || (np != 32 && p1 != np && p2 != np && p4 != np)) && (bits & 1L << p3) == 0) {
			solve(idx + 1, score + cellValues[np], p1 * 1000000 + p2 * 10000 + np * 100 + p4);
			bits |= 1L << p3;
		}
		// p4가 도착 칸에 위치하지 않고, 이동할 칸이 도착 칸이 아닐 땐 이동한 칸에 다른 말이 없으며, 같은 칸에 위치한 첫 번째 말일 때
		if (p4 != 32 && ((np = nc[p4]) == 32 || (np != 32 && p1 != np && p2 != np && p3 != np)) && (bits & 1L << p4) == 0) {
			solve(idx + 1, score + cellValues[np], p1 * 1000000 + p2 * 10000 + p3 * 100 + np);
			bits |= 1L << p4;
		}
	}

	public static void main(String[] args) throws IOException {
		cellValues = new int[] { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 13, 16, 19, 22,
				24, 28, 27, 26, 25, 30, 35, 40, 0 };
		nextCells = new int[][] {
				{ 1, 2, 3, 4, 5, 20, 7, 8, 9, 10, 23, 12, 13, 14, 15, 25, 17, 18, 19, 31, 21, 22, 28, 24, 28, 26, 27, 28, 29,
						30, 31, 32 },
				{ 2, 3, 4, 5, 6, 21, 8, 9, 10, 11, 24, 13, 14, 15, 16, 26, 18, 19, 31, 32, 22, 28, 29, 28, 29, 27, 28, 29, 30,
						31, 32, 32 },
				{ 3, 4, 5, 6, 7, 22, 9, 10, 11, 12, 28, 14, 15, 16, 17, 27, 19, 31, 32, 32, 28, 29, 30, 29, 30, 28, 29, 30, 31,
						32, 32, 32 },
				{ 4, 5, 6, 7, 8, 28, 10, 11, 12, 13, 29, 15, 16, 17, 18, 28, 31, 32, 32, 32, 29, 30, 31, 30, 31, 29, 30, 31, 32,
						32, 32, 32 },
				{ 5, 6, 7, 8, 9, 29, 11, 12, 13, 14, 30, 16, 17, 18, 19, 29, 32, 32, 32, 32, 30, 31, 32, 31, 32, 30, 31, 32, 32,
						32, 32, 32 } };
		diceValues = new int[10];
		for (int i = 0; i < 10; i++)
			diceValues[i] = read() - 1;
		solve(0, 0, 0);
		System.out.println(res);
	}
}