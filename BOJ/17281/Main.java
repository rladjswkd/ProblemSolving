import java.io.IOException;

public class Main {
	private static int n, bits, res;
	private static int[][] results;
	private static int[] order;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void calculate() {
		// ground의 오른쪽에서 0번 비트는 출발 지점, 1, 2, 3 비트는 각각 1, 2, 3루를 나타낸다.
		// 따라서 비트 시프트 연산자를 사용해 처리하면 최대 4 ~ 7비트가 1일 수 있다.
		int out, ground, player = 0, score = 0;
		int[] result;

		for (int inning = 0; inning < n; inning++) {
			result = results[inning];
			out = ground = 0;
			while (out < 3) {
				switch (result[order[player]]) {
					case 0:
						out++;
						break;
					case 1:
						ground |= 1;
						ground <<= 1;
						for (int i = 4; i <= 7; i++)
							if ((ground & 1 << i) > 0)
								score++;
						ground &= 0b1111;
						break;
					case 2:
						ground |= 1;
						ground <<= 2;
						for (int i = 4; i <= 7; i++)
							if ((ground & 1 << i) > 0)
								score++;
						break;
					case 3:
						ground |= 1;
						ground <<= 3;
						for (int i = 4; i <= 7; i++)
							if ((ground & 1 << i) > 0)
								score++;
						ground &= 0b1111;
						break;
					case 4:
						ground |= 1;
						ground <<= 4;
						for (int i = 4; i <= 7; i++)
							if ((ground & 1 << i) > 0)
								score++;
						ground &= 0b1111;
						break;
					default:
						break;
				}
				player = (player + 1) % 9;
			}
		}
		res = Math.max(res, score);
	}

	private static void solve(int idx) {
		if (idx == 9) {
			calculate();
			return;
		}
		if (idx == 3) {
			solve(idx + 1);
			return;
		}
		for (int i = 1; i < 9; i++) {
			if ((bits & 1 << i) == 0) {
				order[idx] = i;
				bits |= 1 << i;
				solve(idx + 1);
				bits ^= 1 << i;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		results = new int[n = read()][9];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 9; j++) {
				results[i][j] = System.in.read() & 15;
				System.in.read();
			}
		}
		order = new int[9];
		solve(0);
		System.out.println(res);
	}
}