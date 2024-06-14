/*
 * https://www.geeksforgeeks.org/meet-in-the-middle/ 방식을 참고해 적용한 meet-in-the-middle 알고리즘 구현
 * 리스트가 아닌 배열을 썼다고 해서 더 시간 효율적이진 않다. 시간은 대충 비슷하게 걸리지만, 메모리는 훨씬 효율적이다.
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int[] seq;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			n = ~n + 1;
		return n;
	}

	// [start, end)
	private static void calculateSubArray(int start, int end, int[] arr) {
		// end - start는 배열 x 또는 y의 크기를 나타내고, 1 << (end - start)는 각 배열의 숫자들을 선택하거나 안하거나의
		// 모든 경우를 나타내는 2^(end -start)다.
		int size = end - start, last = 1 << size, acc;

		// 배열의 특정 범위 내의 원소들 증 아무것도 선택하지 않은 i = 0부터 모두를 선택한 last까지 순회
		// seq의 최대 길이가 40이고, 이를 x와 y로 둘로 나눴으니 x와 y는 길어야 20이다. -> int 타입으로 비트마스킹 가능
		for (int i = 0; i < last; i++) {
			acc = 0;
			// 현재 선택한 원소들에 대한 비트가 1로 채워져있는지 확인하고 그렇다면, seq 배열 내의 해당 값을 누적
			for (int j = 0; j < size; j++)
				if ((i & (1 << j)) > 0)
					acc += seq[j + start];
			arr[i] = acc;
		}
	}

	private static int findLowerBound(int[] arr, int value) {
		int start = 0, end = arr.length - 1, mid;

		while (start < end) {
			mid = (start + end) >>> 1;
			if (arr[mid] >= value)
				end = mid;
			else
				start = mid + 1;
		}
		return arr[start] < value ? arr.length : start;
	}

	private static int findUpperBound(int[] arr, int value) {
		int start = 0, end = arr.length - 1, mid;

		while (start < end) {
			mid = (start + end) >>> 1;
			if (arr[mid] <= value)
				start = mid + 1;
			else
				end = mid;
		}
		return arr[start] <= value ? arr.length : start;
	}

	private static long meetInTheMiddle(int n, int s) {
		long res = 0;
		int[] x, y;

		x = new int[1 << (n >>> 1)];
		y = new int[1 << n - (n >>> 1)];
		calculateSubArray(0, n >>> 1, x);
		calculateSubArray(n >>> 1, n, y);
		Arrays.sort(y);
		for (int val : x)
			res += findUpperBound(y, s - val) - findLowerBound(y, s - val);
		if (s == 0)
			res--;
		return res;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), s = readInt();

		seq = new int[n];
		for (int i = 0; i < n; i++)
			seq[i] = readInt();
		System.out.println(meetInTheMiddle(n, s));
	}
}