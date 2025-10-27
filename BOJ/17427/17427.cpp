/*
1
1 2
1 3
1 2 4
1 5
1 2 3 6
1 7
1 2 4 8
1 3 9
*/

#include <iostream>
#include <cmath>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n;

	std::cin >> n;

	uint64_t res = 0;

	for (int i = 1; i <= n; i++)
		res += (n / i) * i;
	std::cout << res;
	return 0;
}