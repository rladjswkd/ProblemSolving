/*
이런 걸 슬라이딩 윈도우라고 하나
그리디?

A->B 중 최소 가격 도시에서 충전 B->C 중 최소 가격 도시에서 충전...

시작 도시 휘발유 값으로 출발 -> 다음 도시와 비교, 다음 도시가 더 싸면 그때부턴 다음 도시에서 주유한다고 판단 -> 반복
*/
#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int city_count;
	int64_t route_length[100000], gas_price[100000];

	std::cin >> city_count;
	for (int i = 0; i < city_count - 1; i++)
		std::cin >> route_length[i];
	for (int i = 0; i < city_count; i++)
		std::cin >> gas_price[i];

	int64_t min_price = 1000000000, res = 0;

	for (int i = 0; i < city_count; i++)
	{
		min_price = std::min(min_price, gas_price[i]);
		res += min_price * route_length[i];
	}
	std::cout << res;
	return 0;
}