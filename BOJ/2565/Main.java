
/*
 * 각 전깃줄을 선택하거나(남기거나) 선택하지 않거나(제거하거나).
 * 
 * 만약 한 전깃줄을 선택하였고 각 전봇대에서의 위치가 [a, b]라면,
 * 다음으로 선택할 전깃줄의 위치는 [0 ~ a - 1, 0 ~ b - 1] 또는 [a + 1 ~ 전봇대 끝 번호, b + 1 ~ 전봇대 끝 번호]여야 한다.
 * 
 * 풀이를 간단하게 하기 위해 전깃줄을 A 전봇대를 기준으로 정렬하면 도움이 되지 않을까?
 * -> 다음으로 선택할 전깃줄의 위치는 [a + 1 ~ 전봇대 끝 번호, b + 1 ~ 전봇대 끝 번호]가 된다.
 * 
 * 이때 전깃줄들은 A 전봇대 위치를 기준으로 오름차순 정렬이므로, B 전봇대 위치가 b + 1 이상인지 확인하면 된다.
 * 
 * -> Longest Increasing Subsequence 문제.
 * 
 * 
 * 
 * 
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static List<Integer> il = new ArrayList<>();

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int binarySearch(int val) {
		int start = 0, end = il.size() - 1, low = end;

		while (start <= end && start != low) {
			low = (start + end) / 2;
			if (il.get(low) < val)
				start = low + 1;
			else if (il.get(low) > val)
				end = low;
			else
				break;
		}
		return low;
	}

	public static void main(String[] args) throws IOException {
		int cableCount = readInt(), max, cur;
		PriorityQueue<int[]> pq = new PriorityQueue<>(cableCount, (a, b) -> a[0] - b[0]);

		for (int i = 0; i < cableCount; i++)
			pq.add(new int[] { readInt() - 1, readInt() - 1 });
		il.add(pq.poll()[1]);
		while (!pq.isEmpty()) {
			max = il.get(il.size() - 1);
			cur = pq.poll()[1];
			if (max == cur)
				continue;
			else if (max < cur)
				il.add(cur);
			else
				il.set(binarySearch(cur), cur);
		}
		bw.append(String.valueOf(cableCount - il.size())).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}