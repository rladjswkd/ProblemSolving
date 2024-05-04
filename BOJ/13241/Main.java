import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static long readLong() throws IOException {
		long n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static long getGCD(long a, long b) {
		long c;

		while (b > 0) {
			c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		long a = readLong(), b = readLong(), gcd = getGCD(a, b);

		bw.append(String.valueOf(a * b / gcd)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}