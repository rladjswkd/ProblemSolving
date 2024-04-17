// import java.io.*;

// public class Main {
// 	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
// 	private static StringBuilder sb = new StringBuilder();
// 	static int[] ds;

// 	private static int readInt() throws IOException {
// 		int n = 0, cur;

// 		while (48 <= (cur = br.read()) && cur <= 57)
// 			n = (n << 3) + (n << 1) + (cur & 15);
// 		return n;
// 	}

// 	public static void main(String[] args) throws IOException {
// 		int n = readInt(), m = readInt(), c, a, b;

// 		ds = new int[n + 1];
// 		for (int i = 1; i <= n; i++)
// 			ds[i] = i;
// 		for (int i = 0; i < m; i++) {
// 			c = readInt();
// 			a = readInt();
// 			b = readInt();

// 			switch (c) {
// 				case 0:
// 					union(a, b);
// 					break;
// 				case 1:
// 					sb.append((isUnion(a, b) ? "YES" : "NO") + "\n");
// 					break;
// 			}
// 		}
// 		bw.write(sb.toString());
// 		bw.flush();
// 		br.close();
// 		bw.close();
// 	}

// 	static int find(int x) {
// 		if (ds[x] == x)
// 			return x;
// 		return ds[x] = find(ds[x]);
// 	}

// 	static void union(int a, int b) {
// 		int A = find(a);
// 		int B = find(b);

// 		if (A == B)
// 			return;
// 		ds[B] = A;
// 	}

// 	static boolean isUnion(int a, int b) {
// 		int A = find(a);
// 		int B = find(b);

// 		if (A == B)
// 			return true;
// 		return false;
// 	}
// }

