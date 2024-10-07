import java.io.IOException;

public class Main {
	private static int n;
	private static int[] liquids;

	private static int read() throws IOException {
		int n = 0, c, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return sign == 45 ? ~n + 1 : n;
	}

	public static void main(String[] args) throws IOException {
		int front, rear, res = 200000001, value;

		liquids = new int[n = read()];
		for (int i = 0; i < n; i++)
			liquids[i] = read();
		front = 0;
		rear = n - 1;
		while (front < rear) {
			value = liquids[front] + liquids[rear];
			if (Math.abs(value) < Math.abs(res))
				res = value;
			if (value < 0)
				front++;
			else
				rear--;
		}
		System.out.println(res);
	}
}