### java8 기준 메모리 565188KB, 시간 628ms

```java
import java.io.IOException;

public class Main {
	private static int[] ds, candies;
	// 분리 집합의 수를 저장할 변수
	private static int setCount;
	private static int[][] dp;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int find(int node) {
		int root = node, upper;

		while (ds[root] >= 0)
			root = ds[root];
		while (ds[node] >= 0) {
			upper = ds[node];
			ds[node] = root;
			node = upper;
		}
		return root;
	}

	private static void union(int u, int v) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return;
		else if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
			// v가 포함된 분리 집합이 u가 포함된 분리 집합에 합쳐졌으므로, u가 포함된 분리 집합의 루트에 해당하는 인덱스의 candies 값에 v가
			// 포함된 분리 집합의 사탕 전부(candies[vRoot])를 더한다.
			candies[uRoot] += candies[vRoot];
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
			candies[vRoot] += candies[uRoot];
		}
		// 두 분리 집합이 하나로 합쳐졌다면 루트의 숫자를 하나 줄인다.
		setCount--;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), m = readInt(), k = readInt(), index = 0;

		ds = new int[n];
		candies = new int[n];
		for (int i = 0; i < n; i++) {
			ds[i] = -1;
			candies[i] = readInt();
		}
		setCount = n;
		// 사탕을 뺏은 아이의 친구의 사탕도 뺏어야 하므로 친구의 친구의 친구의... 친구를 모두 하나의 분리 집합에 포함시킨다.
		for (int i = 0; i < m; i++)
			union(readInt() - 1, readInt() - 1);
		// 여기서부턴 DP
		// ds와 candies를 분리 집합을 표현할 배열로 재활용한다.
		// 현재 인덱스가 분리 집합을 대표하는 루트라면 ds와 candies의 값을 index에 저장해, 결론적으론 ds와 candies의 앞
		// setCount개의 원소가 모두 분리 집합의 루트에 해당하는 값들로 구성되게 하기
		for (int i = 0; i < n; i++) {
			if (ds[i] < 0) {
				ds[index] = -ds[i];
				candies[index++] = candies[i];
			}
		}
		dp = new int[setCount + 1][k];
		// i는 setCount개의 각 분리 집합에 대한 인덱스고, j는 k 미만의 아이들의 수를 1부터 순서대로 나타내는 인덱스다.
		for (int i = 1; i <= setCount; i++) {
			for (int j = 1; j < k; j++) {
				if (j < ds[i - 1])
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = Math.max(dp[i - 1][j], candies[i - 1] + dp[i - 1][j - ds[i - 1]]);
			}
		}
		System.out.println(dp[setCount][k - 1]);
	}
}
```
