#include <iostream>
#include <vector>

int n;
int matrices[500][2], dp[500][500];

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

	int d1, d2, lowest;

	std::cin >> n;
	for (int i = 0; i < n; i++)
		std::cin >> matrices[i][0] >> matrices[i][1];
	for (int i = n - 2; i >= 0; i--)
	{
		dp[i][i + 1] = matrices[i][0] * matrices[i][1] * matrices[i + 1][1];
		for (int j = i + 2; j < n; j++)
		{
			lowest = std::min(
					dp[i][j - 1] + matrices[i][0] * matrices[j][0] * matrices[j][1],
					dp[i + 1][j] + matrices[i][0] * matrices[i][1] * matrices[j][1]);
			for (int b = i + 1; b < j - 1; b++)
				lowest = std::min(lowest, dp[i][b] + dp[b + 1][j] + matrices[i][0] * matrices[b][1] * matrices[j][1]);
			dp[i][j] = lowest;
		}
	}
	std::cout << dp[0][n - 1];
}