import java.io.IOException;

public class Main {
	private static int n, k, top;
	private static int[] seq, stack;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int idx, targetSize;
		StringBuilder sb = new StringBuilder();

		n = readInt();
		k = readInt();
		seq = new int[n];
		for (int i = 0; i < n; i++)
			seq[i] = System.in.read() & 15;
		System.in.read();
		stack = new int[n];
		top = -1;
		targetSize = n - k;
		for (idx = 0; idx < n && k > 0; idx++) {
			if (top > -1) {
				while (k > 0 && top > -1 && stack[top] < seq[idx]) {
					top--;
					k--;
				}
			}
			stack[++top] = seq[idx];
		}
		if (top + 1 <= targetSize) {
			for (int i = 0; i <= top; i++)
				sb.append(stack[i]);
			if (top + 1 < targetSize)
				for (; idx < n; idx++)
					sb.append(seq[idx]);
		} else
			for (int i = 0; i < targetSize; i++)
				sb.append(stack[i]);
		System.out.println(sb.toString());
	}
}