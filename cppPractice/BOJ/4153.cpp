#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

	int a, b, c;

	std::cin >> a >> b >> c;
	while (a != 0 || b != 0 || c != 0)
	{
		a = a * a;
		b = b * b;
		c = c * c;
		if (a == b + c || b == a + c || c == a + b)
			std::cout << "right\n";
		else
			std::cout << "wrong\n";
		std::cin >> a >> b >> c;
	}
	return 0;
}