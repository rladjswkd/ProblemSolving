
/*
 * DFS로 자기 자신한테 돌아오는지 확인하기
 * O(V * (V + E)) -> 시간 초과
 * 
 * 이전 학생에 대한 연산 결과를 저장해놓고, 이미 연산한 학생이면 이전의 연산 결과를 반환하는 식으로 하면 시간 단축이 되려나
 * -> 사이클에 포함이 되든지(프로젝트 팀 O) 안되든지(프로젝트 팀 X) 이미 처리한 학생에 다시 방문하면 프로젝트 팀을 생성하지 않는
 * 것.
 * 
 * 그런데, 예시의 4-6-7 사이클에서 4는 7을 원하고, 6은 7, 7은 6을 원하는 상황이라고 가정한다면 4에서 DFS를 호출해
 * 7 -> 6 순서로 DFS를 호출할텐데 4는 프로젝트 팀에 포함되지 않게 처리해야한다. 어떻게 할까?
 * 
 * ---
 * 
 * 방문하지 않은 학생은 재귀적으로 자신이 원하는 학생에 대해 DFS를 호출하며, 자신의 번호를 인자로 넘긴다.
 * 방문한 학생을 방문할 경우, 인자로 받은 "이 학생을 원하는 학생"이
 */
import java.io.IOException;

public class Main {
	private static int[] preferences = new int[100000];
	private static boolean[] processed;
	private static int notInTeamCount, count;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int findCycle(int student) {
		int ret;
		// main에서 각 findCycle이 처음 호출될 때의 processed[student]는 false다.
		// 따라서 processed[student]가 true라면, 처리한 student를 다시 방문한다는 뜻이고, 이는 두 가지를 나타낸다.
		// 1. 사이클이 존재함
		// 2. 이번 findCycle 재귀 호출로 처리한 것이 아닌 이전 findCycle 재귀 호출로 처리한 student에 방문함.
		// 이땐 사이클이 존재하지 않음
		// 어찌됐든, processed[student]가 true면 student를 반환한다.
		// 이는 사이클이 존재할 땐 dfs 호출 순서 상 사이클의 시작이자 마지막이 student임을 나타내고
		// 그렇지 않을 땐 단순히 방문을 한 학생에 대해 다시 방문했으므로 유효하지 않음을 나타낸다.
		if (processed[student])
			return student;
		// 방문 처리
		processed[student] = true;
		// 여기서 반환받는 값은 processed[student]가 true인 첫 번째 student다.
		ret = findCycle(preferences[student]);
		// 사이클이 존재함을 가정할 때, 사이클에 포함되는 학생의 수를 세기 위해 count를 1 증가시킨다.
		count++;
		// 현재 student가 반환받은 ret와 같다면, 이 findCycle에선 사이클의 시작인 student를 방문했음을 나타낸다.
		if (ret == student)
			notInTeamCount -= count;
		return ret;
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), n;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			notInTeamCount = n = readInt();
			processed = new boolean[n];
			for (int i = 0; i < n; i++)
				preferences[i] = readInt() - 1;
			for (int student = 0; student < n; student++) {
				if (!processed[student]) {
					count = 0;
					findCycle(student);
				}
			}
			sb.append(notInTeamCount).append('\n');
		}
		System.out.print(sb.toString());
	}
}