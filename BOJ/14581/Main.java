import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println(new StringBuilder().append(":fan::fan::fan:\n").append(":fan::")
				.append(new BufferedReader(new InputStreamReader(System.in)).readLine()).append("::fan:\n")
				.append(":fan::fan::fan:"));

	}
}