#include <iostream>
#include <cstring>
#include <vector>

int n, target;
std::vector<std::vector<int>> tree;

int search(int node)
{
	if (node == target || node == n)
		return 0;

	int ret = 0;

	for (int i = 0; i < tree[node].size(); i++)
		ret += search(tree[node][i] - 1);
	return ret == 0 ? 1 : ret;
}

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	std::cin >> n;

	for (int i = 0; i < n; i++)
		tree.push_back(std::vector<int>());

	int root = 0;

	for (int i = 0; i < n; i++)
	{
		int upper;

		std::cin >> upper;
		if (upper == -1)
			root = i;
		else
			tree[upper].push_back(i + 1);
	}
	std::cin >> target;
	std::cout << search(root);
	return 0;
}