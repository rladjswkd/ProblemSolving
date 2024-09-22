import java.io.IOException;

public class Main {
	private static int n, s;
	private static int[] seq;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		// l, r: 부분합을 구할 부분의 시작과 끝 인덱스
		// partial: [l, r] 부분합
		// length: 출력할 결과. 초깃값은 최댓값 + 1으로.
		int l, r, partial, length = 100000;

		n = read();
		s = read();
		seq = new int[n];
		for (int i = 0; i < n; i++)
			seq[i] = read();
		l = r = partial = 0;
		while (r < n) {
			if (s <= partial) {
				if (r - l < length)
					length = r - l;
				partial -= seq[l++];
			} else
				partial += seq[r++];
		}
		// 부분합에 seq의 마지막 값이 더해지고 나면 r == n이 되어 while문을 빠져나오게 된다.
		// 그 이후의 작업을 위의 while문 내에선 수행하지 못하므로 별도로 처리해줘야 한ㄷ.
		// 유효한 범위의 포인터 l에 대해 partial -= seq[l++]를 수행하면 partial이 0에 도달하는 순간이 온다.
		// 따라서 l의 유효성 검사를 수행하지 않아도 된다.
		while (s <= partial) {
			if (r - l < length)
				length = r - l;
			partial -= seq[l++];
		}
		System.out.println(length == 100000 ? 0 : length);
	}
}