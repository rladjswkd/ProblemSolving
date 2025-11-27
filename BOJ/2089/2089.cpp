/*
#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n;

	std::cin >> n;

	if (n == 0)
		std::cout << 0;
	else
	{
		long long val = 1;

		while (!(std::abs(n) <= std::abs(val) && ((n < 0 && val < 0) || (n > 0 && val > 0))))
			val *= -2;

		std::cout << 1;

		long long cur = val / -2;

		while (std::abs(cur) > 0)
		{
			if (std::abs(n - (val + cur)) <= std::abs(n - val))
			{
				std::cout << 1;
				val += cur;
			}
			else
				std::cout << 0;
			cur /= -2;
		}
	}
	return 0;
}
*/
// 마지막 while -> val에 cur를 더한 값이 더하지 않은 값보다 n에 더 가깝다면(절대값) 1, 아니면 0 -> 확실하지 않은 방법 사용하지 말자.

/*
홀수는 그냥 짝수 + 1
22: 1101010
20: 10100
18: 10110
16: 10000
14: 10010
12: 11100
10: 11110
8: 11000
6: 11010
4: 100
2: 110
1: 1
0: 0
-1: 11
-2: 10
-4: 1100
-6: 1110
-8: 1000
-10: 1010

양수: 4, 16, 64, ... 와 같이 4의 배수를 더한 값이 n보다 작으면 계속 4를 곱한다. -> 같거나 커지면 그 값을 2로 나눈 값을 시작으로 4를 나눠가며 n보다 크거나 같다면 뺀다.
음수: 2, 8, 32, ... 와 같이 2부터 시작해 4를 곱해나가는 값이 n보다 크면 계속...

예외: 6

#include <iostream>
#include <cstring>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	bool set[32];

	std::memset(set, 0, sizeof(set));

	int n, idx = 0, start;
	long long val = 0;

	std::cin >> n;
	if (n > 0)
	{
		long long cur;

		if (n % 2 == 1)
		{
			cur = 1;
			idx = 0;
		}
		else
		{
			cur = 4;
			idx = 2;
		}

		while (val < n)
		{
			set[idx] = true;
			val += cur;
			idx += 2;
			cur *= 4;
		}
		cur /= 8;
		start = idx - 2;
		idx -= 3;
		while (idx > 0)
		{
			if (val - cur >= n)
			{
				set[idx] = true;
				val -= cur;
			}
			idx -= 2;
			cur /= 4;
		}
		for (; start >= 0; start--)
			std::cout << set[start];
	}
	else if (n < 0)
	{
	}

	return 0;
}
*/

#include <iostream>
#include <vector>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n;
	std::vector<int> v;

	std::cin >> n;
	if (n == 0)
		std::cout << 0;
	else
	{
		while (n != 0)
		{
			if ((n % -2) == 0)
			{
				v.push_back(0);
				n /= -2;
			}
			else
			{
				v.push_back(1);
				n = (n - 1) / -2;
			}
		}
	}
	for (std::vector<int>::reverse_iterator rit = v.rbegin(); rit != v.rend(); rit++)
		std::cout << *rit;
	return 0;
}