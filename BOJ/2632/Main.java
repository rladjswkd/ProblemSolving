import java.io.IOException;

public class Main {
	private static int targetSize, pieceCountA, pieceCountB;
	private static int[] piecesA, piecesB;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		int[] sizeCounterA, sizeCounterB;
		int count, size, res = 0;

		targetSize = readInt();
		piecesA = new int[pieceCountA = readInt()];
		piecesB = new int[pieceCountB = readInt()];
		size = 0;
		for (int i = 0; i < pieceCountA; i++)
			size += (piecesA[i] = readInt());
		sizeCounterA = new int[size + 1];
		// A 피자의 모든 조각을 더한 경우와 어떤 조각도 선택하지 않는 경우를 미리 카운트
		sizeCounterA[0] = sizeCounterA[size] = 1;
		size = 0;
		for (int i = 0; i < pieceCountB; i++)
			size += (piecesB[i] = readInt());
		sizeCounterB = new int[size + 1];
		// B 피자의 모든 조각을 더한 경우와 어떤 조각도 선택하지 않는 경우를 미리 카운트
		sizeCounterB[0] = sizeCounterB[size] = 1;

		// start부터 시작해 1, ... , pieceCountA - 1개의 연달은 조각의 크기를 계산
		// 최대 1000 * 999 * 2(A와 B) = 1,998,000개의 연산
		for (int start = 0; start < pieceCountA; start++) {
			count = 0;
			size = 0;
			while (count < pieceCountA - 1)
				sizeCounterA[size += piecesA[(start + count++) % pieceCountA]]++;
		}
		// B 피자에 대한 같은 연산 수행
		for (int start = 0; start < pieceCountB; start++) {
			count = 0;
			size = 0;
			while (count < pieceCountB - 1)
				sizeCounterB[size += piecesB[(start + count++) % pieceCountB]]++;
		}

		// sizesA를 순회하면서 sizesB의 값과 더해 targetSize를 만들 수 있는 경우의 수를 계산
		// size를 재활용해, A 피자에서 확인할 가장 큰 크기를 저장한다.
		size = Math.min(targetSize, sizeCounterA.length - 1);
		for (int i = 0; i <= size; i++)
			if (sizeCounterA[i] > 0 && targetSize - i < sizeCounterB.length && sizeCounterB[targetSize - i] > 0)
				res += sizeCounterA[i] * (sizeCounterB[targetSize - i]);
		System.out.println(res);
	}
}