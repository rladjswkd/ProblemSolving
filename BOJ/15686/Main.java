import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int n, m, res = Integer.MAX_VALUE;
	private static List<int[]> house = new ArrayList<>(), chicken = new ArrayList<>();
	private static boolean[] vis;

	private static void processInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line;
		String r;

		line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		for (int i = 0; i < n; i++) {
			r = br.readLine();
			for (int j = 0; j < r.length(); j += 2) {
				if (r.charAt(j) == '1')
					house.add(new int[] { i, j / 2 });
				else if (r.charAt(j) == '2')
					chicken.add(new int[] { i, j / 2 });
			}
		}
		vis = new boolean[chicken.size()];
		br.close();
	}

	private static void calculateDistance() {
		int dis, cur, total = 0;

		for (int[] ph : house) {
			dis = Integer.MAX_VALUE;
			for (int i = 0; i < chicken.size(); i++) {
				if (!vis[i])
					continue;
				cur = Math.abs(chicken.get(i)[0] - ph[0]) + Math.abs(chicken.get(i)[1] - ph[1]);
				if (cur < dis)
					dis = cur;
			}
			total += dis;
		}
		if (total < res)
			res = total;
	}

	private static void search(int idx, int remains) {
		if (remains == 0) {
			calculateDistance();
			return;
		}
		for (int i = idx; i <= chicken.size() - remains; i++) {
			if (vis[idx])
				continue;
			vis[i] = true;
			search(i + 1, remains - 1);
			vis[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		processInput();
		search(0, m);
		bw.write(res + "\n");
		bw.flush();
		bw.close();
	}
}