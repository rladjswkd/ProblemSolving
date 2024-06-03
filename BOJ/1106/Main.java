import java.io.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	// 100원에 1명으로 1000명 채워야할 때 + 1
	private static final int INFINITY = 100 * 1000 + 1;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int solve(int customerCount, int cityCount, int[][] cities, int maxCustomer) {
		int cost, customer, res = INFINITY;
		// 우리가 찾는 값은 customerCount만큼의 고객을 확보하기 위한 최소 비용이지만
		// 정확히 customerCount만큼의 고객을 확보하는 것보다 이를 넘어서는 고객을 확보하는 게 비용이 더 적을 가능성이 있으므로 이를
		// 고려해야한다. 이때 "customerCount를 넘어서는 고객 수의 최댓값"이 customerCount + maxCustomer가 된다.
		// maxCustomer는 입력으로 주어진 도시 별 <비용 - 고객의 수> 쌍 중 가장 큰 "고객의 수"를 담고있다.
		// customerCount에 맞는 비용을 찾았다면, 더 많은 비용을 들여서 고객을 추가할 필요가 없다.
		// 하지만 아직 customerCount보다 적은 고객만을 확보했다면, 그 순간이 더 많은 비용을 들여서 고객을 추가할 필요가 있는 순간이고
		// 기준 값이 customerCount보다 작은 상황에서 가장 큰 값인 maxCustomer를 더해봐야 customerCount +
		// maxCounter보다 작을 수 밖에 없다.
		// dp[0]은 의미상 0명의 고객을 확보하기 위한 비용을 뜻하므로 무조건 0의 값을 가져야한다. 따라서 + 1을 해줘야한다.
		int[] dp = new int[customerCount + maxCustomer + 1];
		// dp[0] = 0;
		for (int i = 1; i < dp.length; i++)
			dp[i] = INFINITY;
		for (int[] city : cities) {
			cost = city[0];
			customer = city[1];
			for (int i = customer; i < dp.length; i++)
				if (dp[i - customer] + cost < dp[i])
					dp[i] = dp[i - customer] + cost;
		}
		for (int i = customerCount; i < dp.length; i++)
			if (dp[i] < res)
				res = dp[i];
		return res;
	}

	public static void main(String[] args) throws IOException {
		int customerCount = readInt(), cityCount = readInt(), maxCustomer = 0;
		int[][] cities = new int[cityCount][];

		for (int i = 0; i < cityCount; i++) {
			cities[i] = new int[] { readInt(), readInt() };
			if (maxCustomer < cities[i][1])
				maxCustomer = cities[i][1];
		}
		bw.write(new StringBuilder().append(solve(customerCount, cityCount, cities, maxCustomer)).append('\n').toString());
		bw.flush();
		br.close();
		bw.close();
	}
}