```java
import java.io.IOException;
import java.math.BigInteger;

public class Main {
	private static int aCount, zCount, targetPosition;
	private static BigInteger stringCount;
	private static StringBuilder sb;

	private static int readInt() throws IOException {
		int n = System.in.read() & 15, cur;

		while (48 <= (cur = System.in.read()) && cur <= 57)
			n = (n << 3) + (n << 1) + (cur & 15);
		return n;
	}

	private static void calculateStringCount() {
		BigInteger factA = BigInteger.ONE, factZ = BigInteger.ONE;

		stringCount = BigInteger.ONE;
		for (int i = 1; i <= aCount + zCount; i++) {
			stringCount = stringCount.multiply(new BigInteger(String.valueOf(i)));
			if (i == aCount)
				factA = stringCount;
			if (i == zCount)
				factZ = stringCount;
		}
		stringCount = stringCount.divide(factA).divide(factZ);
	}

	private static int calculateGCD(int a, int b) {
		int c;

		while (b > 0) {
			c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	private static void solve() {
		int gcd, aCountDisjoint, zCountDisjoint;
		BigInteger q, aStringCount, zStringCount;

		if (aCount == 0 && zCount == 0)
			return;
		if (aCount == 0) {
			while (zCount-- > 0)
				sb.append('z');
			return;
		} else if (zCount == 0) {
			while (aCount-- > 0)
				sb.append('a');
			return;
		}
		gcd = calculateGCD(aCount, zCount);
		aCountDisjoint = aCount / gcd;
		zCountDisjoint = zCount / gcd;
		q = stringCount.divide(new BigInteger(String.valueOf(aCountDisjoint + zCountDisjoint)));
		aStringCount = q.multiply(new BigInteger(String.valueOf(aCountDisjoint)));
		zStringCount = q.multiply(new BigInteger(String.valueOf(zCountDisjoint)));
		if (aStringCount.compareTo(new BigInteger(String.valueOf(targetPosition))) >= 0) {
			stringCount = aStringCount;
			aCount--;
			sb.append('a');
			solve();
		} else {
			stringCount = zStringCount;
			zCount--;
			targetPosition = new BigInteger(String.valueOf(targetPosition)).subtract(aStringCount).intValue();
			sb.append('z');
			solve();
		}
	}

	public static void main(String[] args) throws IOException {
		aCount = readInt();
		zCount = readInt();
		targetPosition = readInt();
		calculateStringCount();
		if (stringCount.compareTo(new BigInteger(String.valueOf(targetPosition))) < 0)
			System.out.println(-1);
		else {
			sb = new StringBuilder();
			solve();
			System.out.println(sb.toString());
		}
	}
}
```
