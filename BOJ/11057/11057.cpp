/*
브루트포스 -> 10^1000 -> 시간 초과
백트래킹 -> 앞 자리보다 작은 것만 연산 -> 그래도 시간 초과일듯
dp -> 2차원. 1000 * 10 -> 마지막으로 선택한 숫자

	0		1		2		3		4		5		6		7		8		9
1	1		1		1		1		1		1		1		1		1		1
2	1		2		3		4		5		6		7		8		9		10
3	1		3		6		10	15	21	28	36	45	55

마지막으로 선택할 숫자의 앞 숫자는 마지막으로 선택할 숫자보다 작거나 같은 숫자면 뭐든 가능하다.
*/

#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n, dp[1000][10];

	std::cin >> n;
	for (int i = 0; i < 10; i++)
		dp[0][i] = 1;
	for (int i = 1; i < n; i++)
	{
		dp[i][0] = 1;
		for (int j = 1; j < 10; j++)
			dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 10007;
	}

	int res = 0;

	for (int i = 0; i < 10; i++)
		res += dp[n - 1][i];
	std::cout << res % 10007;
	return 0;
}