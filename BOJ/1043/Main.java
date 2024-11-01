import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static boolean[] detectors;
	private static Deque<int[]> q = new ArrayDeque<>();
	private static int[] ds;

	private static int readInt() throws IOException {
		int n = 0, cur;

		while (48 <= (cur = br.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static int find(int node) {
		int root = node, upper;

		while (ds[root] >= 0)
			root = ds[root];
		while (ds[node] >= 0) {
			upper = ds[node];
			ds[node] = root;
			node = upper;
		}
		return root;
	}

	private static void union(int u, int v) {
		int uRoot = find(u), vRoot = find(v);

		if (uRoot == vRoot)
			return;
		if (detectors[uRoot]) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else if (detectors[vRoot]) {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		} else if (uRoot < vRoot) {
			ds[uRoot] += ds[vRoot];
			ds[vRoot] = uRoot;
		} else {
			ds[vRoot] += ds[uRoot];
			ds[uRoot] = vRoot;
		}
	}

	private static int solve(List<int[]> parties) {
		int[] cur;
		int res = 0;

		while (!q.isEmpty()) {
			cur = q.removeFirst();
			union(cur[0], cur[1]);
		}
		for (int[] participants : parties) {
			res++;
			for (int participant : participants) {
				if (detectors[find(participant)]) {
					res--;
					break;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		int personCount = readInt(), partyCount = readInt(), initialDetectorCount = readInt();
		List<int[]> parties = new ArrayList<>();
		int[] participants;

		detectors = new boolean[personCount];
		for (int i = 0; i < initialDetectorCount; i++)
			detectors[readInt() - 1] = true;
		for (int i = 0; i < partyCount; i++) {
			parties.add(new int[readInt()]);
			participants = parties.get(i);
			for (int j = 0; j < participants.length; j++)
				participants[j] = readInt() - 1;
			for (int j = 1; j < participants.length; j++)
				q.addLast(new int[] { participants[j - 1], participants[j] });
		}
		ds = new int[personCount];
		for (int i = 0; i < personCount; i++)
			ds[i] = -1;
		bw.append(String.valueOf(solve(parties))).append('\n');
		bw.flush();
		br.close();
		bw.close();
	}
}