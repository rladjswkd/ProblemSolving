
/*
 * 지방들이 요청한 예산 중 최대값을 찾아 (총 예산 / 지방의 수)부터 최대값까지 양 끝을 포함한 범위 내의 값에 대해 이분 탐색하며 해당 값을 상한액으로 활용할 수 있는지 확인-> 이 중 최대값 출력
 * 최소값이 아닌 (총 예산 / 지방의 수)를 활용하는 이유는 아래와 같은 반례 때문이다.
 * 5
 * 100 100 100 100 100
 * 10
 * 위 반례에서 최소값, 최대값을 활용하면 둘 다 100이므로 답인 2를 탐색하지 않는다.
 * 
 * 
 * log2(100000) = 16.6...
 * n의 최대값 = 10000
 * -> 10000개를 순회하며 모두 더하는 연산을 16.6회 해봐야 17만 이하
 * 
 * parametricSearch 함수를 아래와 같이 구현하면 안된다.
 * start가 1이고 end가 2인 상황처럼 start와 mid의 값이 같을 때 if (totalTemp <= totalBudget)이 참이라면 무한 반복에 빠진다.
 *  
 * 	private static int parametricSearch(int[] targets, int[] budgets, int totalBudget) {
 *		int start = targets[0], end = targets[targets.length - 1], mid = 0;
 *		long totalTemp;

 *		while (start < end) {
 *			mid = (start + end) / 2;
 *			totalTemp = 0;
 *			for (int budget : budgets)
 *				totalTemp += budget < mid ? budget : mid;
 *			if (totalTemp <= totalBudget)
 *				start = mid;
 *			else
 *				end = mid - 1;
 *		}
 *		return start;
 *	}
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int parametricSearch(int[] budgets, int maxBudget, int totalBudget) {
		int start = totalBudget / budgets.length, end = maxBudget, mid = 0, res = 0;
		long totalTemp;
		// 어떠한 주어진 값을 범위 내에서 찾는 것이 아닌 대상 값들을 검사해야 한다.
		// 따라서 조건에 맞는 값을 찾으면 break하고 해당 값을 반환하면 안되고 최적 값까지 계속 탐색해야 한다.
		while (start <= end) {
			mid = (start + end) / 2;
			totalTemp = 0;
			for (int budget : budgets)
				totalTemp += budget < mid ? budget : mid;
			if (totalTemp <= totalBudget) {
				start = mid + 1;
				// res가 업데이트된다면, 이는 직전의 res보다 큰 값이면서 조건을 만족하는 값이므로 적절하다.
				res = mid;
			} else
				end = mid - 1;
		}
		// start든 end든 같은 값이므로 무얼 반환해도 상관없다.
		return res;
	}

	public static void main(String[] args) throws IOException {
		int budgetCount = readInt(), totalBudget, maxBudget = 0;
		int[] budgets = new int[budgetCount];

		for (int i = 0; i < budgetCount; i++) {
			budgets[i] = readInt();
			if (maxBudget < budgets[i])
				maxBudget = budgets[i];
		}
		totalBudget = readInt();
		bw.append(String.valueOf(parametricSearch(budgets, maxBudget, totalBudget))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}