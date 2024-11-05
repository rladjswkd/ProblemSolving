import java.io.IOException;

public class Main {
	private static int w, h, f, c, x1, y1, x2, y2;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		long total = (long)(w = read()) * (h = read());
		int rx = 0, hCount, wDoubleCount, wCount;

		f = read();
		c = read();
		x1 = read();
		y1 = read();
		x2 = read();
		y2 = read();
		// 가로 접기
		// f == 0, f == w인 경우는 아무 것도 하지 않는다.
		if (0 < f && f <= w / 2)
			rx = f;
		else if (w / 2 < f && f < w)
			rx = w - f;
		// 세로 접기. 따로 할 일은 없다.
		// 색칠할 직사각형의 세로 길이를 계산한다.
		hCount = y2 - y1;
		// 색칠할 직사각형의 가로 길이를 가로로 접힌 부분 wDoubleCount와 그렇지 않은 부분 wCount로 나눈다.
		wDoubleCount = Math.max(0, Math.min(rx, x2) - x1);
		wCount = x2 - x1 - wDoubleCount;
		total -= (c + 1L) * 2 * wDoubleCount * hCount;
		total -= (c + 1L) * wCount * hCount;
		System.out.println(total);
	}
}