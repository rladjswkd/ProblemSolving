import java.io.IOException;

public class Main {
	private static int questionCount, studentCount, all, res;
	private static int[] students;

	private static int read() throws IOException {
		int n = System.in.read() & 15, c;

		while (48 <= (c = System.in.read()) && c <= 57)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static void solve(int start, int questions, int count) {
		if (questions == all) {
			res = Math.min(res, count);
			return;
		}
		if (count + 1 == res)
			return;
		for (int i = start; i < studentCount; i++)
			solve(i + 1, questions | students[i], count + 1);
	}

	public static void main(String[] args) throws IOException {
		int count, student;

		all = (1 << (questionCount = read())) - 1;
		students = new int[studentCount = read()];
		for (int i = 0; i < studentCount; i++) {
			count = read();
			student = 0;
			while (count-- > 0)	
				student |= 1 << read() - 1;
			students[i] = student;
		}
		res = studentCount + 1;
		solve(0, 0, 0);
		if (res == studentCount + 1)
			System.out.println(-1);
		else
			System.out.println(res);
	}
}