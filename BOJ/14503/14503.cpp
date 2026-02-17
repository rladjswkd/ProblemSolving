/*
구현
N, M의 범위가 작으니 브루트포스 가능

청소된 칸 : 2로 설정
작동을 멈추는 플래그 : r을 활용 -> < 0이면 종료

room[r][c]에서 r, c는 항상 유효한 인덱스(가장자리가 벽이므로)
*/

#include <iostream>
#include <vector>

int main()
{
	int n, m, r, c, d, res = 0;
	int room[50][50];
	int dx[]{-1, 0, 1, 0}, dy[]{0, 1, 0, -1};

	std::cin >> n >> m >> r >> c >> d;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			std::cin >> room[i][j];

	while (room[r][c] != 1)
	{
		if (room[r][c] == 0)
		{
			room[r][c] = 2;
			res++;
			continue;
		}
		if (room[r - 1][c] && room[r + 1][c] && room[r][c - 1] && room[r][c + 1])
		{
			const int back = (d + 2) % 4;
			r += dx[back];
			c += dy[back];
		}
		else
		{
			d = (d + 3) % 4;
			if (room[r + dx[d]][c + dy[d]] == 0)
			{
				r += dx[d];
				c += dy[d];
			}
		}
	}
	std::cout << res;
	return 0;
}