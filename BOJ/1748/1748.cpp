/*
1-9 : 1 * 9
10-99 : 2 * 90
100-999 : 3 * 900
1000-9999 : 4 * 9000
...
*/
#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n, start = 1, digit = 1;
	uint64_t res = 0;

	std::cin >> n;
	while (n / start >= 10)
	{
		res += digit * 9 * start;
		digit++;
		start *= 10;
	}
	res += digit * (n - start + 1);
	std::cout << res;
	return 0;
}