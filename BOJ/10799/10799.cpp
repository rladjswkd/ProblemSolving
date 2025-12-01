#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int res = 0, stack = 0;
	char prev = ' ', ch = ' ';

	while (std::cin >> ch)
	{
		if (ch == '(')
			stack++;
		else if (prev == '(')
			res += --stack;
		else
		{
			res++;
			stack--;
		}
		prev = ch;
	}
	std::cout << res;
	return 0;
}
