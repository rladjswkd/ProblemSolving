import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static int employeeCount, complimentCount;
	private static List<List<Integer>> graph;
	private static int[] res;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void solve(int employee, int upper) {
		res[employee] += res[upper];
		for (int sub : graph.get(employee))
			solve(sub, employee);
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		employeeCount = readInt();
		complimentCount = readInt();
		graph = new ArrayList<>();
		for (int i = 0; i < employeeCount; i++)
			graph.add(new ArrayList<>());
		// 사장의 직속 상사 관련 입력 버리기
		readInt();
		for (int i = 1; i < employeeCount; i++)
			graph.get(readInt() - 1).add(i);
		res = new int[employeeCount];
		for (int i = 0; i < complimentCount; i++)
			res[readInt() - 1] += readInt();
		solve(0, 0);
		for (int r : res)
			sb.append(r).append(' ');
		System.out.println(sb.toString());
	}
}