import java.io.*;
import java.util.*;

public class Main {
  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  private static int[] ds;
  private static PriorityQueue<int[]> pq;
  private static int res = 0;

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

  private static int union(int u, int v, int w) {
    int uRoot = find(u), vRoot = find(v);

    if (uRoot == vRoot)
      return 0;
    res += w;
    if (ds[uRoot] < ds[vRoot]) {
      ds[uRoot] += ds[vRoot];
      ds[vRoot] = uRoot;
    } else {
      ds[vRoot] += ds[uRoot];
      ds[uRoot] = vRoot;
    }
    return 1;
  }
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()), edgeCount = 0;
    int[] edge;

    ds = new int[v];
    for (int i = 0; i < ds.length; i++)
      ds[i] = -1;
    pq = new PriorityQueue<>(e, (a1, a2) -> a1[2] - a2[2]);
    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      pq.add(new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())});
    }
    while (edgeCount < v - 1) {
      edge = pq.poll();
      edgeCount += union(edge[0], edge[1], edge[2]);
    }
    bw.append(String.valueOf(res)).append('\n');
    bw.flush();
    br.close();
    bw.close();
  }
}