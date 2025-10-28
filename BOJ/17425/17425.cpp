#include <iostream>
#include <cmath>
#include <cstring>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	uint64_t t, n, dp[1000001];

	std::memset(dp, 0, sizeof(dp));
	for (int i = 1; i < 1000001; i++)
	{
		for (int j = i; j < 1000001; j += i)
			dp[j] += i;
		dp[i] += dp[i - 1];
	}
	std::cin >> t;
	while (t-- > 0)
	{
		std::cin >> n;
		std::cout << dp[n] << '\n';
	}
	return 0;
}