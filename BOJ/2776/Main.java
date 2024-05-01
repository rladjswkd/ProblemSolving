/*
 * readInt를 쓰지 않고 StringTokenizer와 Integer.parseInt를 쓰는 게 더 빠르나..
 * 아니 애초에 맞는 알고리즘으로 풀어도 입출력 가지고 시간초과 나는 게 맞는 건가.. 
 */

import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] note;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int binarySearch(int val) {
		int start = 0, end = note.length - 1, mid = end;

		while (start <= end) {
			mid = (start + end) / 2;
			if (note[mid] == val)
				return 1;
			if (note[mid] < val)
				start = mid + 1;
			else
				end = mid - 1;
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		int t = Integer.parseInt(br.readLine()), cnt;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (t-- > 0) {
			cnt = Integer.parseInt(br.readLine());
			note = new int[cnt];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < cnt; i++)
				note[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(note);
			cnt = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < cnt; i++)
				sb.append(binarySearch(Integer.parseInt(st.nextToken()))).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}