
/*
 * 여러 작업을 동시에 처리할 수 있다는 점이 중요하다.
 * 
 * 각 작업을 시작 시각, 종료 시각 쌍으로 계산
 * 
 * dp인가..?
 * 
 * 위상 정렬
 * 1. 입력을 받아 DAG 구성
 * 2. DAG 내의 "선행 관계에 있는 작업이 하나도 없는 작업"들에 대해 DFS 호출
 * 3. DFS는 인자로 받은 시작 시각과 자신의 작업 수행 시각을 더해 다음 노드에 대해 DFS를 재귀 호출한다.
 * 4. 이후에 수행할 작업이 없는 노드에선 인자로 받은 시작 시각 + 자신의 작업 수행 시각과 그때까지 검사한 모든 노드들의 그 값 중 큰 값을 main()에서 활용할 수 있게 별도로 저장한다.
 * 참고. 각 DFS 호출에서 작업이 끝난 노드를 연결리스트의 앞에 추가한 뒤 모든 DFS가 종료되면 연결리스트에 작업이 수행할 순서대로 정렬되어있다.
 * 물론 이 순서가 유일한 순서인 것은 아니다.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

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
		int preTaskCount, taskDuration, latestPreTaskEndTime, res = 0;
		int[] taskEndTimes = new int[readInt()];

		for (int i = 0; i < taskEndTimes.length; i++) {
			latestPreTaskEndTime = 0;
			taskDuration = readInt();
			preTaskCount = readInt();
			while (preTaskCount-- > 0)
				latestPreTaskEndTime = Math.max(latestPreTaskEndTime, taskEndTimes[readInt() - 1]);
			taskEndTimes[i] = latestPreTaskEndTime + taskDuration;
			res = Math.max(taskEndTimes[i], res);
		}
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}
