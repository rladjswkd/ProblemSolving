import java.io.IOException;

public class Main {
	private static int length;
	private static int[] str;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static boolean isPalindrome() {
		int left, right;

		if (length % 2 == 0) {
			right = length >>> 1;
			left = right - 1;
		} else {
			right = (length >>> 1) + 1;
			left = right - 2;
		}
		while (0 <= left && right < length && str[left] == str[right]) {
			left--;
			right++;
		}
		return left == -1 && right == length;
	}

	private static boolean isPseudoPalindrome() {
		int left, right;

		if (length % 2 == 0) {
			right = (length >>> 1) + 1;
			left = right - 2;
		} else {
			left = length >>> 1;
			right = left + 1;
		}
		while (0 <= left && right < length && str[left] == str[right]) {
			left--;
			right++;
		}
		left--;
		while (0 <= left && right < length && str[left] == str[right]) {
			left--;
			right++;
		}
		if (left == -1 && right == length)
			return true;
		if (length % 2 == 0) {
			right = (length >>> 1);
			left = right - 2;
		} else {
			right = length >>> 1;
			left = right - 1;
		}
		while (0 <= left && right < length && str[left] == str[right]) {
			left--;
			right++;
		}
		right++;
		while (0 <= left && right < length && str[left] == str[right]) {
			left--;
			right++;
		}
		return left == -1 && right == length;
	}

	public static void main(String[] args) throws IOException {
		int t = read(), c;
		StringBuilder sb = new StringBuilder();

		str = new int[100000];
		while (t-- > 0) {
			length = 0;
			while ((c = System.in.read()) != 10)
				str[length++] = c;
			if (isPalindrome())
				sb.append(0);
			else if (isPseudoPalindrome())
				sb.append(1);
			else
				sb.append(2);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}