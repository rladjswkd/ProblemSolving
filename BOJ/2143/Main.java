import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Main {
	private static int t, seq1Length, seq2Length;
	private static long[] seq1, seq2;
	private static Map<Long, Integer> counter = new HashMap<>();

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		if (sign == 45)
			n = ~n + 1;
		return n;
	}

	public static void main(String[] args) throws IOException {
		long res = 0, value;

		t = readInt();
		// 첫 번째 배열의 누적 합 계산
		seq1 = new long[seq1Length = readInt()];
		seq1[0] = readInt();
		for (int i = 1; i < seq1Length; i++)
			seq1[i] = readInt() + seq1[i - 1];
		// 두 번째 배열의 누적 합 계산
		seq2 = new long[seq2Length = readInt()];
		seq2[0] = readInt();
		for (int i = 1; i < seq2Length; i++)
			seq2[i] = readInt() + seq2[i - 1];
		// 이분 탐색에 사용할 두 번째 배열의 모든 i, j쌍에 대해 i번째 요소부터 j번째 요소까지의 부분 합을 계산해 해시 맵에 저장
		for (int i = 0; i < seq2Length; i++) {
			value = seq2[i];
			counter.put(value, counter.getOrDefault(value, 0) + 1);
			for (int j = 0; j < i; j++) {
				value = seq2[i] - seq2[j];
				counter.put(value, counter.getOrDefault(value, 0) + 1);
			}
		}
		for (int i = 0; i < seq1Length; i++) {
			value = t - seq1[i];
			// seq1[0] ~ seq1[i] 까지의 합에 대해 계산
			res += counter.getOrDefault(value, 0);
			// seq1[j + 1] ~ seq1[i]까지의 합에 대해 계산
			for (int j = 0; j < i; j++)
				// seq1[0] ~ seq1[j] 까지를 value에 다시 더해준다.
				res += counter.getOrDefault(value + seq1[j], 0);
		}
		System.out.println(res);
	}
}