#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	bool non_primes[1000001];

	non_primes[0] = non_primes[1] = true;

	for (int i = 2; i <= 1000; i++)
	{
		if (non_primes[i])
			continue;
		for (int j = i + i; j < 1000001; j += i)
			non_primes[j] = true;
	}

	int m, n;

	std::cin >> m >> n;

	for (int i = m; i <= n; i++)
		if (!non_primes[i])
			std::cout << i << '\n';
	return 0;
}