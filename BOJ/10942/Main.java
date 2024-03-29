import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] seq;
		boolean[][] res;
		int count;
		String[] line;

		br.readLine();
		line = br.readLine().split(" ");
		seq = new int[line.length];
		for (int i = 0; i < seq.length; i++)
			seq[i] = Integer.parseInt(line[i]);
		res = new boolean[seq.length][seq.length];
		// 길이가 홀수인 팰린드롬 검사. ex) 1, 212, 12121 등
		for (int i = 0; i < seq.length; i++) {
			for (int j = 0; 0 <= i - j && i + j < seq.length; j++) {
				if (seq[i - j] == seq[i + j])
					res[i - j][i + j] = true;
				else
					break;
			}
		}
		// 길이가 짝수인 팰린드롬 검사. ex) 11, 1221, 123321 등
		for (int i = 0; i < seq.length; i++) {
			for (int j = 0; 0 <= i - j && i + 1 + j < seq.length; j++) {
				if (seq[i - j] == seq[i + 1 + j])
					res[i - j][i + 1 + j] = true;
				else
					break;
			}
		}
		count = Integer.parseInt(br.readLine());
		while (count-- > 0) {
			line = br.readLine().split(" ");
			if (res[Integer.parseInt(line[0]) - 1][Integer.parseInt(line[1]) - 1])
				bw.append('1');
			else
				bw.append('0');
			bw.append('\n');
		}
		br.close();
		bw.flush();
		bw.close();
	}
}