#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

	int t, r;
	std::string str;

	std::cin >> t;
	while (t-- > 0)
	{
		std::cin >> r;
		std::cin >> str;
		for (char ch : str)
		{
			for (int i = 0; i < r; i++)
				std::cout << ch;
		}
		std::cout << '\n';
	}
	return 0;
}
