import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Collections;

public class Main {
	private static int count;
	private static StringBuilder sb;
	private static PriorityQueue<Integer> minorPQ, majorPQ;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return sign == 45 ? ~n + 1 : n;
	}

	private static void appendMedian(int median) {
		count++;
		if (count < 10)
			sb.append(median).append(' ');
		else {
			sb.append(median).append('\n');
			count = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		int t = readInt(), size, cur, median;

		sb = new StringBuilder();
		while (t-- > 0) {
			minorPQ = new PriorityQueue<>();
			majorPQ = new PriorityQueue<>();
			size = readInt();
			sb.append((size >>> 1) + size % 2).append('\n');
			// 한 줄에 10개의 중앙값만 출력해야 하므로, 10이 되면 sb에 '\n'을 추가하고
			// count를 0으로 만들기
			count = 0;
			appendMedian(median = readInt());
			for (int i = 2; i <= size; i++) {
				cur = readInt();
				if (cur < median)
					minorPQ.add(-cur);
				else
					majorPQ.add(cur);
				if (i % 2 != 0) {
					while (minorPQ.size() < majorPQ.size()) {
						minorPQ.add(-median);
						median = majorPQ.poll();
					}
					while (minorPQ.size() > majorPQ.size()) {
						majorPQ.add(median);
						median = -minorPQ.poll();
					}
					appendMedian(median);
				}
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}