import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static int seqLength, queryCount;
	private static int[] seq;
	private static PriorityQueue<int[]> pq;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int query, index, value;
		int[] cur;
		StringBuilder sb = new StringBuilder();

		seq = new int[seqLength = readInt()];
		pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		for (int i = 0; i < seqLength; i++) {
			seq[i] = readInt();
			pq.add(new int[] { seq[i], i });
		}
		// int[0]: 값, int[1]: 인덱스
		queryCount = readInt();
		for (int i = 0; i < queryCount; i++) {
			if ((query = readInt()) == 1) {
				seq[index = readInt() - 1] = (value = readInt());
				pq.add(new int[] { value, index });
			} else if (query == 2) {
				// 현재 우선순위 큐에서 가져올 [value, index] 쌍에 대해, seq[index] 가 value와 다르다면 poll 해서 버리기
				while (seq[(cur = pq.peek())[1]] != cur[0])
					pq.poll();
				sb.append(pq.peek()[1] + 1).append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}