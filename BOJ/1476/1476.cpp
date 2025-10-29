#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int E, S, M, res = 1, e = 1, s = 1, m = 1;

	std::cin >> E >> S >> M;
	for (; res <= 7980 && !(e == E && s == S && m == M); res++)
	{
		e = (e + 1) % 16;
		if (e == 0)
			e = 1;
		s = (s + 1) % 29;
		if (s == 0)
			s = 1;
		m = (m + 1) % 20;
		if (m == 0)
			m = 1;
	}
	std::cout << res;
	return 0;
}