import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int e = Integer.parseInt(st.nextToken()) - 1;
		int s = Integer.parseInt(st.nextToken()) - 1;
		int m = Integer.parseInt(st.nextToken()) - 1;
		int year = e;

		while (year % 28 != s || year % 19 != m)
			year += 15;

		bw.write(Integer.toString(year + 1));
		bw.flush();
		bw.close();
		br.close();
	}
}
/*
 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 여야 하는데
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 이므로
 * 1씩 빼서 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 로 만들어 계산 후
 * 츨력 시 다시 1을 더해준다.
 */

/*
 * 13-15번 줄에서 -1이 아니라 각각 %15, %28, %19 연산을 하면(당연히 출력에 +1을 하지 않는다.)
 * 입력이 15 28 19일 때 0이 출력된다.
 * 따라서 이땐 18번 줄 반복 조건에 year == 0 을 OR 조건으로 추가해야 한다.
 */

/*
 * Java 8로 하면 76ms, Java 11로 하면 124ms.
 */