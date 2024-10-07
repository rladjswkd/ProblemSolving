import java.io.IOException;

public class Main {
	private static int height, length, result;
	private static int[] weights;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;

	}

	private static int solve(int node) {
		int l = (node << 1) + 1, r = l + 1, lsub, rsub, max = 0;

		if (l < length) {
			max = Math.max(weights[l] + (lsub = solve(l)), weights[r] + (rsub = solve(r)));
			weights[l] = max - lsub;
			weights[r] = max - rsub;
			result += weights[l] + weights[r];
			return max;
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		weights = new int[(1 << (height = read()) + 1) - 1];
		for (int i = 1; i < weights.length; i++)
			weights[i] = read();
		length = weights.length;
		solve(0);
		System.out.println(result);
	}
}