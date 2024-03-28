import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static int h, w, limit = 0, res = Integer.MIN_VALUE;
	private static int[][] paper;

	private static void search(int checker, int total) {
		int x, y, acc, checkerBackup, bit = 0;
		// checker의 모든 비트가 채워졌다면 res를 res와 total 중 더 큰 값으로 업데이트하고 리턴
		if (checker == limit) {
			res = Math.max(res, total);
			return;
		}
		// 아직 확인하지 않은(종이 조각을 잘라 그 값을 total에 더하지 않은) 1 X 1 종이의 인덱스 찾기
		while ((checker & 1 << bit) != 0)
			bit++;
		x = bit / w;
		y = bit % w;
		// (x, y)에 대해 가로로 자르는 방식, 세로로 자르는 방식을 별도로 검사하면 (x, y) 자체는 두 번 검사하게 되므로,
		// (x, y)는 여기서 별도로 검사하고, 가로 방식은 (x, y + 1)부터 검사하고 세로 방식은 (x + 1, y)부터 검사한다.
		acc = paper[x][y];
		checker |= 1 << bit;
		checkerBackup = checker;
		search(checker, total + acc);
		// 세로 방식 검사
		for (int i = x + 1; i < h; i++) {
			if ((checker & 1 << w * i + y) != 0)
				break;
			acc = acc * 10 + paper[i][y];
			checker |= 1 << w * i + y;
			search(checker, total + acc);
		}
		// 가로 방식 검사
		acc = paper[x][y];
		checker = checkerBackup;
		for (int j = y + 1; j < w; j++) {
			if ((checker & 1 << w * x + j) != 0)
				break;
			acc = acc * 10 + paper[x][j];
			checker |= 1 << w * x + j;
			search(checker, total + acc);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line;
		String eachRow;

		line = br.readLine().split(" ");
		h = Integer.parseInt(line[0]);
		w = Integer.parseInt(line[1]);
		paper = new int[h][w];
		for (int i = 0; i < h; i++) {
			eachRow = br.readLine();
			for (int j = 0; j < eachRow.length(); j++)
				paper[i][j] = eachRow.charAt(j) - 48;
		}
		limit |= 1 << h * w;
		limit--;
		search(0, 0);
		br.close();
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		bw.close();
	}
}