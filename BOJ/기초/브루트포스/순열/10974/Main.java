import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static StringBuilder sb = new StringBuilder(645120); // n이 8(최대 입력값)일 때 출력 문자열의 길이
	private static boolean[] vis;

	private static void backtrack(int[] current, int idx) {
		if (idx == current.length) {
			sb.append(current[0]);
			for (int i = 1; i < idx; i++)
				sb.append(" ").append(current[i]);
			sb.append('\n');
			return;
		}
		for (int i = 0; i < current.length; i++) {
			if (vis[i])
				continue;
			vis[i] = true;
			current[idx] = i + 1;
			backtrack(current, idx + 1);
			vis[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		vis = new boolean[Integer.parseInt(br.readLine())];

		backtrack(new int[vis.length], 0);
		br.close();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}