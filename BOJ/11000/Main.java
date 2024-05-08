/*
 * 입력을 아예 우선순위 큐로 받으면 입력이 적절하게 정렬되지 않았을 때 틀린 결과가 나온다. 
 * 예를 들어 다음과 같은 반례가 있다. 
 * 3 
 * 5 6 
 * 3 4 
 * 1 2 
 * 답은 1이어야 하지만, 출력은 3이 된다. 
 * 
 * 또한, 입력받은 강의를 정렬할 때, 종료 시간(t)를 기준으로 정렬하면 틀리는 이유에 대해선 아래 예제를 보자.
 * 4
 * 1 2
 * 1 4
 * 2 6
 * 4 5
 * 
 * 이를 종료 시간을 기준으로 정렬하면 (1 2), (1 4), (4 5), (2 6)이 된다.
 * 이 상황에서 반복문을 돌며 우선순위 큐를 이용해 연산을 수행하면 아래와 같이 전개된다(맨 앞 원소를 제외한 나열 순서는 실제와 다를 수 있다).
 * 
 * (1 2) -> 1
 * (1 2), (1 4) -> 2
 * (1 4), (4 5) -> 2
 * (1 4), (4 5), (2 6) -> 3
 * 
 * 하지만 실제 답은 아래와 같다.
 * 
 * (1 2) -> 1
 * (1 2), (1 4) -> 2
 * (1 4), (2 6) -> 2
 * (4 5), (2 6) -> 2
 * 
 * 따라서 애초에 입력받은 강의 정보가 (1 2), (1 4), (2 6), (4 5)와 같이 정렬되어야 함을 알 수 있다.
 * 
 * 입력받은 배열을 정렬해 사용하면 우선순위 큐를 사용할 필요가 없다?
 * 애초에 우선순위 큐를 사용해야겠다 생각한 이유?
 * 	처음엔 정렬 대신 강의 정보를 하나 하나 입력받으며 우선순위 큐에 추가하려 했었지만, 2번 줄의 주석을 이유로 실패
 * 	그 이후엔 "먼저 끝나는" 강의에 사용한 강의실을 새로운 강의에 사용하기 위해 사용.
 * 
 * 그렇다면 현재 강의실을 이용중인 강의 중 먼저 끝나는 강의를 구분할 필요가 없다?
 * 6
 * 1 10
 * 2 9
 * 3 8
 * 6 7
 * 5 6
 * 
 * 답: 5 - 1 10, 2 9, 3 8, 4 7, (5 6 -> 6 7)
 * 
 * 이걸 그냥 큐를 사용하면 아래와 같이 전개된다.
 * (1 10) -> 1
 * (1 10), (2 9) -> 2
 * (1 10), (2 9), (3 8) -> 3
 * (1 10), (2 9), (3 8), (4 7) -> 4
 * (1 10), (2 9), (3 8), (4 7), (5 6) -> 5
 * (1 10), (2 9), (3 8), (4 7), (5 6), (6 7) -> 6
 * 
 * 따라서, "먼저 끝나는" 강의를 구분할 필요가 있고! 이를 위해선 우선순위 큐를 사용하는 게 수월하다.
 * 그냥 큐를 사용해서도 직접 정렬을 하든 반복문을 돌든 방법이야 있겠지만.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int[][] lectures = new int[n][];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

		for (int i = 0; i < n; i++)
			lectures[i] = new int[] { readInt(), readInt() };
		Arrays.sort(lectures, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		pq.add(lectures[0]);
		for (int i = 1; i < lectures.length; i++) {
			if (pq.peek()[1] <= lectures[i][0])
				pq.remove();
			pq.add(lectures[i]);
		}
		bw.append(String.valueOf(pq.size())).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}