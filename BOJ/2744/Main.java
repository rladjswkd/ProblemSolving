import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int ch;

		while ((ch = System.in.read()) != 10) {
			if ('a' <= ch)
				sb.append((char) (ch - 32));
			else
				sb.append((char) (ch + 32));
		}
		System.out.println(sb.toString());
	}
}