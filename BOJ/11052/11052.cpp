/*
1:	1
2:	11 2
3:	12 3
4:	13 22 4
5:	14 23 5
*/
#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n, dp[1001];

	std::cin >> n;
	for (int i = 1; i <= n; i++)
		std::cin >> dp[i];
	for (int i = 2; i <= n; i++)
		for (int j = 1; j <= i / 2; j++)
			dp[i] = std::max(dp[i], dp[i - j] + dp[j]);
	std::cout << dp[n];
	return 0;
}