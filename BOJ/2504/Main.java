/*
 * 괄호의 값을 계산하는 방법
 * 1. 재귀
 * 2. 스택(자료구조)
 *
 * (()[])와 같이 괄호 간의 덧셈에 대해 연산하기 위해 재귀가 아닌 스택을 사용할 땐 가장 바깥의 () 쌍에 대해 곱해줘야 하는 값을 함께 저장해줘야 한다.
 * 1. ( -> 스택에 2, 0을 저장
 * 2. ( -> 스택에 2, 0을 저장
 * 3. ) -> 스택의 맨 위의 첫 번째 값이 2인지 확인하고 맞다면 꺼낸다. 두 번째 값이 0이므로 1을 더해주고 첫 번째 값과 곱한다. 곱한 값을 스택의 맨 위의 두 번째 값에다 더해준다.
 * 4. [ -> 스택에 3, 0을 저장
 * 5. ] -> 스택의 맨 위의 첫 번째 값이 3인지 확인하고 맞다면 꺼낸다. 두 번째 값이 0이므로 1을 더해주고 첫 번째 값과 곱한다. 곱한 값을 스택의 맨 위의 두 번째 값에다 더해준다.
 * 6. ) -> 스택의 맨 위의 첫 번째 값이 2인지 확인하고 맞다면 꺼낸다. 두 번째 값이 0이 아니므로 첫 번째 값과 곱한다. 스택이 비어있으므로 다음 문자열을 탐색하고 다음 문자열이 없으므로 종료한다.
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static char[] input;
	private static Deque<int[]> stack = new ArrayDeque<>();
	private static int res = 0;
	private static boolean validFlag = true;

	// 최대값: 3^15 -> int로 충분
	public static void main(String[] args) throws IOException {
		int[] cur;

		input = br.readLine().toCharArray();
		for (char ch : input) {
			// 스택이 비어있을 때(맨 처음 괄호 또는 최상위 레벨의 괄호 쌍이 여러 개일 때 각 쌍의 여는 괄호일 때)
			if (stack.isEmpty()) {
				if (ch == '[')
					stack.addLast(new int[] { 3, 0 });
				else if (ch == '(')
					stack.addLast(new int[] { 2, 0 });
				// 스택이 비어있을 때 여는 괄호가 아닌 다른 괄호가 들어오면 무효
				else {
					validFlag = false;
					break;
				}
			}
			// 스택이 비어있지 않을 때
			else {
				if (ch == '[')
					stack.addLast(new int[] { 3, 0 });
				else if (ch == '(')
					stack.addLast(new int[] { 2, 0 });
				else if (ch == ']') {
					// ']'가 들어왔을 때, 스택의 맨 위는 무조건 '[' 여야 한다.
					if (stack.getLast()[0] == 3) {
						cur = stack.removeLast();
						// 스택의 맨 위에 있던 '['와 현재 ']' 사이에 아무 괄호 쌍이 없었어서 두 번째 값이 0이라면 1을 증가
						if (cur[1] == 0)
							cur[1]++;
						// '['을 뺀 후 스택이 비어있다면 '['와 ']'는 최상위 레벨의 괄호 쌍이므로 바로 res에 더해준다.
						if (stack.isEmpty())
							res += cur[0] * cur[1];
						// 스택이 비어있지 않다면 최상위 레벨의 괄호 쌍이 아니므로 그 다음 상위 레벨의 괄호 쌍의 두 번째 값에다가 더한다.
						else
							stack.getLast()[1] += cur[0] * cur[1];
					} else {
						validFlag = false;
						break;
					}
				} else if (ch == ')') {
					// ')'가 들어왔을 때, 스택의 맨 위는 무조건 '(' 여야 한다.
					if (stack.getLast()[0] == 2) {
						cur = stack.removeLast();
						// 스택의 맨 위에 있던 '('와 현재 ')' 사이에 아무 괄호 쌍이 없었어서 두 번째 값이 0이라면 1을 증가
						if (cur[1] == 0)
							cur[1]++;
						// '('을 뺀 후 스택이 비어있다면 '('와 ')'는 최상위 레벨의 괄호 쌍이므로 바로 res에 더해준다.
						if (stack.isEmpty())
							res += cur[0] * cur[1];
						// 스택이 비어있지 않다면 최상위 레벨의 괄호 쌍이 아니므로 그 다음 상위 레벨의 괄호 쌍의 두 번째 값에다가 더한다.
						else
							stack.getLast()[1] += cur[0] * cur[1];
					} else {
						validFlag = false;
						break;
					}
				}
				// 유효한 괄호가 아닌 문자열이 들어왔을 때 처리 -> 문제를 보면 필요 없는 코드인 것 같긴 하지만 안전한 코드를 위해!
				else {
					validFlag = false;
					break;
				}
			}
		}
		// validFlag는 true지만 stack이 비어있지 않은 경우에도 0 출력 -> ()(((((((
		System.out.println(validFlag && stack.isEmpty() ? res : 0);
		br.close();
	}
}
