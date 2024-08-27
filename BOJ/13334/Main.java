import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {
	static class Position implements Comparable<Position> {
		int house, office;

		Position(int house, int office) {
			this.house = house;
			this.office = office;
		}

		@Override
		public int compareTo(Position o) {
			return office - o.office;
		}
	}

	private static int n, d;
	private static Position[] seq;

	private static int readInt() throws IOException {
		int n = 0, cur, sign = System.in.read();

		if (sign != 45)
			n = sign & 15;
		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return sign == 45 ? ~n + 1 : n;
	}

	public static void main(String[] args) throws IOException {
		// lStart: 선분 l의 시작 지점을 추적한다.
		int h, o, lStart = 100_000_001, lEnd, res = 0;
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> a.house - b.house);

		seq = new Position[n = readInt()];
		for (int i = 0; i < n; i++) {
			h = readInt();
			o = readInt();
			if (h < o)
				seq[i] = new Position(h, o);
			else
				seq[i] = new Position(o, h);
			if (h < lStart)
				lStart = h;
		}
		Arrays.sort(seq);
		lEnd = lStart + (d = readInt());
		for (Position p : seq) {
			if (p.office <= lEnd)
				pq.add(p);
			else {
				while (!pq.isEmpty() && pq.peek().house < lStart)
					pq.poll();
				res = Math.max(res, pq.size());
				pq.add(p);
				lEnd = p.office;
				lStart = lEnd - d;
			}
		}
		// lEnd가 가장 큰 p.office보다 커서 for문 내의 else 문에 들어가지 못한 경우를 위한 코드
		// 모든 정보 쌍이 가장 처음의 L에 모두 포함되어 for문이 pq에 새로운 쌍을 추가하기만 하고 res가 업데이트되지 않는 경우도 이
		// 코드에서 처리할 수 있다.
		while (!pq.isEmpty() && pq.peek().house < lStart)
			pq.poll();
		res = Math.max(res, pq.size());
		System.out.println(res);
	}
}