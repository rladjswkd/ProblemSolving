import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/*
 * DP를 이용한 LIS 문제로 접근하기엔 입력의 크기가 너무 크다. -> 이분 탐색 활용
 */
public class Main {
	private static int[] a;
	private static List<Integer> il;

	// 찾는 값 val과 같거나 val보다 크면서 가장 작은 수의 인덱스를 반환해야 하므로,
	// il.get(low)와 val을 비교하는 로직을 잘 생각해야 한다.
	// 예를 들어, il의 값이 2, 9, 10이고 val이 3이면 이 함수는 9의 인덱스인 1을 반환해야 한다.
	private static int binarySearch(int val) {
		int start = 0, end = il.size() - 1, low = end;

		// while문의 조건식에 start <= end만 있고, start == end 이며 val < il.get(low)인 상황이라면
		// 무한 반복에 빠지기 때문에, start != low 조건도 while문의 조건식에 넣어줘야 한다.
		while (start <= end && start != low) {
			low = (start + end) / 2;
			if (val < il.get(low))
				end = low;
			else if (val > il.get(low))
				start = low + 1;
			else
				break;
		}
		return low;
	}

	private static void solve() {
		int max;

		il.add(a[0]);
		for (int i = 1; i < a.length; i++) {
			max = il.get(il.size() - 1);
			if (max == a[i])
				continue;
			if (max < a[i])
				il.add(a[i]);
			else
				il.set(binarySearch(a[i]), a[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input;

		a = new int[Integer.parseInt(br.readLine())];
		il = new ArrayList<>(a.length);
		input = br.readLine().split("\\s");
		for (int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(input[i]);
		solve();
		bw.append(String.valueOf(il.size())).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}

/*
 * 백준 플랫폼 기준, 입력을 아래처럼 처리하면 488ms로 훨씬 빨라지지만 이는 알고리즘의 효율과 상관 없으니 무시하자.
 * static int nextInt() throws IOException {
 * int n = 0, c;
 * while ((c = System.in.read()) < 48 || c > 57)
 * ;
 * do
 * n = (n << 1) + (n << 3) + (c & 15);
 * while ((c = System.in.read()) > 47 && c < 58);
 * return n;
 * }
 * 
 * public static void main(String[] args) throws IOException {
 * a = new int[nextInt()];
 * il = new ArrayList<>(a.length);
 * for (int i = 0; i < a.length; i++)
 * a[i] = nextInt();
 * solve();
 * System.out.println(il.size());
 * }
 */