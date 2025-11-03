/*
W와 H 각각 N개로 총 길이가 2N
단, 첫 번째 문자부터 임의의 위치의 문자까지 H의 갯수는 W의 갯수보다 많을 수 없다.

첫 번째는 무조건 W

N = 1 : WH
N = 2 : WWHH, WHWH
N = 3 : WWWHHH, WWHWHH, WHWWHH, WWHHWH, WHWHWH

- 전날의 앞에 W, 뒤에 H 붙이는 경우
- 전날의 앞에 WH를 붙이는 경우
- 전날의 뒤에 WH를 붙이는 경우 -> -1 해줘야 함(중복)

#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	uint64_t dp[30];

	dp[0] = 1;
	for (int i = 1; i < 30; i++)
		dp[i] = dp[i - 1] * 3 - 1;

	int n;

	std::cin >> n;
	while (n != 0)
	{
		std::cout << dp[n - 1] << '\n';
		std::cin >> n;
	}

	return 0;
}

2차원 DP
행 : W 개수
열 : H 개수

		0		1		2		3		4
0		0		0		0		0		0
1		1		1		0		0		0
2		1		2		2		0		0
3		1		3		5		5		0
4		1		4		9		14	14
*/

#include <iostream>
#include <cstring>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n;
	uint64_t dp[31][31];

	std::memset(dp, 0, sizeof(dp));
	dp[1][0] = dp[1][1] = 1;

	for (int w = 1; w < 31; w++)
	{
		dp[w][0] = 1;
		for (int h = 1; h <= w; h++)
			dp[w][h] = dp[w - 1][h] + dp[w][h - 1];
	}
	std::cin >> n;
	while (n > 0)
	{
		std::cout << dp[n][n] << '\n';
		std::cin >> n;
	}
	return 0;
}
