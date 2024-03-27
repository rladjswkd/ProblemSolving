import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	private static int[] operands, operators = new int[4];

	private static void search(int next, int acc) {
		if (next == operands.length) {
			min = Math.min(min, acc);
			max = Math.max(max, acc);
			return;
		}
		if (operators[0] > 0) {
			operators[0]--;
			search(next + 1, acc + operands[next]);
			operators[0]++;
		}
		if (operators[1] > 0) {
			operators[1]--;
			search(next + 1, acc - operands[next]);
			operators[1]++;
		}
		if (operators[2] > 0) {
			operators[2]--;
			search(next + 1, acc * operands[next]);
			operators[2]++;
		}
		if (operators[3] > 0) {
			operators[3]--;
			search(next + 1, acc / operands[next]);
			operators[3]++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line;

		br.readLine();
		line = br.readLine().split(" ");
		operands = new int[line.length];
		for (int i = 0; i < operands.length; i++)
			operands[i] = Integer.parseInt(line[i]);
		line = br.readLine().split(" ");
		for (int i = 0; i < operators.length; i++)
			operators[i] = Integer.parseInt(line[i]);
		search(1, operands[0]);
		br.close();
		bw.append(String.valueOf(max)).append('\n').append(String.valueOf(min)).append('\n');
		bw.flush();
		bw.close();
	}
}