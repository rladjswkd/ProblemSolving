import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t;
		int[] res = new int[11];

		res[1] = 1;
		res[2] = 2;
		res[3] = 4;
		for (int i = 4; i < 11; i++) {
			res[i] = res[i - 1] + res[i - 2] + res[i - 3];
		}

		t = Integer.parseInt(br.readLine());
		while (0 != t--) {
			bw.write(Integer.toString(res[Integer.parseInt(br.readLine())]));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}

/*
 * 1
 * 1
 */

/*
 * 2
 * 1 + 1
 * 2
 */

/*
 * 3
 * 1 + 1 + 1
 * 1 + 2
 * 2 + 1
 * 3
 * 
 * 1 + 1 + 1과 1 + 2는 1을 먼저 고르고 나머지 2를 표현하는 방법
 * 2 + 1은 2를 먼저 고르고 나머지 1을 표현하는 방법
 */

/*
 * 4
 * 1 + 1 + 1 + 1
 * 1 + 1 + 2
 * 1 + 2 + 1
 * 1 + 3
 * 2 + 1 + 1
 * 2 + 2
 * 3 + 1
 * 
 * 위에 4개는 1을 먼저 고르고 3을 표현하는 방법
 * 그 다음 2개는 2를 먼저 고르고 2를 표현하는 방법
 * 마지막은 3을 먼저 고르고 1을 표현하는 방법
 */

/*
 * 5
 * 1 + 1 + 1 + 1 + 1
 * 1 + 1 + 1 + 2
 * 1 + 1 + 2 + 1
 * 1 + 1 + 3
 * 1 + 2 + 1 + 1
 * 1 + 2 + 2
 * 1 + 3 + 1
 * 
 * 2 + 1 + 1 + 1
 * 2 + 1 + 2
 * 2 + 2 + 1
 * 2 + 3
 * 
 * 3 + 1 + 1
 * 3 + 2
 */

/*
 * 1: 1
 * 2: 2
 * 3: 4
 * 4: 1 + 2 + 4 = 7
 * 5: 7 + 4 + 2 = 13
 * 6: 13 + 7 + 4 = 24
 * 7: 24 + 13 + 7 = 44
 * 
 * 즉, 숫자가 n일 때 1을 먼저 고르고 n - 1을 1, 2, 3으로 표현하는 방법과
 * 2를 먼저 고르고 n - 2를 1, 2, 3으로 표현하는 방법,
 * 마지막으로 3을 먼저 고르고 n - 3을 1, 2, 3으로 표현하는 방법을 더하면 된다.
 */