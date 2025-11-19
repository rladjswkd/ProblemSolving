/*
			1		2		3
1			1
2					1
3			1		1		1
4			2				1
*/

#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int64_t dp[100001][3];

	dp[1][0] = dp[2][1] = dp[3][0] = dp[3][1] = dp[3][2] = 1;
	dp[1][1] = dp[1][2] = dp[2][0] = dp[2][2] = 0;
	for (int i = 4; i < 100001; i++)
	{
		dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % 1000000009;
		dp[i][1] = (dp[i - 2][0] + dp[i - 2][2]) % 1000000009;
		dp[i][2] = (dp[i - 3][0] + dp[i - 3][1]) % 1000000009;
	}

	int t;

	std::cin >> t;

	while (t-- > 0)
	{
		int n;

		std::cin >> n;
		std::cout << (dp[n][0] + dp[n][1] + dp[n][2]) % 1000000009 << '\n';
	}
	return 0;
}