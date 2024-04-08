import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
	private static PriorityQueue<Integer> processInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> ipq = new PriorityQueue<>();
		int n = Integer.parseInt(br.readLine());

		while (n-- > 0)
			ipq.add(Integer.parseInt(br.readLine()));
		br.close();
		return ipq;
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		PriorityQueue<Integer> ipq = processInput();
		int cur, res = 0;

		while (ipq.size() > 1) {
			cur = ipq.poll();
			cur += ipq.poll();
			res += cur;
			ipq.add(cur);
		}
		bw.write(res + "\n");
		bw.flush();
		bw.close();
	}
}