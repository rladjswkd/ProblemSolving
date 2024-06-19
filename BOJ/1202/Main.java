
/*
 * 보석을 가격 내림차순으로 정렬, 가방을 무게 오름차순으로 정렬.
 * 보석을 높은 가격부터 순회하면서 보석을 담을 수 있는 lower bound 가방을 찾기
 * 
 * 보석과 가방 정렬 -> 2 * 300000 log(300000)
 * 보석을 순회하며 각 보석에 대해 lower bound 가방 찾기 -> 300000 * log(300000)
 * 
 * 3 * 300000 log(300000) =~ 493만 개의 연산
 * 
 * 그리디?
 * 
 * 이 문제가 그리디 문제인 이유는 가방에 보석을 하나만 넣을 수 있기 때문이다.
 * 다이나믹 프로그래밍 knapsack 문제는 가방에 무게만큼 여러 개의 물건을 넣을 수 있기 때문에 그리디로 풀 수 없는 문제다.
 * 예를 들어 (무게, 가치) 쌍이 다음과 같은 보석들이 있다.
 * (100, 1000), (49, 500), (40, 501)
 * 이때 가방이 담을 수 있는 무게가 100이라면 이를 그리디로 풀었을 땐 보석들 중 가장 가치있는 보석을 골라 담을 것이고, (100, 1000) 보석이 담길 것이다.
 * 하지만 (49, 500)과 (40, 501) 보석을 담으면 1001의 가치를 담을 수 있다.
 * 
 * 하지만 가방에 보석을 하나만 담을 수 있게 된다면 (100, 1000)을 담는 것이 맞다.
 * 따라서 이 문제가 그리디인 것이다.
 * 
 * ---
 * 
 * 위 방식에서 추가로 고려할 점은, 가방엔 보석을 하나만 담을 수 있기 때문에, lower bound로 한 번 찾은 가방은 다시 사용할 수 없다는 점이다.
 * -> 정렬한 가방 배열에서 그 가방을 빼줘야 하는데 연결리스트를 사용해야 하려나.... 하지만 연결리스트를 사용하면 탐색이 O(1)이 아닌 O(n)이라서...
 * 
 * ---
 * 
 * 보석과 가방 모두 무게 기준으로 정렬하고(오름차순이든 내림차순이든 상관 없이 둘이 같은 기준으로 정렬하기만 하면 된다.)
 * 둘 다 순회하면서 보석을 가방에 넣을 수 있는 첫 번째 보석-가방 쌍을 바로 선택한다.
 * 예를 들어, 둘 다 오름차순 정렬일 때 x 번째 보석을 y 번째 가방에 넣었다고 하자.
 * x + 1번째 보석은 x번째 보석과 무게가 같거나 보다 무거울 것이므로 0 ~ y 번째 가방에 넣는 것은 고려하지 않아도 된다.
 * 따라서 y + 1 번째 가방부터 검사해 x + 1 번째 보석을 넣을 수 있는 무게 용량을 가진 첫 번째 가방이 등장하면, 바로 그 가방에 x + 1번째 보석을 넣는다.
 * -> 보석이 두 개가 있고 무게, 값 쌍이 (1, 10), (2, 100)이고 가방이 1개가 있으며 무게 용량이 2라 해보자.
 * 이 로직대로면 (1, 10)짜리 보석을 넣게 되고, 10을 출력할 것이다. 땡!
 * 
 * ---
 * 
 * 정답을 도출하는 시간 비효율적인 방법
 * 보석은 가치 기준 내림차순, 가방은 무게 기준 오름차순
 * 보석을 순회:
 * 	가방을 순회:
 * 		현재 가방이 이전에 사용되지 않았으며 현재 보석을 담을 수 있다면:
 * 			이 가방에 보석을 담고 가방을 사용 처리
 * 
 * ---
 * 
 * 보석을 무게 기준 오름차순, 가방도 무게 기준 오름차순
 * -> 가방을 순회하면서 현재 가방의 무게 용량 내에 해당하는 보석들을 우선순위 큐에 넣고 그 중 가치가 가장 큰 걸 pop해 가방에 넣기
 * 1. 우선순위 큐가 비었다면 -> continue;
 * 
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	private static List<int[]> jewels = new ArrayList<>();
	private static int[] knapsacks;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), k = readInt(), idx = 0;
		long res = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

		for (int i = 0; i < n; i++)
			jewels.add(new int[] { readInt(), readInt() });
		Collections.sort(jewels, (a, b) -> a[0] - b[0]);
		knapsacks = new int[k];
		for (int i = 0; i < k; i++)
			knapsacks[i] = readInt();
		Arrays.sort(knapsacks);
		for (int knapsack : knapsacks) {
			for (; idx < jewels.size() && jewels.get(idx)[0] <= knapsack; idx++)
				pq.add(jewels.get(idx));
			if (!pq.isEmpty())
				res += pq.poll()[1];
		}
		System.out.println(res);
	}
}