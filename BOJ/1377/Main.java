import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), res = 0, diff;
		List<int[]> sorted = new ArrayList<>(n);

		for (int i = 0; i < n; i++)
			sorted.add(new int[] { readInt(), i });
		Collections.sort(sorted, (a, b) -> a[0] - b[0]);
		for (int i = 0; i < n; i++)
			// if (i < (formerIndex = sorted.get(i)[1]) && res < formerIndex - i)
			if (res < (diff = sorted.get(i)[1] - i))
				res = diff;
		System.out.println(res + 1);
	}
}