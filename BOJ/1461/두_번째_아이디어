
/*
 * 남은 모든 책을 계속 다음 위치로 이동시키는 방식이 아닌 약간 다른 접근 방식이다.
 * 
 * 음수와 양수 위치들 중 최장 거리가 더 짧은 쪽을 먼저 방문하는 것은 같다.
 * 다른 점은, 책을 한꺼번에 이동시키는 것이 아니라는 점이다.
 * 
 * 최장 거리 위치에서 정확하게 끝나면서도 """"원점으로 복귀해 책을 갖고 오는 위치와 원점 간의 거리가 짧은 것들을 먼저 선택하는 방식""""이다.
 * 
 * 예제 1
 * 7 2
 * -37 2 -6 -39 -29 11 -28 -> -39 -37 -29 -28 -6 2 11
 * 
 * -39와 11중 절댓값이 큰 건 -39이므로 양수 부분을 먼저 방문한다.
 * 책 두 권을 들고 2, 11에 원위치 시킨 후 종료한다.
 * -> 양수 부분만 있다면 여기까지만 수행하지만, 음수 부분이 남아있으므로 원점으로 복귀해 이후 음수부분을 고려해야 한다.
 * 
 * 책을 2권씩 옮길 수 있으므로 -39, -37은 한 번에 원위치하고, -29, -28도 한 번에 원위치한다.
 * 그러면 수중에 책이 0권인 지점은 -39, -29, -6이어야 하고 그 중 원점으로 복귀해서 책을 다시 가져와야 하는 위치는 -29, -6이다.
 * 
 * 즉, 다음 과정으로 수행할 수 있다.
 * 1. 원점에서 책을 들고 -6 지점에 원위치 시킨후 더 이상 책이 없으므로 다시 원점으로 돌아온다. -> 6 * 2
 * 2. 원점에서 책을 들고 -28, -29 지점에 원위치 시킨 후 더 이상 책이 없으므로 다시 원점으로 돌아온다. -> 29 * 2
 * 3. 원점에서 책을 들고 -39, -37 지점에 원위치 시킨 후 종료한다.
 * 
 * 이를 위해 음수 정렬시 인덱스와 값을 아래와 같이 구성할 수 있다.
 * 	0		1		2		3		4
 * 	-39	-37	-29	-28	-6
 * 
 * 그러면 인덱스 % 한 번에 옮길 수 있는 책의 수 == 0인 위치들에서 원점으로 복귀해 책을 다시 가져오면 된다. -> (결과 += 그 위치 값 * 2)
 * 나머지 지점들은 위 지점들을 이동하면서 거쳐가게 되므로 별도로 결괏값을 업데이트하지 않아도 된다.
 * 
 * 인덱스를 반대로 하면 어떻게 될까? (정렬의 편리함을 위해 양수로 만들어서 정렬)
 *  0		1		2		3		4
 * 	6		28	29	37	39
 * 
 * 한 번에 옮길 수 있는 책이 2권일 땐 문제가 없다. 하지만 3권일 땐 원점으로 복귀해 책을 다시 가져와야 할 인덱스가 1로, 3으로 나누어 떨어지지 않는다.
 * 이 방식을 사용하고 싶다면 ((마지막 인덱스 - 현재 인덱스) % 한 번에 옮길 수 있는 책의 수 == 0)인 인덱스를 사용해야 한다.
 * 
 * 참고로 ((리스트 길이 + 현재 인덱스) % 한 번에 옮길 수 있는 책의 수 == 0)는 안된다.
 * 길이가 6이고 한 번에 옮길 수 있는 책이 4권이라 하면, 인덱스 1에서 원점으로 복귀해 책을 가져와야하는데, 그러지 못한다.
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	// capacity: 한 번에 옮길 수 있는 책의 개수
	// negatives: 음수 위치에 정리해야 할 책들
	// positives: 양수 위치에 정리해야 할 책들
	private static int capacity, res = 0;
	private static List<Integer> negatives, positives;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign - 48;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			n = ~n + 1;
		return n;
	}

	// 책의 위치는 0이 아니다 -> 책의 위치는 음수와 양수 뿐이다.
	public static void main(String[] args) throws IOException {
		int bookCount = readInt(), position, last, negDist, posDist;

		capacity = readInt();
		negatives = new ArrayList<>();
		positives = new ArrayList<>();
		for (int i = 0; i < bookCount; i++) {
			position = readInt();
			if (position > 0)
				positives.add(position);
			else
				negatives.add(-position);
		}
		Collections.sort(positives);
		Collections.sort(negatives);
		// 음수 부분 책 위치 제자리로
		last = negatives.size() - 1;
		for (int i = 0; i < negatives.size(); i++)
			if ((last - i) % capacity == 0)
				res += 2 * negatives.get(i);
		// 양수 부분 책 위치 제자리로
		last = positives.size() - 1;
		for (int i = 0; i < positives.size(); i++)
			if ((last - i) % capacity == 0)
				res += 2 * positives.get(i);
		// 음수 부분이든 양수 부분이든 둘의 원점으로부터 최장 거리 중 상대적으로 긴 위치에선 다시 원점으로 돌아올 필요가 없지만,
		// 위의 두 반목문에서 이를 반영한 상황이기 때문에 이를 다시 빼준다.
		negDist = negatives.isEmpty() ? 0 : negatives.get(negatives.size() - 1);
		posDist = positives.isEmpty() ? 0 : positives.get(positives.size() - 1);
		res -= Math.max(negDist, posDist);
		System.out.println(res);
	}
}