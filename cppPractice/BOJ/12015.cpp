#include <iostream>
#include <vector>

std::vector<int> lis;

int find_lower_bound(int val)
{
	int start = 0, end = lis.size() - 1, mid;

	while (start < end)
	{
		mid = ((unsigned int)start + (unsigned int)end) >> 1;
		if (lis[mid] < val)
			start = mid + 1;
		else
			end = mid;
	}
	return lis[start] < val ? lis.size() : start;
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

	int n, val, idx;

	std::cin >> n;
	std::cin >> val;
	lis.push_back(val);
	for (int i = 1; i < n; i++)
	{
		std::cin >> val;
		if ((idx = find_lower_bound(val)) == lis.size())
			lis.push_back(val);
		else
			lis[idx] = val;
	}
	std::cout << lis.size();
	return 0;
}
