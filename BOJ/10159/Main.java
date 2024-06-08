
/*
 * 그래프 경로 찾기
 * 
 * -> 자신한테 도달할 수 있는 노드의 수(자기보다 무거운 물건) + 자신으로부터 도달할 수 있는 노드의 수(자기보다 가벼운 물건) = 물건의 수 - 1이면 된다.
 */
import java.io.IOException;

public class Main {
	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int itemCount = readInt(), compCount = readInt(), count;
		boolean[][] graph = new boolean[itemCount][itemCount];
		StringBuilder sb = new StringBuilder();
		// 그래프 초기화
		for (int i = 0; i < itemCount; i++)
			graph[i][i] = true;
		for (int i = 0; i < compCount; i++)
			graph[readInt() - 1][readInt() - 1] = true;
		// 플로이드-워셜
		for (int via = 0; via < itemCount; via++)
			for (int start = 0; start < itemCount; start++)
				for (int end = 0; end < itemCount; end++)
					graph[start][end] = graph[start][end] || (graph[start][via] && graph[via][end]);
		// 그래프 배열에서 false인 개수 세기
		for (int i = 0; i < itemCount; i++) {
			count = 0;
			for (int j = 0; j < itemCount; j++)
				if (!graph[i][j] && !graph[j][i])
					count++;
			sb.append(count).append('\n');
		}
		System.out.print(sb.toString());
	}
}