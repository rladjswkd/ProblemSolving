import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		List<Integer> input = new ArrayList<>();
		boolean[] primes;
		int maxInput = 0, p;

		while (input.isEmpty() || input.get(input.size() - 1) != 0) {
			input.add(Integer.parseInt(br.readLine()));
			maxInput = Math.max(maxInput, input.get(input.size() - 1));
		}
		input.remove(input.size() - 1);
		br.close();
		primes = new boolean[maxInput + 1];
		for (int i = 2; i < primes.length; i++)
			primes[i] = true;
		for (int i = 2; i < (int) Math.sqrt(primes.length) + 1; i++)
			if (primes[i])
				for (int j = i + i; j < primes.length; j += i)
					primes[j] = false;
		for (int v : input) {
			p = 3;
			for (; p < primes.length; p += 2) {
				if ((v - p) % 2 == 1 && primes[p] && primes[v - p]) {
					bw.write(v + " = " + p + " + " + (v - p) + "\n");
					break;
				}
			}
			if (p >= primes.length)
				bw.write("Goldbach's conjecture is wrong.");
		}
		bw.flush();
		bw.close();
	}
}