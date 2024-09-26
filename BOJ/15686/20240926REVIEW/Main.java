import java.io.IOException;

public class Main {
	private static int citySize, activeStoreCount, housesLength, storesLength, res;
	private static int[] houses, stores, activeStores;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void calculateCityChickenDist() {
		int dist, hx, hy, cur, total = 0;

		for (int h = 0; h < housesLength; h++) {
			hx = houses[h] / citySize;
			hy = houses[h] % citySize;
			dist = Integer.MAX_VALUE;
			for (int store : activeStores)
				if ((cur = Math.abs(hx - store / citySize) + Math.abs(hy - store % citySize)) < dist)
					dist = cur;
			total += dist;
		}
		if (total < res)
			res = total;
	}

	private static void selectActiveStores(int start, int count) {
		if (count == activeStoreCount) {
			calculateCityChickenDist();
			return;
		}
		for (int i = start; i <= storesLength - activeStoreCount + count; i++) {
			activeStores[count] = stores[i];
			selectActiveStores(i + 1, count + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		int c;

		houses = new int[(citySize = read()) << 1];
		stores = new int[13];
		activeStores = new int[activeStoreCount = read()];
		housesLength = storesLength = 0;
		for (int i = 0; i < citySize; i++) {
			for (int j = 0; j < citySize; j++) {
				if ((c = System.in.read()) == '1')
					houses[housesLength++] = i * citySize + j;
				else if (c == '2')
					stores[storesLength++] = i * citySize + j;
				System.in.read();
			}
		}
		res = Integer.MAX_VALUE;
		selectActiveStores(0, 0);
		System.out.println(res);
	}
}