
/*
 * x행부터 시작하게 하기
 * count[len]이 5보다 작은지를 반복문의 시작에서 체크
 */
import java.io.IOException;

public class Main {
	// 각 행을 하나의 int로 표현하고, 각 열은 int의 0 ~ 10번째 bit를 사용. 비트마스킹
	private static int[] paper = new int[10];
	// one ~ five : 각각 이미 사용한 1x1, ..., 5x5 색종이의 수
	private static int[] count = new int[5];
	// 불가능한 색종이 수의 총합으로 초기화 후 최소값으로 업데이트
	private static int res = 26;

	private static void solve(int total) {
		int mask, lenLimit;
		boolean valid = true;

		// 현재 색종이의 모든 칸이 0이라면 res 업데이트 후 리턴
		if (total == 0) {
			res = Math.min(res, count[0] + count[1] + count[2] + count[3] + count[4]);
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (paper[i] == 0)
				continue;
			for (int j = 0; j < 10; j++) {
				// 현재 칸이 0이면 아무 동작도 하지 않고 다음 칸 확인
				if ((paper[i] & 1 << j) == 0)
					continue;
				// 현재 칸이 1일 때 수행
				mask = 0;
				lenLimit = Math.min(5, 10 - i);
				// 현재 칸을 1x1 ~ 5x5의 색종이로 덮기 시도
				// valid가 axa에서 valid가 false가 되었다면 (a+1)x(a+1)도 당연히 불가능하다.
				for (int len = 0; len < lenLimit && valid; len++) {
					// 비트마스킹.
					// 순서대로 0, 01, 012, 0123, 01234와 같이 0 ~ 4 인덱스의 비트를 채운 후 이를 j ~ j + 5 인덱스로 시프트
					mask |= (1 << len) << j;
					if (count[len] == 5)
						continue;
					// len*len 크기의 부분이 모두 1로 설정되어있는지 확인
					valid = true;
					for (int k = 0; k <= len; k++) {
						if ((paper[i + k] & mask) != mask) {
							valid = false;
							break;
						}
					}
					// len*len 크기의 부분이 모두 1로 설정되어있다면 현재 색종이로 덮어 0으로 만든 후, 재귀 호출.
					// 이후 다시 색종이를 떼 부분을 모두 1로 되돌려 다음 동작을 준비
					// len*len 길이의 색종이를 5개 이하로 사용할 때만 재귀호출
					if (valid) {
						for (int k = 0; k <= len; k++)
							paper[i + k] ^= mask;
						count[len]++;
						solve(total - mask * (len + 1));
						for (int k = 0; k <= len; k++)
							paper[i + k] |= mask;
						count[len]--;
					}
				}
				return;
			}
			// 현재 행에 대해 색종이를 붙이는 과정을 고려했음에도 현재 행에 값이 1인 칸들이 남아있다면
			// 가지치기
			if (paper[i] != 0)
				return;
		}
	}

	public static void main(String[] args) throws IOException {
		int total = 0;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if ((System.in.read() & 15) == 1)
					paper[i] |= 1 << j;
				System.in.read();
			}
			total += paper[i];
		}
		solve(total);
		System.out.println(res == 26 ? -1 : res);
	}
}