import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static int[] seq;
	private static int res = 0;
	private static int target = 0;

	private static void backtrack(int idx, int acc, int cnt) {
		if (cnt > 0 && acc == target)
			res++;
		for (int i = idx; i < seq.length; i++)
			backtrack(i + 1, acc + seq[i], cnt + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line;

		target = Integer.parseInt(br.readLine().split(" ")[1]);
		line = br.readLine().split(" ");
		seq = new int[line.length];
		for (int i = 0; i < seq.length; i++)
			seq[i] = Integer.parseInt(line[i]);
		backtrack(0, 0, 0);
		br.close();
		bw.write(String.valueOf(res));
		bw.newLine();
		bw.flush();
		bw.close();
	}
}