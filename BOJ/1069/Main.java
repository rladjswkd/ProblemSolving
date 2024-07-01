import java.io.*;

public class Main {
	private static double remainDist, time = 0;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int x = readInt(), y = readInt(), d = readInt(), t = readInt();

		remainDist = Math.sqrt(x * x + y * y);
		if (d <= t)
			System.out.println(remainDist);
		else {
			while (remainDist > 0) {
				if (remainDist > d) {
					if (remainDist < d << 1) {
						// 이등변 삼각형을 그리며 이동하는 시간, 점프 후 남은 거리 걸어가는 시간, 두 번 점프 후 초과 걸이 돌아오는 데 걸리는 시간을 비교해
						// 최소값 선택
						time += Math.min(t * 2, Math.min(t + remainDist - d, 2 * t + 2 * d - remainDist));
						remainDist = 0;
					} else {
						time += t;
						remainDist -= d;
					}
				} else if (remainDist < d) {
					time = Math.min(t * 2, Math.min(remainDist, t + d - remainDist));
					remainDist = 0;
				} else {
					time += t;
					remainDist = 0;
				}
			}
		}
		System.out.println(time);
	}
}