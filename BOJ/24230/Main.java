import java.io.IOException;

public class Main {
	private static int n;
	private static int[] colors;
	
	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int res = 0;

		colors = new int[n = read()];
		for (int i = 0; i < n; i++)
			colors[i] = read();
		for (int i = 1; i < n; i++)
			if (colors[read() - 1] != colors[read() - 1])
				res++;		
		System.out.println(res + (colors[0] == 0 ? 0 : 1));
	}
}