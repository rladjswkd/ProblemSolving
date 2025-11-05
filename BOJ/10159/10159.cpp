// x번째 노드에서 시작해서 도달 가능한 다른 노드의 개수
// dfs로 하면 최대 2000만 이하
#include <iostream>
#include <cstring>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int node_count;

	std::cin >> node_count;

	bool graph[node_count][node_count];

	std::memset(graph, 0, sizeof(graph));

	int edge_count;

	std::cin >> edge_count;
	for (int i = 0; i < edge_count; i++)
	{
		int v1, v2;

		std::cin >> v1 >> v2;
		graph[v1 - 1][v2 - 1] = true;
	}
	for (int i = 0; i < node_count; i++)
		graph[i][i] = true;
	for (int via = 0; via < node_count; via++)
		for (int start = 0; start < node_count; start++)
			for (int end = 0; end < node_count; end++)
				if (graph[start][via] && graph[via][end])
					graph[start][end] = true;
	for (int i = 0; i < node_count; i++)
	{
		int res = 0;
		for (int j = 0; j < node_count; j++)
			if (!graph[i][j] && !graph[j][i])
				res++;
		std::cout << res << '\n';
	}
	return 0;
}