#include <iostream>
#include <cmath>
int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

	bool eratos[1001];
	int n, val, res = 0;

	eratos[0] = eratos[1] = false;
	for (int i = 2; i < 1001; i++)
		eratos[i] = true;
	for (int i = 2; i <= (int)sqrt(1001); i++)
	{
		if (!eratos[i])
			continue;
		for (int j = i + i; j < 1001; j += i)
			eratos[j] = false;
	}
	std::cin >> n;
	while (n-- > 0)
	{
		std::cin >> val;
		res += eratos[val];
	}
	std::cout << res;
	return 0;
}