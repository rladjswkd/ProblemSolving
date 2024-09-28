import java.io.IOException;

public class Main {
	private static int studentCount, pairCount;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] result = new int[studentCount = read()];
		StringBuilder sb = new StringBuilder();
		boolean[] checker = new boolean[studentCount];

		pairCount = read();
		for (int i = 0; i < pairCount; i++) {
			result[read() - 1]++;
			result[read() - 1]--;
		}
		for (int i = 0; i < studentCount; i++) {
			if (checker[i + result[i]]) {
				System.out.println(-1);
				return;
			}
			checker[i + result[i]] = true;
			sb.append(i + 1 + result[i]).append(' ');
		}
		System.out.println(sb.toString());
	}
}