#include <iostream>
#include <vector>
#include <cstring>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	bool non_primes[1000001];
	std::vector<int> primes;

	std::memset(non_primes, 0, sizeof(non_primes));

	non_primes[0] = non_primes[1] = true;
	for (int i = 2; i < 1001; i++)
	{
		if (non_primes[i])
			continue;
		primes.push_back(i);
		for (int j = i + i; j < 1000001; j += i)
			non_primes[j] = true;
	}

	for (int i = 1001; i < 1000001; i++)
		if (!non_primes[i])
			primes.push_back(i);

	int input;

	std::cin >> input;

	while (input != 0)
	{
		int i = 1;

		for (; primes[i] <= input / 2; i++)
			if ((input - primes[i]) % 2 == 1 && !non_primes[input - primes[i]])
				break;
		if (primes[i] <= input / 2)
			std::cout << input << " = " << primes[i] << " + " << input - primes[i] << '\n';
		else
			std::cout << "Goldbach's conjecture is wrong.\n";
		std::cin >> input;
	}
	return 0;
}