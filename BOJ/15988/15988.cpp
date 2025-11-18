/*
1	1
2 11 2
3 111 21 12 3
4 1111 211 121 31 112 22 13
*/

#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int64_t dp[1000001];

	dp[1] = 1;
	dp[2] = 2;
	dp[3] = 4;
	for (int i = 4; i < 1000001; i++)
		dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;

	int t;

	std::cin >> t;
	while (t-- > 0)
	{
		int n;

		std::cin >> n;
		std::cout << dp[n] << '\n';
	}
	return 0;
}