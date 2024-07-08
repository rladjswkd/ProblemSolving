import java.io.IOException;

class Main {
	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), cur, prev;
		// 적절한 자리의 값을 바꾸고 난 후에 작은 값부터 이어붙일 숫자들을 저장
		int[] digits = new int[10];
		while (true) {
			cur = n % 10;
			prev = n / 10 % 10;
			if (prev < cur) {
				n /= 100;
				// 값을 바꿀 자리의 값 또한 뒤에 등장해야 하므로 digits에 저장
				digits[cur]++;
				digits[prev]++;
				break;
			}
			digits[cur]++;
			n /= 10;
		}
		for (int i = 0; i < 10; i++) {
			if (0 < digits[i] && prev < i) {
				digits[i]--;
				n = (n << 3) + (n << 1) + i;
				break;
			}
		}
		for (int i = 0; i < 10; i++) {
			while (digits[i]-- > 0)
				n = (n << 3) + (n << 1) + i;
		}
		System.out.println(n);
	}
}