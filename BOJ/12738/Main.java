
/*
 * LIS -> dp, binary search
 * 하지만 수열의 크기가 최대 백만이므로, dp로 풀면 이차원 배열의 크기도 문제고, 1조 개의 연산을 수행해야 하므로 시간 초과다.
 * 
 * Binary Search
 * 
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int n;
	private static List<Integer> lis;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			return ~n + 1;
		return n;
	}

	private static void setInLowerBound(int target) {
		int start = 0, end = lis.size() - 1, mid, value;

		while (start < end) {
			mid = (start + end) >>> 1;
			value = lis.get(mid);
			if (value < target)
				start = mid + 1;
			else
				end = mid;
		}
		if (lis.isEmpty() || lis.get(start) < target)
			lis.add(target);
		else
			lis.set(start, target);
	}

	public static void main(String[] args) throws IOException {
		n = readInt();
		lis = new ArrayList<>();
		for (int i = 0; i < n; i++)
			setInLowerBound(readInt());
		System.out.println(lis.size());
	}
}