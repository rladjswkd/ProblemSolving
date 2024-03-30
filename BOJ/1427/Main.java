import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] digit = new int[10];
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder(10);

		while (n > 0) {
			digit[n % 10]++;
			n /= 10;
		}
		for (int i = 9; i >= 0; i--)
			for (int c = 0; c < digit[i]; c++)
				sb.append(i);
		br.close();
		bw.write(sb.toString());
		bw.newLine();
		bw.flush();
		bw.close();
	}
}