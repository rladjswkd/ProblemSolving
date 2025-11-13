#include <iostream>

int len = 0, idx = 0, res = 0, acc;
char arr[50];

int next()
{
	int c = 0;

	while (idx < len && '0' <= arr[idx] && arr[idx] <= '9')
		c = c * 10 + arr[idx++] - '0';
	return c;
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	while (std::cin >> arr[len])
		len++;

	bool flag = false;

	res = next();
	while (idx < len)
	{
		if (flag)
		{
			while (idx < len && arr[idx] == '+')
			{
				idx++;
				acc += next();
			}
			res -= acc;
			acc = 0;
			flag = false;
		}
		else
		{
			if (arr[idx++] == '-')
			{
				flag = true;
				acc = next();
			}
			else
				res += next();
		}
	}
	if (acc != 0)
		res -= acc;
	std::cout << res;
	return 0;
}