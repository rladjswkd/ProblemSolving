import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] cards;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = br.read();

		if (sign != '-')
			n = sign - 48;
		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == '-')
			n = -n;
		return n;
	}

	private static int binarySearch(int val) {
		int start = 0, end = cards.length - 1, low = end;

		while (start <= end) {
			low = (start + end) / 2;
			if (cards[low] < val)
				start = low + 1;
			else if (cards[low] > val)
				end = low - 1;
			else
				break;
		}
		return low;
	}

	public static void main(String[] args) throws IOException {
		int targetCount, target;
		StringBuilder sb = new StringBuilder();

		cards = new int[readInt()];
		for (int i = 0; i < cards.length; i++)
			cards[i] = readInt();
		Arrays.sort(cards);
		targetCount = readInt();
		while (targetCount-- > 0) {
			target = readInt();
			if (cards[binarySearch(target)] == target)
				sb.append(1).append(' ');
			else
				sb.append(0).append(' ');
		}
		bw.write(sb.append('\n').toString());
		bw.flush();
		br.close();
		bw.close();
	}
}