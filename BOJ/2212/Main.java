
/*
 * 1. 정렬
 * 
 * 집중국이 하나일 때 중위값이 최선의 선택인가?
 * 1 2 100
 * 	1 -> 1 + 99
 * 	2 -> 1 + 98
 * 	100 -> 99 + 98
 * 이 예제에선 그렇다.
 * 
 * 1 2 3 50 51
 * 	2 -> 1 + 1 + 48 + 49
 * 	3 -> 2 + 1 + 47 + 48
 * 	50 -> 49 + 48 + 47 + 1
 * 이 예제에서도 그렇다.
 * 
 * 1 3 6 7 9 9
 * 	6 -> 5 + 3 + 1 + 3 + 3
 * 	7 -> 6 + 4 + 1 + 2 + 2
 * 	9 -> 8 + 6 + 3 + 2
 * 이 예제에서도 그렇다.
 * 
 * 1 1 3 3 6 6 7 7 9 9 9 9
 * 	6 -> 5 + 5 + 3 + 3 + 1 + 1 + 3 + 3 + 3 + 3 = 30
 *  7 -> 6 + 6 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 2 = 30
 * 	9 -> 8 + 8 + 5 + 5 + 3 + 3 + 2 + 2 = 36
 * 이 예제에서도 그렇다.
 * 
 * 중위값이 두 개고 연속한 숫자들의 차이가 고르지 않을 때?
 * 1 6 7 15 54 108
 * 	7 -> 6 + 1 + 8 + 47 + 101 = 163
 * 	15 -> 14 + 9 + 8 + 39 + 93 = 163
 * 7을 고르면 15 ~ 108 범위의 숫자 간 차이에 15 - 7 만큼을 더하게 되고 대신 1 ~ 7 범위의 숫자 간 차이에 15 - 7 만큼을 빼게 된다.
 * 15를 고르면 1 ~ 7 범위의 숫자 간 차이에 15 - 7만큼을 더하게 되고 대신 15 ~ 108 범위의 숫자 간 차이에 15 - 7 만큼을 빼게 된다.
 * 결론적으로 붙어있는 두 중위값 중 무엇을 고르던지 같다.
 * 
 * 집중국이 두 개일 땐?
 * 1 2 100
 * 두 파트로 나누고 각 파트에서 중위값 선택?
 * 1 2, 100으로 나누고 1과 100에 설치하면 결과는 1
 * 
 * 예제를 보자.
 * 6
 * 2
 * 1 6 9 3 6 7
 * 답: 5
 * 
 * 1 3 6 6 7 9
 * 3, 7에 설치하면? X
 * 6에 설치하고 3에 설치하면? 2 + 1 + 2 -> 5
 * 
 * 7이 아닌 3에 설치할 기준?
 * 집중국이 3개라면, 3번째 집중국은 6, 7, 9의 중위값에 설치해야 하나 아니면 1, 3 또는 3, 6의 중위값에 설치해야 하나? 기준이 뭐야
 * 이런 방식이 아닌 거 같은데..
 * 
 * 1, 3, 6, 6, 7, 9의 각 센서 사이의 거리를 구하면 다음과 같다.
 * 2 3 0 1 2
 * 
 * 여기서 우리가 설치할 수 있는 집중국의 개수는 2개이므로 센서들을 두 개의 집합으로 분리할 수 있다.
 * -> 앞에서 구한 센서 사이의 거리 중 가장 큰 값을 이루는 두 센서를 기준으로 두 개의 집합으로 분리한다.
 * 
 * 세 개라면 그 다음을 분리하는 식으로 반복하는 그리디
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur, sign = br.read();

		if (sign != '-')
			n = sign - 48;
		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == '-')
			n = -n;
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), k = readInt(), res = 0;
		int[] sensors = new int[n];
		int[] dists = new int[n - 1];

		for (int i = 0; i < n; i++)
			sensors[i] = readInt();
		if (n > k) {
			Arrays.sort(sensors);
			for (int i = 0; i < dists.length; i++)
				dists[i] = sensors[i + 1] - sensors[i];
			Arrays.sort(dists);
			// 큰 값부터 k - 1개 제거한 나머지를 res에 누적
			for (int i = 0; i < dists.length - (k - 1); i++)
				res += dists[i];
		}
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}