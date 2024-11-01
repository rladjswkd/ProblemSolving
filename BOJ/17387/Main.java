
/*
 * x1 < x2, x3 < x4라고 가정할 때, x1 ~ x2 범위 r1과 x3 ~ x4 범위 r2가 일단 겹쳐야 한다. 그렇지 않으면 y값들에 관계없이 무조건 두 선분이 교차하지 않는다.
 * -> x2 < x3 || x1 > x4면 0 반환
 * 물론 x1 < x2와 x3 < x4는 문제에서 보장하지 않는다.
 * 
 * 만약 두 선분이 겹친다면, 겹치는 x 범위 cx1 ~ cx2 내에서 두 선분의 y값 차, diff가 0이 되는 점이 있는지 확인한다.
 * -> cx1에서의 두 선분의 y값 차와 cx2에서의 두 선분의 y값 차의 부호가 바뀌면 양 끝점이 아닌 중간 지점에서 교점이 발생
 * -> cx1, cx2 중 하나라도 두 선분의 차가 0이라면 양 끝점에서 교점이 발생
 * 이를 위해선 직선 방정식을 활용해야 한다.
 * 
 * 만약 둘 중 한 선분이라도 y축에 평행한 선분이라면 직선 방정식을 활용할 수 없다.
 * 1. 둘 다 y축에 평행 -> x값이 같고 y의 범위가 겹칠 때만 교차한다.
 * 2. 하나만 y축에 평행 -> y축에 평행하지 않은 나머지 선분의 y값이 y축에 평행한 선분의 x값을 지날 때 y축에 평행한 선분의 y 범위 내에 포함되는지 확인
 * 
 * ---
 * 아래 테스트케이스에 대해 오답을 출력한다.
 * 1 39 6 11
 * 6 11 17 58
 * 답: 1
 * 출력: 0
 * 
 * 부동 소수점의 연산 결과가 0일 때, 정확히 0이 나오지 않아서 생기는 문제.
 * -> 소수점 아래 몇 자리까지 0이면 0으로 판단하는 로직이 필요하다. -> 찾아보니 다른 사람들은 eps = 1e-9 활용. 왜 1e-9를 사용하는지는 아직 이유 모름.
 */
import java.io.IOException;

public class Main {
	private static final double eps = 1e-9;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			n = ~n + 1;
		return n;
	}

	public static void main(String[] args) throws IOException {
		int x1 = readInt(), y1 = readInt(), x2 = readInt(), y2 = readInt(),
				x3 = readInt(), y3 = readInt(), x4 = readInt(), y4 = readInt(),
				swap, cx1, cx2, res = 0;
		double s1, s2, i1, i2, d1, d2, yAt;

		// x값이 더 작은 점을 x1, y1으로 설정한다.
		if (x2 < x1) {
			swap = x1;
			x1 = x2;
			x2 = swap;
			swap = y1;
			y1 = y2;
			y2 = swap;
		}
		// x값이 더 작은 점을 x3, y3로 설정한다.
		if (x4 < x3) {
			swap = x3;
			x3 = x4;
			x4 = swap;
			swap = y3;
			y3 = y4;
			y4 = swap;
		}
		// 두 선분의 x 범위가 전혀 겹치지 않는다면 0 반환하며, 두 선분의 x 범위가 양 끝점이라도 겹친다면 y 값 체크
		// 두 선분 중 하나라도 y축에 평행할 때도 결국 x의 범위가 겹쳐야 교점이 발생하므로 이 조건문 내에서 확인할 수 있다.
		if (!(x2 < x3 || x4 < x1)) {
			// 둘 다 y축에 평행(이미 두 선분은 공통된 x 범위를 갖는다는 걸 확인했으므로 x2 == x3를 확인할 필요는 없다.)
			// y의 범위가 겹칠 때만 교차한다.
			if (x1 == x2 && x3 == x4) {
				if (y1 <= y3 && y3 <= y2 || y1 <= y4 && y4 <= y2)
					res = 1;
			}
			// x1, x2에 대한 선분만 y축에 평행. y3, y4의 범위가 y1또는 y2를 포함하는지 확인하는 식으로는 안된다.
			// (x3, y3), (x4, y4)를 잇는 선분이 x1==x2 점에서 y1보다 아래를 지나서 나중엔 y1보다 위로 올라갈 수도 있기
			// 때문이다.
			// 결국 이 지점에선 두 번째 선분의 x1==x2 점에서의 y값을 확인하기 위해 (x3, y3), (x4, y4)를 잇는 직선의 방정식이
			// 필요하다.
			else if (x1 == x2) {
				s2 = (double) (y4 - y3) / (x4 - x3);
				i2 = y3 - s2 * x3;
				yAt = s2 * x1 + i2;
				if (y1 <= yAt && yAt <= y2)
					res = 1;
			}
			// x3, x4에 대한 선분만 y축에 평행.
			else if (x3 == x4) {
				s1 = (double) (y2 - y1) / (x2 - x1);
				i1 = y1 - s1 * x1;
				yAt = s1 * x3 + i1;
				if (y3 <= yAt && yAt <= y4)
					res = 1;
			}
			// 두 선분 모두 y축에 평행하지 않을 때. x축에 평행한 선분은 0인 기울기를 가질 수 있으므로 문제 없다.
			else {
				// (x1, y1), (x2, y2)를 잇는 선분의 직선 방정식. s1, s2는 기울기를 나타내고, i1, i2는 y 절편을 나타낸다.
				s1 = (double) (y2 - y1) / (x2 - x1);
				i1 = y1 - s1 * x1;
				s2 = (double) (y4 - y3) / (x4 - x3);
				i2 = y3 - s2 * x3;
				// cx1, cx2는 두 선분이 겹치는 x 범위의 시작과 끝을 나타낸다.
				cx1 = Math.max(x1, x3);
				cx2 = Math.min(x2, x4);
				// cx1에서의 두 선분의 y값 차이
				d1 = (s1 * cx1 + i1) - (s2 * cx1 + i2);
				// cx2에서의 두 선분의 y값 차이
				d2 = (s1 * cx2 + i1) - (s2 * cx2 + i2);
				// d1과 d2의 부호가 바뀌거나, d1이 0, d2가 0이면 두 선분이 교차함을 알 수 있다.
				if (d1 < 0 && d2 > 0 || d1 > 0 && d2 < 0 || Math.abs(d1) < eps || Math.abs(d2) < eps)
					res = 1;
			}
		}
		System.out.println(res);
	}
}