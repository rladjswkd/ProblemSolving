import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int[] fibo = new int[1000001];
		int n = Integer.parseInt(br.readLine());

		fibo[0] = 0;
		fibo[1] = 1;
		for (int i = 2; i <= n; i++)
			fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1_000_000_007;
		bw.append(String.valueOf(fibo[n])).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}