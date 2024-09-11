import java.io.IOException;

public class Main {
	private static int n, length;
	private static int[] lis;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void updateLIS(int val) {
		int start = 0, end = length - 1, mid;

		while (start < end) {
			mid = (start + end) >>> 1;
			if (lis[mid] < val)
				start = mid + 1;
			else
				end = mid;
		}
		if (lis[start] < val)
			lis[length++] = val;
		else
			lis[start] = val;
	}

	public static void main(String[] args) throws IOException {
		lis = new int[n = readInt()];
		length = 0;
		// 책은 무조건 한 권 이상이므로 첫 번째 책은 lis[0]에 그냥 추가한다.
		// 이로 인해 updateLIS에서 lis가 빈 배열인지 아닌지 확인하지 않아도 된다.
		lis[length++] = readInt();
		// 같은 번호의 책은 존재하지 않으므로 upper bound를 쓰든 lower bound를 쓰든 같다.
		for (int i = 1; i < n; i++)
			updateLIS(readInt());
		System.out.println(n - length);
	}
}