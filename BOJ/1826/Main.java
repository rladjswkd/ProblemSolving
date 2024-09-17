import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {
	static class GasStation implements Comparable<GasStation> {
		int pos, fuel;

		GasStation(int pos, int fuel) {
			this.pos = pos;
			this.fuel = fuel;
		}

		@Override
		public int compareTo(GasStation o) {
			return pos - o.pos;
		}
	}

	static class Truck implements Comparable<Truck> {
		int pos, fuel;

		Truck(int pos, int fuel) {
			this.pos = pos;
			this.fuel = fuel;
		}

		Truck(GasStation gs) {
			this.pos = gs.pos;
			this.fuel = gs.fuel;
		}

		@Override
		public int compareTo(Truck o) {
			return o.fuel - fuel;
		}
	}

	private static int gasStationCount, destination, dist;
	private static GasStation[] gasStations;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int idx = 0, res = 0;
		PriorityQueue<Truck> pq = new PriorityQueue<>();

		gasStations = new GasStation[gasStationCount = readInt()];
		for (int i = 0; i < gasStationCount; i++)
			gasStations[i] = new GasStation(readInt(), readInt());
		destination = readInt();
		// dist는 도달 가능한 거리를 말하며, 지점 0에서 시작 시엔 초기 연료를 나타낸다.
		dist = readInt();
		// 주유소 정보를 위치 기준 오름차순으로 정렬
		Arrays.sort(gasStations);
		while (dist < destination) {
			while (idx < gasStationCount && gasStations[idx].pos <= dist)
				pq.add(new Truck(gasStations[idx++]));
			// 현재 연료로 다음에 위치한 주유소에 도달하지 못할 때
			if (pq.isEmpty())
				break;
			dist += pq.poll().fuel;
			res++;
		}
		System.out.println(dist < destination ? -1 : res);
	}
}