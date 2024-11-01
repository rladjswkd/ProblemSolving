import java.io.IOException;

public class Main {
	private static final long BOUNDARY = 1_000_000_000L;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt();
		long res = 0;

		// % BOUNDARY로 구할 수 있는 가장 큰 수 999,999,999와 가장 큰 i의 값 1,000,000을 곱해도 long의 범위를
		// 벗어나지 않는다.
		for (int i = 2; i <= n; i++) {
			if (i % 2 == 0)
				res = (i * res + 1) % BOUNDARY;
			else
				res = (i * res - 1) % BOUNDARY;
		}
		System.out.println(res);
	}
}