import java.io.*;
import java.util.*;

public class Main {
	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			return ~n + 1;
		return n;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), k = readInt();
		long acc = 0, res = 0;
		int[] arr = new int[n];
		Map<Long, Integer> mapper = new HashMap<>();

		for (int i = 0; i < n; i++)
			arr[i] = readInt();
		for (int i = 0; i < n; i++) {
			acc += arr[i];
			if (acc == k)
				res++;
			res += mapper.getOrDefault(acc - k, 0);
			mapper.put(acc, mapper.getOrDefault(acc, 0) + 1);
		}
		System.out.println(res);
	}
}