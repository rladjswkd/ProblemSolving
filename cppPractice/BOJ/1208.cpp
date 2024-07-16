#include <iostream>
#include <vector>
#include <algorithm>

int s;
std::vector<int> left, right, left_sub, right_sub;
unsigned long long res = 0;

void calculate_left_sub(int idx, int acc)
{
	if (idx == left.size())
	{
		left_sub.push_back(acc);
		return;
	}
	calculate_left_sub(idx + 1, acc);
	calculate_left_sub(idx + 1, acc + left[idx]);
}

void calculate_right_sub(int idx, int acc)
{
	if (idx == right.size())
	{
		right_sub.push_back(acc);
		return;
	}
	calculate_right_sub(idx + 1, acc);
	calculate_right_sub(idx + 1, acc + right[idx]);
}

void solve()
{
	int p1 = 0, p2 = right_sub.size() - 1, val;
	unsigned long long left_count, right_count;

	while (p1 < left_sub.size() && 0 <= p2)
	{
		if (left_sub[p1] + right_sub[p2] < s)
			p1++;
		else if (left_sub[p1] + right_sub[p2] > s)
			p2--;
		else
		{
			left_count = right_count = 0;
			val = left_sub[p1];
			while (p1 < left_sub.size() && left_sub[p1] == val)
			{
				p1++;
				left_count++;
			}
			val = right_sub[p2];
			while (0 <= p2 && right_sub[p2] == val)
			{
				p2--;
				right_count++;
			}
			res += left_count * right_count;
		}
	}
	while (p1 < left_sub.size())
		if (left_sub[p1++] == s)
			res++;
	while (p2 >= 0)
		if (right_sub[p2--] == s)
			res++;
}
int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

	int n, half, val;

	std::cin >> n >> s;
	half = n >> 1;

	for (int i = 0; i < half; i++)
	{
		std::cin >> val;
		left.push_back(val);
	}
	for (int i = half; i < n; i++)
	{
		std::cin >> val;
		right.push_back(val);
	}
	calculate_left_sub(0, 0);
	calculate_right_sub(0, 0);
	std::sort(left_sub.begin(), left_sub.end());
	std::sort(right_sub.begin(), right_sub.end());
	solve();
	// s가 0일 땐, 아무것도 선택하지 않아서 0이 나오는 경우를 제외해야 한다.
	std::cout << ((s == 0 ? -1 : 0) + res);
	return 0;
}