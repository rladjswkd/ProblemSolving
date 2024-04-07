import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
 * 가장 긴 오름차순 부분을 제외한 나머지 모든 숫자들을 옮기면 된다.
 * 이러한 순열을 LIS(Longest Increasing Sequence)라고 한다.
 * 
 * 예를 들어, 예제인 3 7 5 2 6 1 4에선 가장 긴 연속하는 부분인 3, 5, 6을 그대로 두고 7, 2, 1, 4를 옮기면 된다.
 * 
 * 그런데, 7, 2, 1, 4를 옮기는 와중에 7, 2, 1, 4 중 하나가 자신의 원래 자리를 찾는다면?
 * 상관없다. 어차피 나머지가 자기 자리가 아닐테니까 그들을 옮기는 것보다 원래 계획한 대로 옮기는 게 더 최적해가 아닐까
 * 
 * 또한, 7, 2, 1, 4를 옮기는 순서도 상관없어보인다.
 * 
 * 그렇다면, "가장 긴 연속하는 부분"이 아닌 "가장 긴 오름차순 부분"은 어떻게 찾아야 할까?
 * 예를 들어 3 다음에 7을 선택해버리면 3, 7로 오름차순 부분의 길이가 2에 불과하지만
 * 3 다음에 7을 건너뛰고 5를 선택한 후 6을 선택해야 오름차순 부분의 길이가 3이 된다.
 * 
 * LIS의 길이를 구하는 방식은 두 가지가 있다.
 * 하나는 DP, 하나는 Binary Search 방식이다.
 * 
 * 이 문제에서는 입력값의 길이가 최대 200이므로, O(n^2)의 시간 복잡도를 갖는 DP 방식을 사용해도 문제없다.
 * idx	0	1	2	3	4	5	6	7
 * arr	0	3	7	5	2	6	1	4
 * dp		0	1	2	2	1	3	1	2	-> dp[n]은 0 ~ n - 1까지의 idx를 순회하며 arr[idx] 값이 arr[n]보다 작은 경우 중 가장 큰 dp[idx] 값을 찾아 1을 더한다.
 * 
 * 다만, 시간 복잡도가 O(nlogn)으로 더 효율적인 Binary Search를 사용하는 방법도 알아두자.
 * 
 */

public class Main {
	private static int n;
	private static int[] order;

	// dp를 이용한 방식
	private static int calculateLISLengthDP() {
		int[] dp = new int[order.length];
		int max = 0;

		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++)
				if (order[j] < order[i] && dp[j] + 1 > dp[i])
					dp[i] = dp[j] + 1;
			if (max < dp[i])
				max = dp[i];
		}
		return max;
	}

	// binary search를 이용한 방식
	private static int calculateLISLengthBS() {
		List<Integer> il = new ArrayList<>();
		int low;

		il.add(order[0]);
		for (int i = 1; i < n; i++) {
			if (order[i] > il.get(il.size() - 1))
				il.add(order[i]);
			else {
				low = Collections.binarySearch(il, order[i]);
				if (low < 0)
					low = (low + 1) * -1;
				il.set(low, order[i]);
			}
		}
		return il.size();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		order = new int[n];
		for (int i = 0; i < n; i++)
			order[i] = Integer.parseInt(br.readLine());
		bw.write((n - calculateLISLengthDP()) + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
}