import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static double readDouble() throws IOException {
		double d = br.read() - 48;

		br.read(); // '.'
		d += (br.read() & 15) / 10.0;
		br.read(); // '\n'
		return d;
	}

	public static void main(String[] args) throws IOException {
		double[] arr = new double[Integer.parseInt(br.readLine())];
		double mult, res = readDouble();

		arr[0] = res;
		for (int i = 1; i < arr.length; i++) {
			arr[i] = readDouble();
			mult = arr[i - 1] * arr[i];
			if (arr[i] < mult)
				arr[i] = mult;
			if (res < arr[i])
				res = arr[i];
		}
		bw.append(String.format("%.3f", res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}