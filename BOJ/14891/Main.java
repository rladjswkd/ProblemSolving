import java.io.IOException;

public class Main {
	private static int[] gearWheels;
	private static int k;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int gearWheel, idx, rightCount, leftCount;

		gearWheels = new int[4];
		for (int i = 0; i < 4; i++) {
			gearWheel = 0;
			for (int bit = 0; bit < 8; bit++)
				gearWheel = (gearWheel << 1) | (System.in.read() & 15);
			gearWheels[i] = gearWheel;
			System.in.read();
		}
		k = read();
		while (k-- > 0) {
			idx = read() - 1;
			rightCount = 0;
			while (idx + rightCount + 1 < 4
					&& (gearWheels[idx + rightCount] & 1 << 5) >>> 5 != (gearWheels[idx + rightCount + 1] & 1 << 1) >>> 1)
				rightCount++;
			leftCount = 0;
			while (0 <= idx - leftCount - 1
					&& (gearWheels[idx - leftCount] & 1 << 1) >>> 1 != (gearWheels[idx - leftCount - 1] & 1 << 5) >>> 5)
				leftCount++;
			if (System.in.read() == '-') {
				System.in.read();
				System.in.read();
				gearWheels[idx] = (gearWheels[idx] & 1 << 7) >>> 7 | (gearWheels[idx] & 0b01111111) << 1;
				for (int i = idx + 1; i <= idx + rightCount; i += 2)
					gearWheels[i] = (gearWheels[i] & 1) << 7 | gearWheels[i] >>> 1;
				for (int i = idx + 2; i <= idx + rightCount; i += 2)
					gearWheels[i] = (gearWheels[i] & 1 << 7) >>> 7 | (gearWheels[i] & 0b01111111) << 1;
				for (int i = idx - 1; i >= idx - leftCount; i -= 2)
					gearWheels[i] = (gearWheels[i] & 1) << 7 | gearWheels[i] >>> 1;
				for (int i = idx - 2; i >= idx - leftCount; i -= 2)
					gearWheels[i] = (gearWheels[i] & 1 << 7) >>> 7 | (gearWheels[i] & 0b01111111) << 1;
			} else {
				System.in.read();
				gearWheels[idx] = (gearWheels[idx] & 1) << 7 | gearWheels[idx] >>> 1;
				for (int i = idx + 1; i <= idx + rightCount; i += 2)
					gearWheels[i] = (gearWheels[i] & 1 << 7) >>> 7 | (gearWheels[i] & 0b01111111) << 1;
				for (int i = idx + 2; i <= idx + rightCount; i += 2)
					gearWheels[i] = (gearWheels[i] & 1) << 7 | gearWheels[i] >>> 1;
				for (int i = idx - 1; i >= idx - leftCount; i -= 2)
					gearWheels[i] = (gearWheels[i] & 1 << 7) >>> 7 | (gearWheels[i] & 0b01111111) << 1;
				for (int i = idx - 2; i >= idx - leftCount; i -= 2)
					gearWheels[i] = (gearWheels[i] & 1) << 7 | gearWheels[i] >>> 1;
			}
		}
		System.out.println((gearWheels[0] & 1 << 7) >>> 7 | (gearWheels[1] & 1 << 7) >>> 6 | (gearWheels[2] & 1 << 7) >>> 5
				| (gearWheels[3] & 1 << 7) >>> 4);
	}
}