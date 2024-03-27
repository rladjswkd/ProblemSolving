import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/*
 * 1 ~ 9: 9(개수) * 1(자리수) = 9
 * 10 ~ 99: 90(개수) * 2(자리수) = 180
 * 100 ~ 999: 900(개수) * 3(자리수) = 2700
 * ...
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int digit = 1, count = 9, nextMin = 10, result = 0;

		while (n / nextMin != 0) {
			result += count * digit;
			count *= 10;
			digit++;
			nextMin *= 10;
		}

		result += (n - nextMin / 10 + 1) * digit;
		bw.write(Integer.toString(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
