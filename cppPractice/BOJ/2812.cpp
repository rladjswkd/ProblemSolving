#include <iostream>

int read_int()
{
	int val;

	std::cin >> val;
	return val;
}

int read_byte()
{
	char ch;

	std::cin >> ch;
	return ch;
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n = read_int(), k = read_int(), seq[n], stack[n], top = -1, target_size = n - k, idx;

	for (int i = 0; i < n; i++)
		seq[i] = read_byte() - 48;
	for (idx = 0; idx < n && k > 0; idx++)
	{
		if (top > -1)
			while (k > 0 && top > -1 && stack[top] < seq[idx])
			{
				top--;
				k--;
			}
		stack[++top] = seq[idx];
	}
	if (top + 1 >= target_size)
		for (int i = 0; i < target_size; i++)
			std::cout << stack[i];
	else
	{
		for (int i = 0; i <= top; i++)
			std::cout << stack[i];
		for (; idx < n; idx++)
			std::cout << seq[idx];
	}
	return 0;
}