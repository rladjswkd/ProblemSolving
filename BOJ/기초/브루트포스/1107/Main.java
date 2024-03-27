import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	static int countBtn(int channel, int brokenBtns) {
		int res = 0;

		if (channel == 0 && (brokenBtns & 1) == 0)
			res = 1;
		while (channel != 0 && ((brokenBtns & 1 << channel % 10) >> channel % 10) == 0) {
			channel /= 10;
			res++;
		}
		return channel == 0 ? res : 0;
	}

	static int solve(int targetChannel, int brokenBtns) {
		int res = Math.abs(targetChannel - 100);
		int btnClick;

		for (int channel = 0; channel < 1000000; channel++) {
			btnClick = countBtn(channel, brokenBtns);
			if (0 < btnClick)
				res = Math.min(res, btnClick + Math.abs(targetChannel - channel));
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int targetChannel, brokenCount, brokenBtns = 0;

		targetChannel = Integer.parseInt(br.readLine());
		brokenCount = Integer.parseInt(br.readLine());
		if (brokenCount > 0) {
			st = new StringTokenizer(br.readLine());
			while (0 < brokenCount--)
				brokenBtns |= 1 << Integer.parseInt(st.nextToken());
		}
		System.out.println(solve(targetChannel, brokenBtns));
	}
}

/*
 * 0 ~ 500000 까지 모든 채널에 대해 아래 내용을 확인한다.
 * 1. 해당 채널이 현재 채널이거나 번호를 입력해 바로 이동할 수 있는지
 * 1-1. 불가능하다면 다음 채널로 넘어간다.
 * 1-2. 가능하다면 해당 채널에서 이동하려는 채널까지 +, -로 이동하는 데 필요한 클릭 수와 번호 클릭 수를 확인하고 최소 클릭 수
 * 업데이트
 */