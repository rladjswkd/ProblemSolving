/*
 * ""내가 궁금한 변수가 다른 변수와 단조증가/단조감소 관계에 있음을 발견하는 게 이분탐색의 핵심""
 * 
 * 공유기 간 간격을 미리 정하고, 공유기들의 간격이 미리 정한 간격보다 클 때 공유기를 설치하는 방식으로 접근
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int[] houses;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int countRouters(int dist) {
		int currentHouse = houses[0];
		int count = 1;

		for (int i = 1; i < houses.length; i++) {
			if (dist <= houses[i] - currentHouse) {
				currentHouse = houses[i];
				count++;
			}
		}
		return count;
	}

	private static int binarySearch(int routerCount) {
		int minDist = 1;
		int maxDist = houses[houses.length - 1] - houses[0];
		int dist = maxDist;
		int count;
		int res = 0;

		while (minDist <= maxDist) {
			dist = (minDist + maxDist) / 2;
			count = countRouters(dist);
			// 현재 거리를 기준으로 설치할 수 있는 공유기의 수가 목표보다 적다면 거리를 줄인다(거리를 줄이면 더 많은 공유기를 설치할 수 있다).
			if (count < routerCount)
				maxDist = dist - 1;
			/*
			 * // 현재 거리를 기준으로 설치할 수 있는 공유기의 수가 목표보다 적다면 거리를 늘린다.
			 * else if (count > routerCount)
			 * minDist = dist + 1;
			 * // 현재 거리를 기준으로 설치할 수 있는 공유기의 수가 목표와 같다면 res를 업데이트하고, 더 먼 거리를 검사한다.
			 * else {
			 * res = dist;
			 * minDist = dist + 1;
			 * }
			 */
			// 현재 거리를 기준으로 설치할 수 있는 공유기의 수가 목표보다 많다면 거리를 늘린다.
			// 또한 이렇게 설치한 공유기 중 몇 개를 제거하면 목표 개수가 될 수 있으므로, res를 업데이트한다.
			else {
				res = dist;
				minDist = dist + 1;
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		int n = readInt(), c = readInt();

		houses = new int[n];
		for (int i = 0; i < n; i++)
			houses[i] = readInt();
		Arrays.sort(houses);
		bw.append(String.valueOf(binarySearch(c))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}