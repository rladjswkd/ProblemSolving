
/*
 * 기존에 사용하던 방식대로 그냥 무조건 기존 lis의 값을 변경한다면, 실제 "증가하는 부분 수열"을 만족하지 않는다.
 * 
 * 어떻게 하면 실제 증가하는 부분 수열의 순서를 유지하면서 값을 반영할 수 있을까?
 * 
 * ---
 * 
 * 주어진 배열을 순회하며 각각의 값이 들어갈 수 있는 인덱스를 찾아서 저장한다.
 * 이 중 가장 큰 인덱스를 찾아 역순으로 순회하며 가장 큰 인덱스, 가장 큰 인덱스 - 1, 가장 큰 인덱스 - 2, ..., 1, 0을 찾아 그 값을 역순으로 뽑아내면 된다.
 * 
 * 역순으로 순회해야 하는 이유
 * 만약 주어진 배열이 10 20 30 50 1 2 3 4 5 6 7이라면, 역순으로 인덱스가 0일떄까지 순회할 경우 1 2 3 4 5 6 7을 스택에 담게 된다.
 * 하지만 정방향으로 순회할 경우 10 20 30 50 까지가 인덱스 0 ~ 3이므로 스택에 담게 되고, 이후 인덱스 4부터 5 6 7을 담게 된다.
 * 
 * 만약 10 20 30 50 1 2 3과 같이 주어졌을 때에도 역순으로 순회하는 것이 올바른 게, 어차피 가장 큰 인덱스는 3으로 숫자 50에 해당하므로, 뒤의 1 2 3은 고려하지 않는다.
 * 
 * ---
 * 
 * lis도, stack도 그냥 int[]로 사용하는 게 메모리, 시간 모두 더 효율적이다.
 */
import java.io.IOException;

public class Main {
	private static int[] lis, seq, indices;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (47 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			n = ~n + 1;
		return n;
	}

	private static int findLowerBound(int value, int lisLength) {
		int start = 0, end = lisLength - 1, mid;

		while (start < end) {
			mid = (start + end) >>> 1;
			if (lis[mid] < value)
				start = mid + 1;
			else
				end = mid;
		}
		return lis[start] >= value ? start : lisLength;
	}

	private static void findValidLIS(int lisIdx, int maxValueIdx) {
		for (int i = maxValueIdx; i >= 0 && lisIdx >= 0; i--) {
			if (indices[i] == lisIdx)
				lis[lisIdx--] = seq[i];
		}
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), maxValueIdx = 0, lisLength = 0;
		StringBuilder sb = new StringBuilder();

		// seq는 올바른 입력값들을 유지하기 위한 변수다.
		seq = new int[n];
		indices = new int[n];
		lis = new int[n];
		lis[lisLength++] = (seq[0] = readInt());
		for (int i = 1; i < n; i++) {
			if (indices[maxValueIdx] < (indices[i] = findLowerBound(seq[i] = readInt(), lisLength)))
				maxValueIdx = i;
			if (indices[i] == lisLength)
				lis[lisLength++] = seq[i];
			else
				lis[indices[i]] = seq[i];
		}
		findValidLIS(lisLength - 1, maxValueIdx);
		sb.append(lisLength).append('\n').append(lis[0]);
		for (int i = 1; i < lisLength; i++)
			sb.append(' ').append(lis[i]);
		System.out.println(sb.toString());
	}
}