/*
DP
대각선 이동은 필요 없다. -> 우하 또는 하우로 이동하는 것이 무조건 더 이득
*/
#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n, m, dp[1000][1000];

	std::cin >> n >> m;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			std::cin >> dp[i][j];
	for (int j = 1; j < m; j++)
		dp[0][j] += dp[0][j - 1];
	for (int i = 1; i < n; i++)
	{
		dp[i][0] += dp[i - 1][0];
		for (int j = 1; j < m; j++)
			dp[i][j] += std::max(dp[i - 1][j], dp[i][j - 1]);
	}
	std::cout << dp[n - 1][m - 1];
	return 0;
}