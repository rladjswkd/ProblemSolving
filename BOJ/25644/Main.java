
/*
 * 
 * 4 2 3 1 5
 * 
 * i번째와 i + 1 ~ n - 1번째를 모두 비교하며 계산하는 방식 O(n^2)은 시간초과가 날 것이다.
 * 
 * 반복문을 돌면서 현재 최솟값과 현재 최댓값을 계속 업데이트하며 최대 이익을 업데이트하는 방식으로 풀자.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

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
		int n = readInt(), min = Integer.MAX_VALUE, max = Integer.MAX_VALUE, price, profit = 0;

		for (int i = 0; i < n; i++) {
			price = readInt();
			if (price < min)
				min = max = price;
			else if (max < price) {
				max = price;
				if (profit < max - min)
					profit = max - min;
			}
		}
		bw.append(String.valueOf(profit)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}