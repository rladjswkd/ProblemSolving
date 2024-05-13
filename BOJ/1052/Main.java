import java.io.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), k = readInt(), oneCount = Integer.MAX_VALUE, newCount = 0, total;

		while (true) {
			total = n;
			oneCount = 0;
			while (total > 0) {
				if ((total & 1) == 1)
					oneCount++;
				total /= 2;
			}
			if (k < oneCount) {
				n++;
				newCount++;
			} else
				break;
		}
		bw.append(String.valueOf(newCount)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}