
/*
 * "고층 빌딩 A에서 다른 고층 빌딩 B가 볼 수 있는 빌딩이 되려면, 두 지붕을 잇는 선분이 A와 B를 제외한 다른 고층 빌딩을 지나거나 접하지 않아야 한다."
 * 
 * A와 B의 두 지붕을 잇는 선분을 y = ax + b라고 하자.
 * 
 * A와 B 사이에 있는 고층 빌딩들의 인덱스 i를 x에 대입했을 때, 그 값이 고층 빌딩들의 높이보다 커야 한다.
 * (building[i] < ai + b)
 * 
 * 
 */
import java.io.IOException;

public class Main {
	private static int[] visibleBuildingCounts;
	private static int res = 0;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int intermediate;
		double slope, yIntercept;
		int[] heights = new int[n];

		visibleBuildingCounts = new int[n];
		for (int i = 0; i < n; i++)
			heights[i] = readInt();
		for (int benchmark = 0; benchmark < n; benchmark++) {
			for (int target = benchmark + 1; target < n; target++) {
				slope = (heights[target] - heights[benchmark]) / (double) (target - benchmark);
				yIntercept = heights[target] - slope * target;
				for (intermediate = benchmark + 1; intermediate < target; intermediate++)
					if (slope * intermediate + yIntercept <= heights[intermediate])
						break;
				if (intermediate == target) {
					visibleBuildingCounts[benchmark]++;
					visibleBuildingCounts[target]++;
				}
			}
			res = Math.max(res, visibleBuildingCounts[benchmark]);
		}
		System.out.println(res);
	}
}