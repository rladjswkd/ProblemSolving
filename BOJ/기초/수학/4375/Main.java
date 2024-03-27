import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line;
		int n, r, c;

		while ((line = br.readLine()) != null) {
			n = Integer.parseInt(line);
			r = 1;
			c = 1;
			while (r % n != 0) {
				r = (r * 10 + 1) % n;
				c++;
			}
			bw.write(String.valueOf(c));
			bw.newLine();
		}
		br.close();
		bw.flush();
		bw.close();
	}
}