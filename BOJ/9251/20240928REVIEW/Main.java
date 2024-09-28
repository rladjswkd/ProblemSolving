import java.io.IOException;

public class Main {
	private static short[] seq1, seq2;
	private static short l1, l2;

	public static void main(String[] args) throws IOException {
		short c, val;
		short[] dp, prev, swap;

		seq1 = new short[1000];
		seq2 = new short[1000];
		while ('A' <= (c = (short) System.in.read()) && c <= 'Z')
			seq1[l1++] = c;
		while ('A' <= (c = (short) System.in.read()) && c <= 'Z')
			seq2[l2++] = c;
		dp = new short[l2 + 1];
		prev = new short[l2 + 1];
		for (short i = 1; i <= l1; i++) {
			swap = prev;
			prev = dp;
			dp = swap;
			for (short j = 1; j <= l2; j++) {
				val = (short) Math.max(dp[j - 1], prev[j]);
				if (seq1[i - 1] == seq2[j - 1])
					val = (short) Math.max(val, prev[j - 1] + 1);
				dp[j] = val;
			}
		}
		System.out.println(dp[l2]);
	}
}