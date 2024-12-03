import java.io.IOException;
import java.util.List;

public class Main {

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int t = read();
		List<List<Integer>> list = List.of(
				List.of(10), List.of(1), List.of(6, 2, 4, 8),
				List.of(1, 3, 9, 7), List.of(6, 4), List.of(5), List.of(6),
				List.of(1, 7, 9, 3), List.of(6, 8, 4, 2), List.of(1, 9));
		List<Integer> curr;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0)
			sb.append((curr = list.get(read() % 10)).get(read() % curr.size())).append('\n');
		System.out.print(sb.toString());
	}
}