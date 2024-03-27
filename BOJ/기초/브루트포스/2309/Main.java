import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		List<Integer> heights = new ArrayList<Integer>();
		boolean go = true;
		int sum = 0;

		for (int i = 0; i < 9; i++) {
			heights.add(Integer.parseInt(br.readLine()));
			sum += heights.get(i);
		}

		for (int i = 0; i < 9 && go; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (sum - (heights.get(i) + heights.get(j)) == 100) {
					heights.set(i, 101);
					heights.set(j, 101);
					go = false;
				}
			}
		}

		Collections.sort(heights);
		for (int i = 0; i < 7; i++) {
			bw.write(Integer.toString(heights.get(i)));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
