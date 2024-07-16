#include <iostream>
#include <vector>
#include <queue>

#define INF 10000 * 1000

struct compare
{
	bool operator()(const std::pair<int, int> &p1, const std::pair<int, int> &p2)
	{
		return p1.second > p2.second;
	}
};

int n, c, costs[10000];

int read_int()
{
	int val;

	std::cin >> val;
	return val;
}

void solve(std::vector<std::pair<int, int>> graph[])
{
	bool processed[n];
	std::priority_queue<std::pair<int, int>, std::vector<std::pair<int, int>>, compare> pq;
	std::pair<int, int> cur;

	for (int i = 0; i < n; i++)
	{
		costs[i] = INF;
		processed[i] = false;
	}
	pq.emplace(c, 0);
	costs[c] = 0;
	while (!pq.empty())
	{
		cur = pq.top();
		pq.pop();
		if (processed[cur.first])
			continue;
		for (std::pair<int, int> neighbor : graph[cur.first])
			if (!processed[neighbor.first] && costs[cur.first] + neighbor.second < costs[neighbor.first])
				pq.emplace(neighbor.first, costs[neighbor.first] = costs[cur.first] + neighbor.second);
		processed[cur.first] = true;
	}
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int t = read_int(), d, count, interval, a, b;

	while (t-- > 0)
	{
		std::cin >> n >> d >> c;
		std::vector<std::pair<int, int>> graph[n];

		for (int i = 0; i < d; i++)
		{
			a = read_int() - 1;
			b = read_int() - 1;
			graph[b].emplace_back(a, read_int());
		}
		count = interval = 0;
		c--;
		solve(graph);
		for (int i = 0; i < n; i++)
		{
			if (costs[i] != INF)
			{
				count++;
				interval = std::max(interval, costs[i]);
			}
		}
		std::cout << count << ' ' << interval << '\n';
	}
	return 0;
}