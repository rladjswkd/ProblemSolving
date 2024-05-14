
/*
 * 방법 1. 에라토스테네스의 체 -> 8자리면 99999999까지 판별해야 하는데 너무 크기가 크다.
 * 
 * 방법 2. 최대 입력값의 제곱근까지 소수를 판별해놓은 다음, 자리수 N이 주어지면 왼쪽부터 1자리, 2자리, ... N자리에 해당하는 수에 대해 자신의 제곱근까지 나눠보기
 * -> 모두 나눠지지 않는다면 그 수는 소수다.
 * 	판별한 모든 수들은 해시셋에 저장해 다음에 다시 판별하지 않아도 결과를 확인할 수 있게 하기
 * 	또한 첫 번째 자리도 무조건 소수여야 하므로 2, 3, 5, 7로 시작하는 숫자만 검사하기
 * 
 * 방법2로 통과했지만, 미리 최대 입력값의 제곱근까지 소수를 판별해놓기, 판별한 모든 수를 해시셋에 저장해 중복 판별 연산 제거하기 등을 적용하지 않고,
 * 검사해야 하는 모든 수(모든 N자리 수의 왼쪽부터 1자리, 2자리, ... N자리)에 대해 2부터 그 수의 제곱근까지를 무식하게 순회하며 그 수가 순회하는 값으로 나누어 떨어지는지를 확인하는 소수 판별 방법을 사용한 코드가
 * 더 빠르다..
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int n;
	private static Set<Integer> primeChecker = new HashSet<>();
	private static List<Integer> primes = new ArrayList<>();
	private static StringBuilder sb = new StringBuilder();

	private static boolean isPrimeNumber(int number) {
		for (int prime : primes)
			if (number % prime == 0)
				return false;
		return true;
	}

	private static void solve(int number, int depth) {
		boolean primeFlag = primeChecker.contains(number) || isPrimeNumber(number);

		if (depth == n && primeFlag) {
			sb.append(number).append('\n');
			return;
		} else if (!primeFlag)
			return;
		for (int digit = 0; digit <= 9; digit++)
			solve(number * 10 + digit, depth + 1);
	}

	public static void main(String[] args) throws IOException {
		boolean[] composites = new boolean[10001];
		int bound = (int) Math.sqrt(10000) + 1;

		n = br.read() & 15;
		composites[0] = composites[1] = true;
		for (int i = 2; i <= bound; i++) {
			if (!composites[i]) {
				primeChecker.add(i);
				primes.add(i);
				for (int j = i + i; j < composites.length; j += i)
					composites[j] = true;
			}
		}
		for (int i = bound + 1; i < composites.length; i++) {
			if (!composites[i]) {
				primeChecker.add(i);
				primes.add(i);
			}
		}
		solve(2, 1);
		solve(3, 1);
		solve(5, 1);
		solve(7, 1);
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}