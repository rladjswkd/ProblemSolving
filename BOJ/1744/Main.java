import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int solve(List<Integer> positives, List<Integer> zeroOrLess) {
		int res = 0, index = 0, first, second;

		Collections.sort(positives, Collections.reverseOrder());
		Collections.sort(zeroOrLess);
		while (index < positives.size()) {
			if (index == positives.size() - 1)
				res += positives.get(index++);
			else {
				first = positives.get(index);
				second = positives.get(index + 1);
				if (first != 1 && second != 1)
					res += first * second;
				else
					res += first + second;
				index += 2;
			}
		}
		index = 0;
		while (index < zeroOrLess.size()) {
			if (index == zeroOrLess.size() - 1)
				res += zeroOrLess.get(index++);
			else {
				res += zeroOrLess.get(index) * zeroOrLess.get(index + 1);
				index += 2;
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine()), val;
		List<Integer> positives = new ArrayList<>(), zeroOrLess = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			val = Integer.parseInt(br.readLine());
			if (val > 0)
				positives.add(val);
			else
				zeroOrLess.add(val);
		}
		bw.append(String.valueOf(solve(positives, zeroOrLess))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}