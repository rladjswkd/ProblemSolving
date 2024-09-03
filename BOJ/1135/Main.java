import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static class Worker implements Comparable<Worker> {
		int id, subCount;

		Worker(int id, int subCount) {
			this.id = id;
			this.subCount = subCount;
		}

		@Override
		public int compareTo(Worker o) {
			return o.subCount - subCount;
		}
	}

	private static int n;
	private static int[] supervisors;
	private static List<List<Worker>> tree;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int calculateTime(int id) {
		List<Worker> subordinates = tree.get(id);
		int order = 0, max = 0;

		for (Worker s : subordinates)
			s.subCount = calculateTime(s.id);
		Collections.sort(subordinates);
		for (Worker s : subordinates)
			max = Math.max(max, ++order + s.subCount);
		return max;
	}

	public static void main(String[] args) throws IOException {
		supervisors = new int[n = readInt()];
		// -, 1, [space]를 처리
		System.in.read();
		System.in.read();
		System.in.read();
		supervisors[0] = -1;
		for (int i = 1; i < n; i++)
			supervisors[i] = readInt();
		tree = new ArrayList<>();
		for (int i = 0; i < n; i++)
			tree.add(new ArrayList<>());
		for (int i = 1; i < n; i++)
			tree.get(supervisors[i]).add(new Worker(i, 0));
		System.out.println(calculateTime(0));
	}
}