#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <functional>

std::vector<std::pair<int, int>> jewels;
std::vector<int> knapsacks;
std::priority_queue<int> pq;

unsigned long long solve()
{
	int idx = 0;
	unsigned long long res = 0;

	for (int capacity : knapsacks)
	{
		while (idx < jewels.size())
		{
			if (jewels[idx].first <= capacity)
				pq.emplace(jewels[idx++].second);
			else
				break;
		}
		if (!pq.empty())
		{
			res += pq.top();
			pq.pop();
		}
	}
	return res;
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);

	int n, k, first, second, c;

	std::cin >> n >> k;
	for (int i = 0; i < n; i++)
	{
		std::cin >> first >> second;
		jewels.push_back(std::make_pair(first, second));
	}
	for (int i = 0; i < k; i++)
	{
		std::cin >> c;
		knapsacks.push_back(c);
	}
	std::sort(jewels.begin(), jewels.end());
	std::sort(knapsacks.begin(), knapsacks.end());
	std::cout << solve();
}