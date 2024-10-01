import java.io.IOException;

public class Main {
	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = read(), index = 0;
		long res = n;

		for (int i = 1; i < n; i = index + 1) {
			// (n - 1) / i와 같은 값을 갖는 가장 큰 인덱스를 추적한다.
			index = (n - 1) / ((n - 1) / i);
			res += (n - 1) / i * (index - i + 1);
		}
		System.out.println(res);
	}
}