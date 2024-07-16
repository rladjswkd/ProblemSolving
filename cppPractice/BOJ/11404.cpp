#include <iostream>
#include <cmath>

#define INF 100 * 100000

int read_int()
{
	int val;

	std::cin >> val;
	return val;
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int cityCount = read_int(), busCount = read_int(), graph[cityCount][cityCount], u, v;

	for (int i = 0; i < cityCount; i++)
		for (int j = 0; j < cityCount; j++)
			graph[i][j] = i == j ? 0 : INF;
	for (int i = 0; i < busCount; i++)
	{
		u = read_int() - 1;
		v = read_int() - 1;
		graph[u][v] = std::min(read_int(), graph[u][v]);
	}
	for (int via = 0; via < cityCount; via++)
		for (int start = 0; start < cityCount; start++)
			for (int end = 0; end < cityCount; end++)
				graph[start][end] = std::min(graph[start][end], graph[start][via] + graph[via][end]);
	for (int i = 0; i < cityCount; i++)
	{
		for (int j = 0; j < cityCount; j++)
			std::cout << (graph[i][j] == INF ? 0 : graph[i][j]) << ' ';
		std::cout << '\n';
	}
	return 0;
}