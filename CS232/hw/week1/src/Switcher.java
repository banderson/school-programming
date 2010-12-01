import java.util.Scanner;

public class Switcher {

	public static void main(String[] args) {
		String input; 

		Scanner keyboard = new Scanner(System.in);
		//input = keyboard.nextLine();
		
		//System.out.println("You entered: "+ input);
		
		//input = input.trim();
		//System.out.println("Or, trimmed: "+ input);
		
		//char c = input.charAt(0);
		//System.out.println("The first character is: "+ c);
		
		boolean done = false;
		while (!done) {
			input = keyboard.nextLine().trim();
			char c = input.charAt(0);
			switch (c) {
			case 'h':
			case 'H':
				System.out.println("You want help?");
				break; // this prevents fall through (executes all code below, except default)
			case 'q':
			case 'Q':
				System.out.println("Why quit now?");
				done = true;
				break;
			case 'p':
			case 'P':
				System.out.println("Check your printer");
				break;
			default:
				System.out.println("Please enter: ");
				System.out.println("\t h for help");
				System.out.println("\t q to quit");
				System.out.println("\t p to print");
				break;
			}
		}
		
		System.out.println("Done and Done...");
	}

}
