import java.io.IOException;

public class Main {
	private static int studentCount, blockCount, targetHeight;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] prev, curr, swap;
		int n, c;

		studentCount = read();
		blockCount = read();
		targetHeight = read();
		prev = new int[targetHeight + 1];
		curr = new int[targetHeight + 1];
		c = 0;
		while (c != 10) {
			n = 0;
			while (48 <= (c = System.in.read()) && c <= 57)
				n = (n << 3) + (n << 1) + (c & 15);
			curr[n]++;
		}
		for (int i = 2; i <= studentCount; i++) {
			swap = prev;
			prev = curr;
			curr = swap;
			n = c = 0;
			for (int h = 1; h <= targetHeight; h++)
				curr[h] = prev[h];
			while (c != 10) {
				while (48 <= (c = System.in.read()) && c <= 57)
					n = (n << 3) + (n << 1) + (c & 15);
				for (int h = 1; h <= targetHeight; h++)
					if (prev[h] > 0 && h + n <= targetHeight)
						curr[h + n] = (curr[h + n] + prev[h]) % 10007;
				curr[n] = (curr[n] + 1) % 10007;
				n = 0;
			}
		}
		System.out.println(curr[targetHeight]);
	}
}