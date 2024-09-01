import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int size, switchCount;
	private static List<List<Integer>> switches;
	private static final int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	public static void main(String[] args) throws IOException {
		Deque<int[]> q = new ArrayDeque<>();
		int[] cur;
		// 0: 방문하지 않았고 스위치가 켜져있지 않음, 1: 방문하지 않았고 스위치가 켜짐, 2: 스위치가 켜져있으며 방문함
		int[][] barn;
		int barnArea, lx, ly, nx, ny, res = 1;

		size = readInt();
		switchCount = readInt();
		switches = new ArrayList<>(size);
		barnArea = size * size;
		// 왼쪽 상단에서 오른쪽 하단 순서로 0 ~ size * size - 1 번 방으로 간주한다.
		for (int i = 0; i < barnArea; i++)
			switches.add(new ArrayList<>());
		for (int i = 0; i < switchCount; i++)
			switches.get((readInt() - 1) * size + readInt() - 1).add((readInt() - 1) * size + readInt() - 1);
		barn = new int[size][size];
		barn[0][0] = 2;
		q.addLast(new int[] { 0, 0 });
		while (!q.isEmpty()) {
			cur = q.removeFirst();
			// 이미 대상 방의 불이 켜져있거나 방문한 상태라면 불을 다시 켜지 않는다.
			for (int target : switches.get(cur[0] * size + cur[1])) {
				if (barn[lx = target / size][ly = target % size] == 0) {
					barn[lx][ly] = 1;
					for (int i = 0; i < 4; i++) {
						nx = lx + dx[i];
						ny = ly + dy[i];
						if (0 <= nx && nx < size && 0 <= ny && ny < size && barn[nx][ny] == 2) {
							barn[lx][ly]++;
							q.addLast(new int[] { lx, ly });
							break;
						}
					}
					res++;
				}
			}
			for (int i = 0; i < 4; i++) {
				nx = cur[0] + dx[i];
				ny = cur[1] + dy[i];
				if (0 <= nx && nx < size && 0 <= ny && ny < size && barn[nx][ny] == 1) {
					barn[nx][ny]++;
					q.addLast(new int[] { nx, ny });
				}
			}
		}
		System.out.println(res);
	}
}