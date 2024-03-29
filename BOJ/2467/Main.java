import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int fp = 0, rp, val = Integer.MAX_VALUE;
		int[] liq, res = new int[2];
		String[] line;

		br.readLine();
		line = br.readLine().split(" ");
		liq = new int[line.length];
		for (int i = 0; i < liq.length; i++)
			liq[i] = Integer.parseInt(line[i]);
		rp = liq.length - 1;
		while (fp < rp) {
			if (Math.abs(liq[rp] + liq[fp]) <= Math.abs(val)) {
				res[0] = liq[fp];
				res[1] = liq[rp];
				val = liq[rp] + liq[fp];
			}
			if (Math.abs(liq[rp] + liq[fp + 1]) < Math.abs(liq[fp] + liq[rp - 1]))
				fp++;
			else
				rp--;
		}
		br.close();
		bw.write(new StringBuilder().append(res[0]).append(" ").append(res[1]).append('\n').toString());
		bw.flush();
		bw.close();
	}
}