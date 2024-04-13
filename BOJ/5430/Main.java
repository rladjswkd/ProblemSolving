import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

// 투 포인터
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final String ERROR = "error";

	// static int read() throws IOException {
	// int c, n = System.in.read() & 15; // 숫자는 48 ~ 57의 값을 갖고, 여기서 48을 뺀 0 ~ 9 값을
	// 추출하는 과정이 & 15(하위 4개 비트)다.
	// while ((c = System.in.read()) > 32) // ASCII 32: space
	// n = (n << 3) + (n << 1) + (c & 15); // (n << 3) + (n << 1) == n * 10
	// return n;
	// }

	// 배열 입력을 문자열로 처리한 방식보다 메모리, 시간 측면 모두 다른 자바11 풀이보다 훨씬 효율적이다.
	private static int[] readArray(int length) throws IOException {
		int[] res = new int[length];
		int idx = 0, val, ch;

		br.read(); // [
		while (idx < res.length) {
			val = 0;
			while (48 <= (ch = br.read()) && ch <= 57)
				val = (val << 3) + (val << 1) + (ch - 48);
			res[idx++] = val;
		}
		br.readLine(); // \n
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine()), fp, rp, swap, remains;
		char[] ops;
		int[] arr;
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			ops = br.readLine().toCharArray();
			// 배열 입력 형태가 굳이..?
			arr = readArray(Integer.parseInt(br.readLine()));
			fp = 0;
			rp = arr.length - 1;
			remains = arr.length;
			for (char op : ops) {
				if (op == 'R') {
					swap = fp;
					fp = rp;
					rp = swap;
				} else {
					if (fp < rp)
						fp++;
					else
						fp--;
					remains--;
				}
			}
			if (remains < 0)
				sb.append(ERROR).append('\n');
			else if (remains == 0)
				sb.append("[]").append('\n');
			else {
				sb.append('[');
				if (fp < rp) {
					sb.append(arr[fp]);
					for (int i = fp + 1; i <= rp; i++)
						sb.append(',').append(arr[i]);
				} else {
					sb.append(arr[fp]);
					for (int i = fp - 1; i >= rp; i--)
						sb.append(',').append(arr[i]);
				}
				sb.append(']').append('\n');
			}
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}