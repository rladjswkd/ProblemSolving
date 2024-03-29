import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int s, acc = 0, cur, res = Integer.MAX_VALUE;
		String[] seq;
		Deque<Integer> q = new ArrayDeque<>();

		s = Integer.parseInt(br.readLine().split(" ")[1]);
		seq = br.readLine().split(" ");
		for (int i = 0; i < seq.length; i++) {
			cur = Integer.parseInt(seq[i]);
			q.addLast(cur);
			acc += cur;
			while (s <= acc) {
				if (q.size() < res)
					res = q.size();
				acc -= q.removeFirst();
			}
		}
		if (res == Integer.MAX_VALUE)
			res = 0;
		br.close();
		bw.write(res + "\n");
		bw.flush();
		bw.close();
	}
}