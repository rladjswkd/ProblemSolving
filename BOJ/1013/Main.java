import java.io.*;
import java.util.regex.*;
import java.util.*;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile("(100+1+|01)+");

		while (t-- > 0)
			sb.append(p.matcher(br.readLine()).matches() ? "YES\n" : "NO\n");
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}