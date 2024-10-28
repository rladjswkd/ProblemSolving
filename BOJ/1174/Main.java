import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int n;
	private static List<List<Long>> l;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(long val, int length) {
		int last = (int) (val % 10);
		long newVal;

		l.get(length).add(val);
		for (int d = last - 1; d >= 0; d--) {
			newVal = val;
			newVal = (newVal << 3) + (newVal << 1) + d;
			solve(newVal, length + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		int length = 1;

		n = read();
		l = new ArrayList<>(11);
		for (int i = 0; i < 11; i++)
			l.add(new ArrayList<>());
		for (int d = 9; d >= 0; d--)
			solve(d, 1);
		for (; length <= 10; length++) {
			if (n - l.get(length).size() <= 0)
				break;
			n -= l.get(length).size();
		}
		if (length == 11)
			System.out.println(-1);
		else
			System.out.println(l.get(length).get(l.get(length).size() - n));
	}
}