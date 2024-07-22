import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int studentCount, compareCount, target, start, end;
	private static List<List<Integer>> orders = new ArrayList<>();
	private static List<List<Integer>> prevs = new ArrayList<>();
	private static boolean[] visited;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void countBetterOnes(int student) {
		for (int neighbor : prevs.get(student)) {
			if (!visited[neighbor]) {
				start++;
				visited[neighbor] = true;
				countBetterOnes(neighbor);
			}
		}
	}

	private static void countWorseOnes(int student) {
		for (int neighbor : orders.get(student)) {
			if (!visited[neighbor]) {
				end--;
				visited[neighbor] = true;
				countWorseOnes(neighbor);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int prev, post;

		studentCount = readInt();
		compareCount = readInt();
		target = readInt() - 1;
		for (int i = 0; i < studentCount; i++) {
			orders.add(new ArrayList<>());
			prevs.add(new ArrayList<>());
		}
		for (int i = 0; i < compareCount; i++) {
			orders.get(prev = readInt() - 1).add(post = readInt() - 1);
			prevs.get(post).add(prev);
		}
		visited = new boolean[studentCount];
		start = 1;
		end = studentCount;
		if (!(prevs.get(target).isEmpty()))
			countBetterOnes(target);
		if (!(orders.get(target).isEmpty()))
			countWorseOnes(target);
		System.out.println(new StringBuilder().append(start).append(' ').append(end));
	}
}