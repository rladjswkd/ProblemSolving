
/* 
 * 키						가능한 경우
 * 1						1
 * 
 * 2						1+1
 * 
 * 3						1+1+1
 * 4						1+2+1
 * 
 * 5						1+2+1+1
 * 6						1+2+2+1
 * 
 * 7						1+2+2+1+1
 * 8						1+2+2+2+1
 * 9						1+2+3+2+1
 * 
 * 10						1+2+3+2+1+1
 * 11						1+2+3+2+2+1
 * 12						1+2+3+3+2+1
 * 
 * 13						1+2+3+3+2+2+1
 * 14						1+2+3+3+3+2+1
 * 15						1+2+3+3+3+2+1
 * 16						1+2+3+4+3+2+1
 * 
 * 17						1+2+3+4+3+2+1+1
 * ...
 * 24						1+2+3+4+4+4+3+2+1
 * 25						1+2+3+4+5+4+3+2+1
 * 26						1+2+3+4+5+4+3+2+1+1
 *
 * 경우의 수를 보면 키가 제곱수(4, 9, 16, ...)일 때 키의 제곱근을 중심으로 좌우 대칭인 걸 알 수 있다.
 * 또한 제곱수 자신을 포함해 위로 제곱근 값 만큼 키가 같고, 제곱수 다음 수부터 제곱근 값 만큼 키가 같다.
 * 	키 4를 포함해 위로 4의 제곱근인 2칸만큼이면 키 3, 4에 해당한다.
 * 	이 둘의 키는 3으로 같다.
 * 	다음으로 키 5부터 2칸만큼이면 키 5, 6에 해당하며, 이 둘의 이동횟수는 4로 같다.
 * 
 * 위로 제곱근 값 만큼의 키들은 키가 (제곱근 값 * 2 - 1) 만큼이고
 * 아래로 제곱근 값 만큼의 키들은 이도 횟수가 (제곱근 값 * 2)다.
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
		int diff, sqrt, res = 0;

		// 원숭이와 강아지의 키가 같을 수 있다.
		diff = -readInt() + readInt();
		if (diff > 0) {
			sqrt = (int) Math.ceil(Math.sqrt(diff));
			if ((long) Math.pow(sqrt, 2) - diff < sqrt)
				res = sqrt * 2 - 1;
			else
				res = (sqrt - 1) * 2;
		}
		System.out.println(res);
	}
}
