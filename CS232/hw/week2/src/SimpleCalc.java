import java.util.Scanner;

public class SimpleCalc {
	public static void main(String[] args) {
		double first, second, answer = 0;
		String op;
		Scanner k = new Scanner(System.in);

		puts("Enter Simple Mathematical expression:");

		first = k.nextDouble();
		op = k.next();
		second = k.nextDouble();

		switch (op.charAt(0)) {
		case '+':
			answer = first + second;
			break;
		case '-':
			answer = first - second;
			break;
		case '*':
			answer = first * second;
			break;
		case '/':
			answer = first / second;
		}
		
		puts("The answer is: "+ answer);
	}

	private static void puts(Object s) {
		System.out.println(s);
	}
}
