import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static int w;
	private static int[] blocks;

	private static int solve() {
		int l = 0, r, res = 0;

		while (l < blocks.length - 1) {
			r = -1;
			for (int i = l + 1; i < blocks.length; i++) {
				if (r == -1 || blocks[r] < blocks[i]) {
					r = i;
					if (blocks[l] <= blocks[r])
						break;
				}
			}
			for (int i = l + 1; i < r; i++)
				res += Math.min(blocks[l], blocks[r]) - blocks[i];
			if (l < r)
				l = r;
			else
				l++;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line;

		w = Integer.parseInt(br.readLine().split(" ")[1]);
		blocks = new int[w];
		line = br.readLine().split(" ");
		for (int i = 0; i < line.length; i++)
			blocks[i] = Integer.parseInt(line[i]);
		bw.write(solve() + "\n");
		bw.flush();
		br.close();
		bw.close();
	}
}