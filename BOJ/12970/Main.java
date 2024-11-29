import java.io.IOException;

public class Main {
	private static int n, k;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int ac, bc, carried, idx;
		StringBuilder sb = new StringBuilder();

		n = read();
		k = read();
		if ((n / 2) * (n - n / 2) < k)
			System.out.println(-1);
		else {
			bc = n - (ac = n / 2);
			carried = idx = 0;
			while (k <= (ac - carried - 1) * bc)
				carried++;
			if ((idx = (ac - carried) * bc - k) > 0) {
				for (int i = 1; i < ac - carried; i++)
					sb.append('A');
				for (int i = 0; i < idx; i++)
					sb.append('B');
				sb.append('A');
				for (int i = idx; i < bc; i++)
					sb.append('B');
			} else {
				for (int i = 0; i < ac - carried; i++)
					sb.append('A');
				for (int i = idx; i < bc; i++)
					sb.append('B');
			}
			for (int i = 0; i < carried; i++)
				sb.append('A');
			System.out.println(sb.toString());
		}
	}
}