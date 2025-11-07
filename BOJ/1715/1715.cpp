/*
1. 무조건 가장 작은 숫자를 두 개 더하면 된다. -> 정렬?

예외) 10 20 20 20 20 30 40 -> 30 20 20 20 30 40 -> 정렬 다시 필요

우선순위 큐!
*/

#include <iostream>
#include <queue>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int n;

	std::cin >> n;

	std::priority_queue<uint64_t> pq;

	while (n-- > 0)
	{
		uint64_t in;

		std::cin >> in;
		pq.push(-in);
	}

	uint64_t res = 0;

	while (pq.size() > 1)
	{
		uint64_t cur = pq.top();

		pq.pop();
		cur += pq.top();
		pq.pop();
		pq.push(cur);
		res += cur;
	}
	std::cout << -res;
	return 0;
}