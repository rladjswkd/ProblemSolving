#include <iostream>

char board[50][50];

int check(int x, int y)
{
	int w = 0, b = 0;

	for (int i = x; i < x + 8; i++)
	{
		for (int j = y; j < y + 8; j++)
		{
			if ((i + j) % 2 == 0)
			{
				if (board[i][j] == 'W')
					b++;
				else
					w++;
			}
			else
			{
				if (board[i][j] == 'W')
					w++;
				else
					b++;
			}
		}
	}
	return std::min(w, b);
}
int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n, m;

	std::cin >> n >> m;

	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			std::cin >> board[i][j];

	int res = INT32_MAX;

	for (int i = 0; i <= n - 8; i++)
		for (int j = 0; j <= m - 8; j++)
			res = std::min(res, check(i, j));
	std::cout << res;
	return 0;
}