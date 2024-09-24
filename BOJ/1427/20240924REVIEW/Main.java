import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		byte[] digit = new byte[10];
		int c, r = 0;

		while ((c = System.in.read()) != 10)
			digit[c & 15]++;
		for (int i = 9; i >= 0; i--) {
			c = digit[i];
			while (c-- > 0)
				r = (r << 3) + (r << 1) + i;
		}
		System.out.println(r);
	}
}