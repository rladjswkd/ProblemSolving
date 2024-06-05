import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] numbers;

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
		int res = 0, front, rear;

		numbers = new int[readInt()];
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = readInt();
		Arrays.sort(numbers);
		// numbers 내의 모든 숫자들이 다른 두 수를 더해서 나올 수 있는 수인지 탐색하는 과정
		for (int target = 0; target < numbers.length; target++) {
			front = 0;
			rear = numbers.length - 1;
			while (front < rear) {
				// 아래 주석처리한 if문을 넣으면 틀림
				// if (numbers[front] > numbers[target])
				// break;
				// 왜 틀리나?
				// numbers[front] < numbers[rear]인 건 자명하고 이 둘을 더한 게 numbers[target]이어야 하는데
				// numbers[front]가 numbers[target]보다 크면 더 검사할 필요없지 않나?
				// numbers[front]와 numbers[target]은 아래와 같이 구성될 수 있다.
				// 음수(+0), 양수(+0)
				// 더 작은 음수(+0), 더 큰 음수(+0)
				// 더 작은 양수(+0), 더 큰 양수(+0)
				// 더 작은 음수 + 더 큰 음수일 때 이 둘을 더한 것이 numbers[target]보다 더 작을 수 있다.
				if (front == target)
					front++;
				else if (rear == target)
					rear--;
				else {
					if (numbers[front] + numbers[rear] < numbers[target])
						front++;
					else if (numbers[front] + numbers[rear] > numbers[target])
						rear--;
					// 두 수를 더해서 나올 수 있는 수라면 res에 1을 더하고 break
					else {
						res++;
						break;
					}
				}
			}
		}
		bw.append(String.valueOf(res)).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}