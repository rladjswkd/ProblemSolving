
/*
 * LIS
 * 
 * 1. DP를 사용하는 방식 O(n^2)
 * arr:	1 5 2 3 7
 * dp:	1 2 2 3 4
 * 
 * dp[n]은 arr[x], 0 <= x <= n - 1을 순회하며 arr[n]보다 "값이 작은" 것들 중 가장 큰 dp[x]를 선택해 1을 더하여 채운다.
 * 
 * 2. binary search를 사용하는 방식 O(n logn)
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static List<Integer> il = new ArrayList<>();

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int solveDP() throws IOException {
		// arr[idx] < arr[현재_인덱스] 인 모든 idx들 중 dp[idx] 값이 가장 큰 idx.
		int n = readInt(), idx, res = 0;
		// dp[0]의 값을 0으로 둬야 arr[x]가 그 앞의 값들 중에 가장 작은 값이 나왔을 때의 dp[x]가 1이 될 수 있다.
		// arr과 dp의 사이즈를 맞춰야 하므로 arr도 길이를 n + 1로 설정하고 arr[0]을 0으로 둔다.
		int[] arr = new int[n + 1], dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			arr[i] = readInt();
			idx = 0;
			for (int j = 0; j < i; j++)
				if (arr[j] < arr[i] && dp[idx] < dp[j])
					idx = j;
			dp[i] = dp[idx] + 1;
			if (res < dp[i])
				res = dp[i];
		}
		return res;
	}

	private static int binarySearch(int start, int end, int val) {
		int low = -1;

		// while 조건식에서 start와 end읙 값이 같을 때도 반드시 체크해줘야 하는 이유는 low가 (start + end) / 2로
		// 업데이트 되어야 하기 때문이다. 하지만 start == end인 상황에서 il.get(low) > val이 성립한다면,
		// end가 low가 되고(즉, 값이 변하지 않게 된다), 무한 반복에 빠지게 된다.
		// 따라서 while 조건식에 start != low가 필요하다.
		while (start <= end && start != low) {
			low = (start + end) / 2;
			if (il.get(low) < val)
				start = low + 1;
			else if (il.get(low) > val)
				end = low;
			else
				break;
		}
		return low;
	}

	private static int solveBS() throws IOException {
		int n = readInt(), max;
		int[] arr = new int[n];

		for (int i = 0; i < n; i++)
			arr[i] = readInt();
		il.add(arr[0]);
		for (int i = 1; i < n; i++) {
			max = il.get(il.size() - 1);
			if (max == arr[i])
				continue;
			else if (max < arr[i])
				il.add(arr[i]);
			else
				il.set(binarySearch(0, il.size() - 1, arr[i]), arr[i]);
		}
		return il.size();
	}

	public static void main(String[] args) throws IOException {
		// bw.append(String.valueOf(solveDP())).append('\n');
		bw.append(String.valueOf(solveBS())).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}