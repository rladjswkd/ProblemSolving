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
		int studentCount = readInt(), checkCount = readInt(), res = 0, count;
		boolean[][] heightInfo = new boolean[studentCount][studentCount];

		for (int i = 0; i < checkCount; i++)
			heightInfo[readInt() - 1][readInt() - 1] = true;
		for (int via = 0; via < studentCount; via++)
			for (int start = 0; start < studentCount; start++)
				for (int end = 0; end < studentCount; end++)
					heightInfo[start][end] = heightInfo[start][end] || (heightInfo[start][via] && heightInfo[via][end]);
		for (int i = 0; i < studentCount; i++) {
			count = 0;
			for (int j = 0; j < studentCount; j++) {
				if (heightInfo[i][j])
					count++;
				if (heightInfo[j][i])
					count++;
			}
			if (count == studentCount - 1)
				res++;
		}
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}