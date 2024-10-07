import java.io.IOException;

public class Main {
	private static int boardSize, dayCount;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] total;
		int idx, count, start;
		StringBuilder sb = new StringBuilder();

		boardSize = read();
		dayCount = read();
		total = new int[boardSize + boardSize - 1];
		for (int i = 0; i < dayCount; i++) {
			idx = 0;
			for (int growth = 0; growth < 3; growth++) {
				count = read();
				while (count-- > 0)
					total[idx++] += growth;
			}
		}
		idx = total.length / 2;
		start = idx + 1;
		for (int i = 0; i < boardSize; i++) {
			sb.append(1 + total[idx--]).append(' ');
			for (int j = start; j < total.length; j++)
				sb.append(1 + total[j]).append(' ');
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}