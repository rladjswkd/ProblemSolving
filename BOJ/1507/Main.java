import java.io.IOException;

public class Main {
	private static int n;
	private static int[][] lowestCosts, costs;
	private static final int INFINITY = 2501;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void copyCosts() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				costs[i][j] = lowestCosts[i][j];
	}

	private static void findShortestPath() {
		int newCost;

		for (int via = 0; via < n; via++)
			for (int start = 0; start < n; start++)
				for (int end = 0; end < n; end++)
					if ((newCost = costs[start][via] + costs[via][end]) < costs[start][end])
						costs[start][end] = newCost;
	}

	private static boolean checkCosts() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (lowestCosts[i][j] != costs[i][j])
					return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		int length = 0, res = 0;
		int[][] removed;
		int[] road;
		boolean wrongInput = false;

		lowestCosts = new int[n = readInt()][n];
		costs = new int[n][n];
		removed = new int[n * n - n][];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				lowestCosts[i][j] = readInt();
		// 애초에 강호가 잘못 구했을 때를 문제에서 불가능한 경우라고 말하는 것 같다.
		// 이땐 -1을 반환해야 한다.
		for (int i = 0; i < n && !wrongInput; i++)
			for (int j = i + 1; j < n && !wrongInput; j++)
				if (lowestCosts[i][j] != lowestCosts[j][i])
					wrongInput = true;
		copyCosts();
		findShortestPath();
		if (!checkCosts() || wrongInput)
			System.out.println(-1);
		else {
			for (int i = 0; i < n; i++)
				for (int j = i + 1; j < n; j++)
					res += lowestCosts[i][j];
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					copyCosts();
					for (int k = 0; k < length; k++) {
						road = removed[k];
						costs[road[0]][road[1]] = costs[road[1]][road[0]] = INFINITY;
					}
					costs[i][j] = costs[j][i] = INFINITY;
					findShortestPath();
					if (checkCosts()) {
						removed[length++] = new int[] { i, j };
						res -= lowestCosts[i][j];
					}
				}
			}
			System.out.println(res);
		}
	}
}