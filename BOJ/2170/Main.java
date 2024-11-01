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
		int n = readInt(), res = 0;
		List<int[]> lines = new ArrayList<>(n);
		int[] prev = new int[] { -1_000_000_001, -1_000_000_001 };

		for (int i = 0; i < n; i++)
			lines.add(new int[] { readInt(), readInt() });
		Collections.sort(lines, (a, b) -> a[0] - b[0]);
		for (int[] line : lines) {
			// 1. line이 prev를 포함 -> 불가능
			// 2. prev가 line을 포함
			if (prev[0] <= line[0] && line[1] <= prev[1])
				continue;
			// 3. prev의 오른쪽과 line의 왼쪽이 겹침
			else if (prev[0] <= line[0] && line[0] <= prev[1]) {
				res += line[1] - prev[1];
				prev[1] = line[1];
			}
			// 4. prev의 왼쪽과 line의 오른쪽이 겹침 -> 불가능
			// 5. 겹치는 부분이 없이 line이 prev의 오른쪽에 위치
			else if (prev[1] < line[0]) {
				res += line[1] - line[0];
				prev[0] = line[0];
				prev[1] = line[1];
			}
			// 6. 겹치는 부부닝 없이 line이 prev의 왼쪽에 위치 -> 불가능
		}
		System.out.println(res);
	}
}