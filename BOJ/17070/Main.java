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

	public static void main(String[] args) throws IOException {
		int n = readInt();
		int[][] house = new int[n][n];
		int[][][] dist = new int[3][n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				house[i][j] = readInt();
		dist[0][0][1] = 1;
		for (int j = 2; j < n; j++)
			if (house[0][j] == 0)
				dist[0][0][j] = dist[0][0][j - 1];
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				if (house[i][j] == 0) {
					dist[0][i][j] = dist[0][i][j - 1] + dist[2][i][j - 1];
					dist[1][i][j] = dist[1][i - 1][j] + dist[2][i - 1][j];
					if (house[i - 1][j] == 0 && house[i][j - 1] == 0)
						dist[2][i][j] = dist[0][i - 1][j - 1] + dist[1][i - 1][j - 1] + dist[2][i - 1][j - 1];
				}
			}
		}
		bw.append(String.valueOf(dist[0][n - 1][n - 1] + dist[1][n - 1][n - 1] + dist[2][n - 1][n - 1])).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}