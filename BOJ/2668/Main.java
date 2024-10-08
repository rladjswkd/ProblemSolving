import java.io.IOException;

public class Main {
	private static int n;
	private static int[] seq, state;
	private static boolean[] set;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int check(int idx) {
		int head;

		if (state[idx] == 2)
			return -1;
		if (state[idx] == 1)
			return idx;
		state[idx] = 1;
		head = check(seq[idx] - 1);
		state[idx] = 2;
		if (head == -1)
			return -1;
		// 사이클의 시작 지점 또는 중간 지점들
		set[idx] = true;
		// 사이클의 시작 지점
		if (head == idx)
			return -1;
		// 중간 지점들
		return head;
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		seq = new int[n = read()];
		for (int i = 0; i < n; i++)
			seq[i] = read();
		state = new int[n];
		set = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (state[i] == 0)
				check(i);
			if (set[i])
				count++;
		}
		sb.append(count).append('\n');
		for (int i = 0; i < n; i++)
			if (set[i])
				sb.append(i + 1).append('\n');
		System.out.print(sb.toString());
	}
}