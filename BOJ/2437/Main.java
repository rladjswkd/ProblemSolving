
/*
 * 각 저울추를 선택하거나 하지 않았을 때 모든 경우의 수를 다 구해 더하면 최대 2^1000 개의 연산을 해야 한다. -> 시간 초과
 * 
 * 1 1 2 3 6 7 30
 * 
 * 1 -> 1
 * 1 -> 1, 2
 * 2 -> 1, 2, 3, 4
 * 3 -> 1, 2, 3, 4, 5, 6, 7
 * 6 -> 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
 * 7 -> 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
 * 30 -> 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
 * 31, 32, ... 50
 * 
 * i 번째 저울추까지 더한 값이 X이며 i번째 저울추까지 활용했을 때 1 ~ X를 모두 측정할 수 있다고 가정하자.
 * i + 1 번째 저울추가 Y라고 하면, Y, Y + 1, ... , Y + X까지를 추가로 표현할 수 있게 된다.
 * 
 * 따라서 Y가 X + 1보다 작거나 같으면 중간에 측정할 수 있는 값이 끊어지지 않는 것이고, Y + X까지 표현할 수 있게 된다.
 * 이땐 i + 2 번째 저울추에 대해 같은 과정을 반복하면 된다.
 * 하지만 Y가 X + 1보다 크다면 X + 1이 우리가 찾는 측정할 수 없는 무게가 된다.
 * 
 * 이 과정을 위해 저울추는 당연히 오름차순으로 정렬해야 한다.
 * 
 */
import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	/*
	 * acc를 1로 초기화
	 * 1. 가장 작은 추의 무게가 1부터 시작할 때: 측정할 수 없는 무게에 도달할 때까지 아무 문제 없이 진행되지만, acc가 표현하는 값이
	 * X가 아닌 X + 1이 된다.
	 * 2. 가장 작은 추의 무게가 1보다 큰 수에서 시작할 때: 반복문에서 바로 빠져나오고 1을 출력하게 된다.
	 * => 1로 초기화해도 그에 맞게 코드를 짜면 문제 없음!
	 */
	public static void main(String[] args) throws IOException {
		int n = readInt(), acc = 1;
		int[] arr = new int[n];

		for (int i = 0; i < n; i++)
			arr[i] = readInt();
		Arrays.sort(arr);
		for (int weight : arr) {
			if (acc < weight)
				break;
			acc += weight;
		}
		System.out.println(acc);
	}
}