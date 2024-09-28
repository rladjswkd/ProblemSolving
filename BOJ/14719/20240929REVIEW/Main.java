import java.io.IOException;

public class Main {
	private static int h, w;
	private static int[] blocks;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int count() {
		// left: 높이가 1 이상이며, j보다 왼쪽에 있는 블록의 인덱스를 추적한다.
		// count: 높이가 1 이상인 블록 사이에 끼인 높이가 0 이하인 블록의 개수를 추적한다.
		int left, count = 0;

		// 높이가 1 이상인 첫 번째 블록 인덱스 찾기
		for (left = 0; left < w; left++)
			// count를 세면서 높이를 감소시키기
			if (blocks[left]-- > 0)
				break;
		// left 인덱스의 블록 높이를 1 감소시키기
		for (int i = left + 1; i < w; i++) {
			// count를 세면서 높이를 감소시키기
			if (blocks[i]-- > 0) {
				count += i - left - 1;
				left = i;
			}
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		int res = 0;

		h = read();
		blocks = new int[w = read()];
		for (int i = 0; i < w; i++)
			blocks[i] = read();
		for (int i = 0; i < h; i++)
			res += count();
		System.out.println(res);
	}
}