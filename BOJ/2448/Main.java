
/*
 * 삼각형 모두를 채운 상태에서 가운데 인덱스부터 마지막 인덱스까지 "역삼각형" 모양으로 빼기 + 재귀로 모두 적용
 * 
 * 예시로 나온 24 -> 맨 위부터 0으로 간주하고 12번째 행에서
 * 12번째 행부터 제거하는 별의 개수를 2개씩 줄여나가다 1개인 행까지만 별을 제거.
 * 나머지부분에서 이 과정을 재귀로 적용 -> 나머지 세 부분에 대해 어떻게 재귀적으로 호출하지? -> 꼭지점의 열 좌표 이용?
 * 
 * 높이, 꼭지점 열 좌표 이용
 * 
 * 가로 길이 : 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, ...
 * 세로 높이 : 1, 2, 3, 4, 5, 6,  7,  8, ...
 * 
 * 즉, 입력으로 받은 세로 높이 N에 대해, 2N - 1이 가로 길이가 된다.
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	/*
	 * bottom: 현재 처리 대상인 삼각형의 밑변의 위치(가장 큰 삼각형의 꼭지점을 1로, 밑변을 기존에 입력받은 height로 표현한다)
	 * topX: 현재 처리 대상인 삼각형의 꼭지점의 stars 내 행 인덱스
	 * topY: 현재 처리 대상인 삼각형의 꼭지점의 stars 내 열 인덱스
	 */
	private static void removeStar(char[][] stars, int bottom, int topX, int topY) {
		int span, realHeight = bottom - topX, midX = topX + realHeight / 2;

		if (realHeight < 3)
			return;
		for (int i = midX; i < bottom; i++) {
			span = realHeight / 2 - (i - midX);
			for (int j = 0; j < span; j++)
				stars[i][topY + j] = stars[i][topY - j] = ' ';
		}
		if (realHeight <= 3)
			return;
		removeStar(stars, midX, topX, topY);
		removeStar(stars, bottom, midX, topY - realHeight / 2);
		removeStar(stars, bottom, midX, topY + realHeight / 2);
	}

	public static void main(String[] args) throws IOException {
		int height = readInt(), width = 2 * height - 1, start, end;
		char[][] stars = new char[height][width];

		for (int i = 0; i < height; i++) {
			start = width / 2 - i;
			end = width / 2 + i;
			for (int j = 0; j < width; j++)
				stars[i][j] = start <= j && j <= end ? '*' : ' ';
		}
		removeStar(stars, height, 0, width / 2);
		for (char[] star : stars) {
			for (char ch : star)
				bw.write(ch);
			bw.newLine();
		}
		bw.flush();
		br.close();
		bw.close();
	}
}