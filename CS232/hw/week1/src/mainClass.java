import java.util.*;

public class mainClass {

	public static void main(String[] args) {

		System.out.println("Hello  out there.");
		System.out.println("I will add two numbers for you.");
		System.out.println("Enter two whole numbers on a line.");

		int n1, n2, total;

		Scanner keyboard = new Scanner(System.in);

		n1 = keyboard.nextInt();
		n2 = keyboard.nextInt();

		total = n1 + n2;

		System.out.println("The sum of your 2 numbers is: ");
		System.out.println(total);
	}

}
