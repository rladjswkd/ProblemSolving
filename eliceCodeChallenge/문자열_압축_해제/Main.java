
// Q는 한 자리의 정수, Q는 길이가 0 이상인 문자열
// 9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(99)))))))))))))))) -> 문자열로 직접 만들어서 스택에 다 저장하면 메모리 터질듯
// 숫자 타입 또한 int가 아닌 long을 써야한다.
// 11(18(72(7)))
// 11(18(72(7)12)34)56 -> 46
// 9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(99)))))))))))))))) -> 3,706,040,377,703,682
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static char[] arr;
	private static int index = 0;

	private static long solve() {
		long part = 0, k;

		while (index < arr.length) {
			if (arr[index] == '(') {
				part--;
				k = arr[index - 1] - 48;
				index++;
				part += k * solve();
			} else if (arr[index] == ')') {
				index++;
				break;
			} else {
				part++;
				index++;
			}
		}
		return part;
	}

	public static void main(String[] args) throws IOException {
		arr = br.readLine().toCharArray();
		br.close();
		System.out.println(solve());
	}
}