import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	static int gcd(int a, int b) {
		int r;

		while (b > 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	static int solve(int m, int n, int x, int y) {
		int last = m * n / gcd(m, n), year;

		x -= 1;
		y -= 1;
		year = x;
		while ((year % n != y) && year < last) {
			year += m;
		}
		return year < last ? year + 1 : -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t, m, n, x, y;
		StringTokenizer st;

		t = Integer.parseInt(br.readLine());
		while (0 != t--) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			bw.write(Integer.toString(solve(m, n, x, y)));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
/*
 * 1476과 같은 유형의 문제
 * 
 */