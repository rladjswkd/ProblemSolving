import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static int[] seq;
	private static boolean[] checker;

	private static void backtrack(int next, int acc) {
		checker[acc] = true;
		for (int i = next; i < seq.length; i++)
			backtrack(i + 1, acc + seq[i]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line;
		int total = 0;
		int res = 1;

		br.readLine();
		line = br.readLine().split(" ");
		seq = new int[line.length];
		for (int i = 0; i < seq.length; i++)
			seq[i] = Integer.parseInt(line[i]);
		for (int i : seq)
			total += i;
		checker = new boolean[total + 2];
		backtrack(0, 0);
		while (checker[res])
			res++;
		br.close();
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		bw.close();
	}
}