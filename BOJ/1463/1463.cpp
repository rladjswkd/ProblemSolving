/*
BFS + visited, DP?
*/
#include <iostream>
#include <cstring>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int dp[1000001];

	for (int i = 0; i < 1000001; i++)
		dp[i] = INT32_MAX;
	int n;

	std::cin >> n;
	dp[n] = 0;
	for (int i = n; i >= 2; i--)
	{
		if (i % 3 == 0)
			dp[i / 3] = std::min(dp[i / 3], dp[i] + 1);
		if (i % 2 == 0)
			dp[i / 2] = std::min(dp[i / 2], dp[i] + 1);
		dp[i - 1] = std::min(dp[i - 1], dp[i] + 1);
	}
	std::cout << dp[1];
	return 0;
}