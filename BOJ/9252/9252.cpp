/*
	0	A	C	A	Y	K	P
0 0	0	0	0	0	0	0
C	0	0	1	1	1	1	1
A	0	1	1	2	2	2	2
P	0	1	1	2	2	2	3
C	0	1	2	2	2	2	3
A	0	1	2	3	3	3	3
K	0	1	2	3	3	4	4

*/
#include <iostream>
#include <cstring>
#include <string>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	std::string s1, s2;

	std::cin >> s1 >> s2;

	int dp[1001][1001];

	std::memset(dp, 0, sizeof(dp));

	for (int i = 1; i <= s1.size(); i++)
	{
		for (int j = 1; j <= s2.size(); j++)
		{
			if (s1[i - 1] == s2[j - 1])
				dp[i][j] = dp[i - 1][j - 1] + 1;
			else
				dp[i][j] = std::max(dp[i - 1][j], dp[i][j - 1]);
		}
	}

	int i = s1.size(), j = s2.size(), idx = dp[s1.size()][s2.size()];
	char str[1000];

	std::memset(str, 0, sizeof(str));
	std::cout << idx << '\n';
	while (dp[i][j] != 0)
	{
		if (dp[i - 1][j] == dp[i][j])
			i--;
		else if (dp[i][j - 1] == dp[i][j])
			j--;
		else
		{
			str[--idx] = s1[i - 1];
			i--;
			j--;
		}
	}
	if (dp[s1.size()][s2.size()] > 0)
		std::cout << str << '\n';
	return 0;
}