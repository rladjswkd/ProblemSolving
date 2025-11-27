#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n, s;

	std::cin >> n >> s;

	int arr[100000];

	for (int i = 0; i < n; i++)
		std::cin >> arr[i];

	int a = std::abs(arr[0] - s);

	for (int i = 0; i < n; i++)
	{
		int b = std::abs(arr[i] - s);

		while (b > 0)
		{
			int c = a % b;
			a = b;
			b = c;
		}
	}
	std::cout << a;
	return 0;
}