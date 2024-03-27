import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int[] beads;
	private static int res = 0;

	private static void search(int count, int acc) {
		int l, r, wi;

		if (count == beads.length) {
			res = Math.max(res, acc);
			return;
		}
		for (int i = 1; i < beads.length - 1; i++) {
			if (beads[i] == 0)
				continue;
			wi = beads[i];
			beads[i] = 0;
			l = r = i;
			while (beads[l] == 0)
				l--;
			while (beads[r] == 0)
				r++;
			search(count + 1, acc + beads[l] * beads[r]);
			beads[i] = wi;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line;

		br.readLine();
		line = br.readLine().split(" ");
		beads = new int[line.length];
		for (int i = 0; i < line.length; i++)
			beads[i] = Integer.parseInt(line[i]);
		search(2, 0);
		br.close();
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		bw.close();
	}
}