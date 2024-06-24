import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	private static int n, res = 0;
	private static List<int[]> schedule = new ArrayList<>();

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int from, to;
		boolean possible = true;

		n = readInt();
		for (int i = 0; i < n; i++) {
			from = readInt();
			from = (from << 6) + (from << 5) + (from << 2) + readInt();
			to = readInt();
			to = (to << 6) + (to << 5) + (to << 2) + readInt();
			schedule.add(new int[] { from, to });
		}
		Collections.sort(schedule, (a, b) -> a[0] - b[0]);
		// 현재 심으려고 고려중인 꽃의 개화, 낙화 시기로 재활용
		from = to = 301;
		for (int[] flower : schedule) {
			/*
			 * flower가 피는 시기가 from 보다 이전(포함)
			 * -> flower가 지는 시기가 to 보다 이후라면 to를 flower[1]로 업데이트
			 * flower가 피는 시기가 from 보다 이후
			 ** flower가 피는 시기가 to 보다 이전(포함)
			 ** -> from을 to로 업데이트
			 ** -> to을 flower[1]로 업데이트
			 ** -> res 1 증가
			 ** flower가 피는 시기가 to 보다 이후
			 ** -> possible을 false로 설정하고 break
			 */
			if (flower[0] <= from)
				to = Math.max(to, flower[1]);
			else {
				if (flower[0] <= to) {
					from = to;
					to = Math.max(to, flower[1]);
					res++;
				} else {
					possible = false;
					break;
				}
			}
		}
		if (to <= 1130)
			possible = false;
		if (possible) {
			if (from <= 1130 && 1130 < to)
				res++;
			System.out.println(res);
		} else
			System.out.println(0);
	}
}