#include <iostream>
#include <algorithm>

int n, c;
int arr[5000];
bool found;

bool searchValue(int start, int value) {
	int end = n - 1, mid;

	// lower bound
	while (start < end) {
		mid = start + ((end - start) / 2);
		if (arr[mid] < value)
			start = mid + 1;
		else
			end = mid;
	}
	return arr[start] == value;
}

void selectTwo(int start, int count, int weight, int selected) {
	if (weight == c) {
		found = true;
		return;
	}
	if (count == 2) {
		if (c - weight > 0)
			found = searchValue(start, c - weight);
		return;
	}
	for (int i = start; i < n; i++) {
		if (found)
			return;
		if ((selected & 1 << i) == 0)
			selectTwo(i + 1, count + 1, weight + arr[i], selected | 1 << i);
	}
}
int main()
{
	std::ios_base::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	std::cin >> n >> c;
	for (int i = 0; i < n; i++)
		std::cin >> arr[i];
	std::sort(arr, arr + n);
	found = false;
	selectTwo(0, 0, 0, 0);
	if (found)
		std::cout << 1 << std::endl;
	else
		std::cout << 0 << std::endl;
	return 0;
}