#include <iostream>

int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	std::string s, t;

	std::cin >> s >> t;

	int front = 0, rear = t.size() - 1;
	bool reversed = false;

	while (rear - front + 1 != s.size())
	{
		if (reversed)
		{
			if (t[front] == 'B')
				reversed = false;
			front++;
		}
		else
		{
			if (t[rear] == 'B')
				reversed = true;
			rear--;
		}
	}

	int res = 1;

	if (reversed)
	{
		for (int i = rear; i >= front; i--)
		{
			if (s[rear - i] != t[i])
			{
				res = 0;
				break;
			}
		}
	}
	else
	{
		for (int i = front; i <= rear; i++)
		{
			if (s[i - front] != t[i])
			{
				res = 0;
				break;
			}
		}
	}
	std::cout << res;
	return 0;
}