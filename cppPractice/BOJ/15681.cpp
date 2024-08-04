#include <iostream>
#include <vector>

std::vector<int> graph[100000];
int dp[100000];

int count_nodes(int node)
{
	int count = 0;

	if (dp[node] > 0)
		return dp[node];
	dp[node] = 1;
	for (int sub : graph[node])
		// dp[sub]이 0이면 sub 노드에 아직 방문하지 않은 것.
		if (dp[sub] == 0)
			count += count_nodes(sub);
	return dp[node] += count;
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n, r, q, u, v;

	std::cin >> n >> r >> q;
	r--;
	for (int i = 1; i < n; i++)
	{
		std::cin >> u >> v;
		graph[u - 1].push_back(v - 1);
		graph[v - 1].push_back(u - 1);
	}
	count_nodes(r);
	for (int i = 0; i < q; i++)
	{
		std::cin >> u;
		std::cout << dp[u - 1] << '\n';
	}
	return 0;
}