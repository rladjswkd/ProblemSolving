import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static List<Integer> il = new ArrayList<>();
	private static int[] set;

	private static void backtrack(int idx) throws IOException {
		if (il.size() == 6) {
			StringBuilder sb = new StringBuilder(11);
			sb.append(il.get(0));
			for (int i = 1; i < il.size(); i++)
				sb.append(" ").append(il.get(i));
			bw.write(sb.toString());
			bw.newLine();
			return;
		}
		for (int i = idx; i < set.length; i++) {
			if (6 - il.size() <= set.length - idx) {
				il.add(set[i]);
				backtrack(i + 1);
				il.remove(il.size() - 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		String[] strs;

		while ((line = br.readLine()).charAt(0) != 48) {
			strs = line.split(" ");
			set = new int[strs.length - 1];
			for (int i = 1; i < strs.length; i++)
				set[i - 1] = Integer.parseInt(strs[i]);
			backtrack(0);
			bw.newLine();
		}
		br.close();
		bw.flush();
		bw.close();
	}
}