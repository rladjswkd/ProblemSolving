/*
2와 5로 나누어 떨어지지 않는다. = 마지막 자리가 2, 4, 5, 6, 8, 0이 아니다.
1, 3, 7, 9

반복문?
*/
#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	unsigned long long input, digit, target;

	while (std::cin >> input)
	{
		digit = 1;
		target = 1;
		while (target % input != 0)
		{
			digit++;
			target = (target * 10 + 1) % input;
		}
		std::cout << digit << '\n';
	}
	return 0;
}