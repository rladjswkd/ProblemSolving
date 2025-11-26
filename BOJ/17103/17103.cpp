#include <iostream>
#include <cstring>
#include <cmath>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	bool np[1000001];
	int p[78498], idx = 0;

	std::memset(np, 0, sizeof(np));
	for (int i = 2; i <= 1000; i++)
	{
		if (np[i])
			continue;
		p[idx++] = i;
		for (int j = i + i; j < 1000001; j += i)
			np[j] = true;
	}

	for (int i = 1001; i < 1000001; i++)
		if (!np[i])
			p[idx++] = i;
	int t;

	std::cin >> t;
	while (t-- > 0)
	{
		int n, count = 0;

		std::cin >> n;
		for (int i = 0; i < 78498 && p[i] <= n / 2; i++)
			if (!np[p[i]] && !np[n - p[i]])
				count++;
		std::cout << count << '\n';
	}
	return 0;
}