/*
BFS + DP
*/
#include <iostream>
#include <deque>
#include <cstring>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n;
	int arr[1000];

	std::cin >> n;
	for (int i = 0; i < n; i++)
		std::cin >> arr[i];

	std::deque<int> dq;
	int count[1000];

	std::memset(count, 0, sizeof(count));
	count[n - 1] = -1;
	dq.push_back(0);
	count[0] = 0;
	while (!dq.empty() && count[n - 1] == -1)
	{
		int cur = dq.front();

		dq.pop_front();
		for (int i = cur + 1; i <= cur + arr[cur]; i++)
		{
			if (count[i] <= 0)
			{
				dq.push_back(i);
				count[i] = count[cur] + 1;
			}
		}
	}
	std::cout << count[n - 1];
	return 0;
}