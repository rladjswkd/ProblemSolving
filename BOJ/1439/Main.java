import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		char[] input = br.readLine().toCharArray();
		int zeroCount = 0, oneCount = 0;

		if (input[0] == '0')
			zeroCount++;
		else
			oneCount++;
		for (int i = 1; i < input.length; i++) {
			if (input[i] != input[i - 1]) {
				if (input[i] == '0')
					zeroCount++;
				else
					oneCount++;
			}
		}
		bw.append(String.valueOf(Math.min(zeroCount, oneCount))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}