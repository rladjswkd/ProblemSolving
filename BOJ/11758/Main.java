import java.io.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int first = br.read(), n = 0, cur;

		if (48 <= first && first <= 57)
			n = (first & 15);
		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (first == '-')
			n *= -1;
		return n;
	}
	// 외적 -> 값이 0이면 평행 음수면 시계, 양수면 반시계
	/*
	 * p1x - p2x, p1y - p2y
	 * p2x - p3x, p2y - p3y
	 * 
	 * p1x*p2y - p1x*p3y - p2x*p2y + p2x*p3y - (p1y*p2x - p1y*p3x - p2y*p2x + p2y*p3x)
	 */
	public static void main(String[] args) throws IOException {
		int p1x = readInt(), p1y = readInt(),
			p2x = readInt(), p2y = readInt(),
			p3x = readInt(), p3y = readInt(), res;

		res = p1x * p2y + p2x * p3y + p1y * p3x - p1x * p3y - p1y * p2x - p2y * p3x;
		if (res < 0)
			res = -1;
		else if (res > 0)
			res = 1;
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}