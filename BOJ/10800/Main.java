import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int n;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int sizeAcc = 0, c, s;
		int[] sizeAccByColor = new int[200000], res;
		// int[]의 0번 인덱스: 공의 색, 1번 인덱스: 기존 인덱스
		List<List<int[]>> ballsGroupBySize = new ArrayList<>(2002);
		List<int[]> ballsSameSize;
		StringBuilder sb = new StringBuilder();

		n = readInt();
		for (int i = 0; i < 2001; i++)
			ballsGroupBySize.add(new ArrayList<>());
		for (int i = 0; i < n; i++) {
			c = readInt() - 1;
			s = readInt();
			ballsGroupBySize.get(s).add(new int[] { c, i });
		}
		res = new int[n];
		for (int size = 1; size <= 2000; size++) {
			ballsSameSize = ballsGroupBySize.get(size);
			for (int[] ball : ballsSameSize)
				res[ball[1]] = sizeAcc - sizeAccByColor[ball[0]];
			for (int[] ball : ballsSameSize) {
				sizeAcc += size;
				sizeAccByColor[ball[0]] += size;
			}
		}
		for (int v : res)
			sb.append(v).append('\n');
		System.out.print(sb.toString());
	}
}