/*
백트래킹..?
dp..?
		6		10		13		9			8			1
0		0		6			16		23		28		33
1		6		10		19		25		31		29
2		0		16		23		28		33		32

0: 해당 값 선택 X
1: 해당 값을 연속으로 마신 잔 중 첫 번째 잔으로 선택
2: 해당 값을 연속으로 마신 잔 중 두 번째 잔으로 선택
*/
/*
#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int dp[10000][3];
	int wine[10000];
	int n;

	std::cin >> n;
	for (int i = 0; i < n; i++)
		std::cin >> wine[i];
	dp[0][0] = dp[0][2] = 0;
	dp[0][1] = wine[0];
	for (int i = 1; i < n; i++)
	{
		dp[i][0] = std::max(dp[i - 1][1], dp[i - 1][2]);
		dp[i][1] = dp[i - 1][0] + wine[i];
		dp[i][2] = dp[i - 1][1] + wine[i];
	}
	std::cout << std::max(std::max(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
	return 0;
}
*/
/*
반례
6
10
10
1
1
10
10
*/

#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int dp[10000][3];
	int wine[10000];
	int n;

	std::cin >> n;
	for (int i = 0; i < n; i++)
		std::cin >> wine[i];
	dp[0][0] = dp[0][2] = 0;
	dp[0][1] = wine[0];
	for (int i = 1; i < n; i++)
	{
		dp[i][0] = std::max(dp[i - 1][0], std::max(dp[i - 1][1], dp[i - 1][2]));
		dp[i][1] = dp[i - 1][0] + wine[i];
		dp[i][2] = dp[i - 1][1] + wine[i];
	}
	std::cout << std::max(std::max(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
	return 0;
}