
// (100~1~|01)~
import java.io.IOException;

public class Main {
	private static int[] seq;
	private static int length;

	public static void main(String[] args) throws IOException {
		int ch, idx;
		boolean res = true;

		seq = new int[150];
		length = 0;
		while ((ch = System.in.read()) == 48 || ch == 49)
			seq[length++] = ch - 48;
		idx = 0;
		while (idx < length && res) {
			if (seq[idx] == 1) {
				if (++idx >= length || seq[idx] != 0)
					res = false;
				if (++idx >= length || seq[idx] != 0)
					res = false;
				while (idx < length && seq[idx] == 0)
					idx++;
				if (idx >= length || seq[idx] != 1)
					res = false;
				while (idx < length && seq[idx] == 1)
					idx++;
			} else {
				if (idx + 1 < length) {
					if (seq[idx + 1] == 1)
						idx += 2;
					else {
						if (idx - 2 >= 0 && seq[idx - 1] == 1 && seq[idx - 2] == 1)
							idx--;
						else
							res = false;
					}
				} else
					res = false;
			}
		}
		System.out.println(res ? "SUBMARINE" : "NOISE");
	}
}