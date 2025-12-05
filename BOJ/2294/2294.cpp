/*
		0		1		2		3		4		5		6		7		8		9		10		11		12		13		14		15
1		0		1		2		3		4		5		6		7		8		9		10		11		12		13		14		15
5		0		1		2		3		4		1		2		3		4		5		2			3			4			5			6			3
12	0		1		2		3		4		1		2		3		4		5		2			3			1			2			3			3
*/

#include <iostream>
#include <cstring>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n, k, coins[100], dp[100001];

	std::cin >> n >> k;
	for (int i = 0; i < n; i++)
		std::cin >> coins[i];
	std::memset(dp, 0, sizeof(dp));
	for (int i = 0; i < n; i++)
	{
		dp[coins[i]] = 1;
		for (int j = coins[i] + 1; j <= k; j++)
		{
			if (dp[j - coins[i]] == 0)
				continue;
			if (dp[j] == 0 || dp[j] > dp[j - coins[i]] + 1)
				dp[j] = dp[j - coins[i]] + 1;
		}
	}
	if (dp[k] == 0)
		std::cout << -1;
	else
		std::cout << dp[k];
	return 0;
}