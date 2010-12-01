import java.util.Random;
import java.util.Scanner;


public class Guessing {
	
	static final int MAX_NUMBER = 10;

	public static void main(String[] args) {
		Guessing me = new Guessing();
		me.playGame();

	}

	public void playGame() {
		int guess = 0;
		int x = getRandomNumber(MAX_NUMBER);
		Scanner kb = new Scanner(System.in);
		
		boolean done = false;
		
		System.out.println("I'm thinking of a nuymber between 1 and "+ MAX_NUMBER);
		while (!done) {
			System.out.println("Guess>");
			guess = kb.nextInt();
			
			if (guess > x) {
				System.out.println("Too high");
			} else if (guess < x) {
				System.out.println("Too low");
			} else {
				System.out.println("YAYYYYY!!!!");
				done = true;
			}

		}
	}
	
	public int getRandomNumber(int max) {  
		return new Random().nextInt(max);
	}
}
