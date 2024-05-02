
/*
 * 투 포인터 
 * 정렬에 걸리는 시간이 O(NlogN)
 * 투 포인터를 이용해 탐색하는데 걸리는 시간이 O(N)
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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

	public static void main(String[] args) throws IOException {
		int front, rear, first = 1_000_000_001, second = 1_000_000_001;
		int[] liq;

		liq = new int[readInt()];
		for (int i = 0; i < liq.length; i++)
			liq[i] = readInt();
		Arrays.sort(liq);
		front = 0;
		rear = liq.length - 1;
		first = liq[front];
		second = liq[rear];
		while (front < rear) {
			if (Math.abs(liq[front] + liq[rear]) < Math.abs(first + second)) {
				first = liq[front];
				second = liq[rear];
			}
			// 두 용액의 특성값의 합이 음수일 때
			if (liq[front] + liq[rear] < 0)
				front++;
			// 두 용액의 특성값의 합이 양수일 때
			else
				rear--;
		}
		bw.write(new StringBuilder().append(first).append(' ').append(second).append('\n').toString());
		bw.flush();
		br.close();
		bw.close();
	}
}