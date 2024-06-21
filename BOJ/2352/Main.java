
/*
 * LIS 문제.
 * 왼쪽 포트 번호(오름차순)를 기준으로, 오른쪽 포트의 번호 중 오름차순인 부분 수열 중 가장 길이가 긴 것을 찾으면 된다.
 * 그래야 연결선끼리 겹치지 않는다.
 * 
 * 여기선 입력이 최대 40000개이므로 DP로는 풀기 어렵고 이분 탐색을 써야 한다.
 * 
 * 처음엔 왼쪽 포트 번호와 오른쪽 포트 번호의 차이가 작은 것을 우선 선택하는 그리디 문제인가 생각했었는데,
 * 차이가 작은 것을 먼저 선택하더라도 1-2, 2-1 과 같이 꼬일 수가 있다. 꼬이는지 다 확인하면서 푸는 문제는 아닌 것 같았다.
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static List<Integer> lis = new ArrayList<>();

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	// 오른쪽 포트 번호로 입력에서 같은 번호가 주어지진 않으니, lower bound를 써도되고 upper bound를 써도 된다.
	private static void addUpperBound(int target) {
		int start = 0, end = lis.size() - 1, mid;

		while (start < end) {
			mid = (start + end) >>> 1;
			if (lis.get(mid) <= target)
				start = mid + 1;
			else
				end = mid;
		}
		if (lis.isEmpty() || lis.get(start) <= target)
			lis.add(target);
		else
			lis.set(start, target);
	}

	public static void main(String[] args) throws IOException {
		int n = readInt();

		for (int i = 0; i < n; i++)
			addUpperBound(readInt());
		System.out.println(lis.size());
	}
}