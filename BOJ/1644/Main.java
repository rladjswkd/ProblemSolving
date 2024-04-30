import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static boolean[] primeFlags;

	private static int getNextPrime(int cur) {
		int next = cur + 1;

		for (; next < primeFlags.length; next++)
			if (primeFlags[next])
				break;
		return next;
	}

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine()), start = 1, end = 1, val = 0, res = 0;

		primeFlags = new boolean[n + 1];
		for (int i = 2; i < primeFlags.length; i++)
			primeFlags[i] = true;
		for (int i = 2; i <= (int) Math.sqrt(n); i++) {
			if (!primeFlags[i])
				continue;
			for (int j = i << 1; j < primeFlags.length; j += i)
				primeFlags[j] = false;
		}
		while (start == 1 && end == 1 || start != end) {
			// 현재 합(val)이 n보다 작을 때
			if (val < n)
				val += (end = getNextPrime(end));
			// 현재 합(val)이 n보다 크거나 같을 때
			else {
				// 현재 합(val)이 n과 같을 때
				if (val == n)
					res++;
				val -= (start = getNextPrime(start));
			}
		}
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}