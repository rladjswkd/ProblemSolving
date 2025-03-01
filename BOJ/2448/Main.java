import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(char[][] stars, int bottom, int topX, int topY) {
		int realHeight = bottom - topX, midX = topX + realHeight / 2;

		if (realHeight == 3) {
			stars[topX][topY] = stars[topX + 1][topY - 1] = stars[topX + 1][topY + 1] = '*';
			for (int i = topY - 2; i <= topY + 2; i++)
				stars[topX + 2][i] = '*';
			return;
		}
		solve(stars, midX, topX, topY);
		solve(stars, bottom, midX, topY - realHeight / 2);
		solve(stars, bottom, midX, topY + realHeight / 2);
	}

	public static void main(String[] args) throws IOException {
		int height = readInt(), width = 2 * height - 1;
		char[][] stars = new char[height][width];

		for (char[] star : stars)
			for (int i = 0; i < star.length; i++)
				star[i] = ' ';
		solve(stars, height, 0, width / 2);
		for (char[] star : stars) {
			bw.write(star);
			bw.newLine();
		}
		bw.flush();
		br.close();
		bw.close();
	}
}