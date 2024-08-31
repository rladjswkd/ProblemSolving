import java.io.IOException;

public class Main {
	private static int[] before, after;
	private static int beforeLength, afterLength;

	public static void main(String[] args) throws IOException {
		int ch, beforeFront, beforeRear, afterFront, afterRear;

		before = new int[100000];
		after = new int[100000];
		beforeLength = afterLength = beforeFront = afterFront = 0;
		while ((ch = System.in.read()) != '\n')
			before[beforeLength++] = ch;
		while ((ch = System.in.read()) != '\n')
			after[afterLength++] = ch;
		beforeRear = beforeLength - 1;
		afterRear = afterLength - 1;
		// beforeFront와 afterFront의 문자가 서로 같다면 둘 다 1씩 증가시키는 것을 반복
		// 반복문을 빠져나갈 때 beforeFront와 afterFront가 가리키는 문자는 서로 다르다.
		while (beforeFront < beforeLength && afterFront < afterLength && before[beforeFront] == after[afterFront]) {
			beforeFront++;
			afterFront++;
		}
		// beforeRear과 afterRear의 문자가 서로 같다면 둘 다 1씩 감소시키는 것을 반복
		//
		while (beforeFront <= beforeRear && afterFront <= afterRear && before[beforeRear] == after[afterRear]) {
			beforeRear--;
			afterRear--;
		}
		if (afterFront <= afterRear)
			System.out.println(afterRear - afterFront + 1);
		else
			System.out.println(0);
	}
}