
/*
 * 투 포인터 방식
 * - 앞서 생성한 배열 X, Y 모두를 정렬한다.
 * - X, Y를 투 포인터를 이용해 탐색하며 두 값의 합이 0인 것들을 카운트한다.
 * 
 * ---
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!중요 참고!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 				X, Y를 생성하기 전에 a, b, c, d를 정렬한 후 생성하면, X, Y를 정렬할 때 걸리는 시간이 훨씬 줄어든다.      
 * 				a, b, c, d를 정렬하지 않고 X, Y를 생성한 후 X, Y만 정렬했을 때 총 실행 시간은 4032 ms가 걸렸지만
 * 				a, b, c, d를 정렬하고 X, Y를 생성한 뒤 정렬했을 땐 실행 시간이 3280 ms가 걸렸다.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * 
 */
import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static int[] x, y;
	private static int size;
	private static long res = 0;

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

	private static void solve(int front, int rear) {
		long xCnt, yCnt;
		int xVal, yVal, val;

		while (front < size && rear >= 0) {
			if ((val = (xVal = x[front]) + (yVal = y[rear])) < 0)
				front++;
			else if (val > 0)
				rear--;
			// x[front] + y[rear]가 0일 때, x[front]와 같은 값의 x 원소 갯수, y[rear]와 같은 값의 y 원소 갯수를
			// 세어, 이 둘을 곱한다.
			else {
				xCnt = yCnt = 0;
				// x[front]가 더이상 xVal이 아니라면 x[front] > xVal이다.
				while (front < size && x[front] == xVal) {
					// 조건문에서 front >= 0 && x[front++] == xVal과 같이 front를 업데이트하면
					// x[front]가 xVal과 달라도 값이 하나 빠지므로 그렇게 하면 안된다.
					front++;
					xCnt++;
				}
				// y[rear]가 더이상 yVal이 아니라면 y[rear] < yVal이다.
				while (rear >= 0 && y[rear] == yVal) {
					// 조건문에서 rear >= 0 && y[rear--] == yVal과 같이 rear를 업데이트하면
					// y[rear]가 yVal과 달라도 값이 하나 빠지므로 그렇게 하면 안된다.
					rear--;
					yCnt++;
				}
				res += xCnt * yCnt;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), base;
		int[] a = new int[n], b = new int[n], c = new int[n], d = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = readInt();
			b[i] = readInt();
			c[i] = readInt();
			d[i] = readInt();
		}
		Arrays.sort(a);
		Arrays.sort(b);
		Arrays.sort(c);
		Arrays.sort(d);
		size = n * n;
		x = new int[size];
		y = new int[size];
		for (int i = 0; i < n; i++) {
			base = i * n;
			for (int j = 0; j < n; j++) {
				x[base + j] = a[i] + b[j];
				y[base + j] = c[i] + d[j];
			}
		}
		Arrays.sort(x);
		Arrays.sort(y);
		solve(0, size - 1);
		System.out.println(res);
	}
}