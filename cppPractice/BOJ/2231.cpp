#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

	int n, cur, val = 0, res = 0;

	std::cin >> n;

	for (int i = n - 54; i <= n; i++)
	{
		cur = val = i;
		while (cur > 0)
		{
			val += cur % 10;
			cur /= 10;
		}
		if (val == n)
		{
			res = i;
			break;
		}
	}
	std::cout << res;
	return 0;
}