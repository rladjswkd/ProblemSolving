
/*
 * 1 1 -> 0
 * 1 2 -> 1
 * 1 3 -> 2
 * 1 4 -> 3
 * ...
 * 
 * 2 1 -> 0
 * 2 2 -> 0
 * 2 3 -> 2(2/3, 2/3, 1/3 + 1/3)
 * 2 4 -> 2(1/2, 1/2, 1/2, 1/2)
 * 2 5 -> 4(2/5, 2/5, 2/5, 2/5, 1/5 + 1/5)
 * 2 6 -> 4(1/3, 1/3, 1/3, 1/3, 1/3, 1/3)
 * 2 7 -> 6(2/7, 2/7, 2/7, 2/7, 2/7, 2/7, 1/7 + 1/7)
 * 2 8 -> 6(1/4, 1/4, 1/4, 1/4, 1/4, 1/4, 1/4, 1/4)
 * 8
 * 8
 * 10
 * 10
 * ...
 * 3 1 -> 0
 * 3 2 -> 1(1 + 1/2, 1 + 1/2)
 * 3 3 -> 0
 * 3 4 -> 3(3/4, 3/4, 3/4, 1/4 + 1/4 + 1/4)
 * 3 5 -> 4(3/5, 3/5, 3/5, (2/5 + 1/5), (2/5 + 1/5))
 * 3 6 -> 3(1/2, 1/2, 1/2, 1/2, 1/2, 1/2)
 * 3 7 -> 6(3/7, 3/7, 3/7, (3/7), (1/7 + 2/7), (3/7), (1/7 + 2/7))
 * 인원 수 - 최대 공약수
 */
import java.io.*;

public class Main {
	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int getGCD(int a, int b) {
		int c;

		while (b > 0) {
			c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		int sausageCount = readInt(), criticCount = readInt();

		System.out.println(criticCount - getGCD(sausageCount, criticCount));
	}
}