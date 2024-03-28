import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

/*
 * 특정 위치 P에서 순간이동한 후 -1씩 돌아와 목적지에 도달하기 위한 이동 횟수가 
 * P에서 +1 씩 이동해 목적지에 도달하기 위한 이동 횟수보다 크다면 순간이동을 하면 안된다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]), k = Integer.parseInt(line[1]), cur;
		Deque<Integer> q = new ArrayDeque<>();
		int[] prev = new int[100002];

		Arrays.fill(prev, -1);
		q.addLast(n);
		prev[n] = Integer.MAX_VALUE;
		while (prev[k] == -1) {
			cur = q.removeFirst();
			if (0 <= cur - 1 && prev[cur - 1] == -1) {
				q.addLast(cur - 1);
				prev[cur - 1] = cur;
			}
			if (cur + 1 < prev.length && prev[cur + 1] == -1) {
				q.addLast(cur + 1);
				prev[cur + 1] = cur;
			}
			if (cur != 0 && 2 * cur < prev.length && prev[2 * cur] == -1) {
				q.addLast(2 * cur);
				prev[2 * cur] = cur;
			}
		}
		q.clear();
		cur = k;
		while (cur != n) {
			q.addLast(cur);
			cur = prev[cur];
		}
		bw.append(String.valueOf(q.size())).append('\n').append(String.valueOf(n));
		while (!q.isEmpty())
			bw.append(' ').append(String.valueOf(q.removeLast()));
		bw.append('\n');
		br.close();
		bw.flush();
		bw.close();
	}
}