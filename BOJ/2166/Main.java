
/*
 * x 좌표를 기준으로 순서대로 정렬 -> 세 좌표로 모든 삼각형 넓이 구하여 더하기?
 * 
 * 평면 좌표 상에서 꼭지점들의 좌표를 알 때 다각형의 넓이를 구하는 방법: 신발끈 공식
 * https://ko.wikipedia.org/wiki/%EC%8B%A0%EB%B0%9C%EB%81%88_%EA%B3%B5%EC%8B%9D
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static long readLong() throws IOException {
		long n = 0, cur, sign = br.read();

		if (sign != '-')
			n = sign & 15;
		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == '-')
			n = -n;
		return n;
	}

	public static void main(String[] args) throws IOException {
		long n = readLong(), fx, fy, px, py, cx = 0, cy = 0, res = 0;

		fx = px = readLong();
		fy = py = readLong();
		for (int i = 1; i < n; i++) {
			cx = readLong();
			cy = readLong();
			res += px * cy - cx * py;
			px = cx;
			py = cy;
		}
		res += cx * fy - fx * cy;
		bw.append(String.format("%.1f", Math.abs(res) / 2.0)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}