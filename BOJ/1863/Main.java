import java.io.IOException;

public class Main {
	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), size = 0, l, r, res = 0, cur;
		int[][] stack = new int[n][2];

		for (int i = 0; i < n; i++) {
			l = readInt();
			r = readInt();
			if (size == 0 || stack[size - 1][1] < r) {
				stack[size][0] = l;
				stack[size][1] = r;
				size++;
			} else {
				while (size > 0 && stack[size - 1][1] > r) {
					cur = stack[size - 1][1];
					while (size > 0 && stack[size - 1][1] == cur)
						size--;
					res++;
				}
				if (r != 0) {
					stack[size][0] = l;
					stack[size][1] = r;
					size++;
				}
			}
		}
		r = 0;
		while (size > 0 && stack[size - 1][1] > r) {
			cur = stack[size - 1][1];
			while (size > 0 && stack[size - 1][1] == cur)
				size--;
			res++;
		}
		System.out.println(res);
	}
}