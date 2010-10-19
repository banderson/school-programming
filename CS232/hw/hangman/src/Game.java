import java.util.Scanner;

public class Game {
	private final int ALLOWED_GUESSES = 7;
	private String secretWord, displayableWord;
	private int wrongGuesses, rightGuesses;
	
	public static void main(String[] args) {
		Game hangman = new Game();
		
		System.out.println("\nEnter a secret word:");
		String word = getUserInput();
		hangman.setWord(word);
		
		do {
			System.out.println("Guess a letter (or 'quit' to end): ");
			String guess = getUserInput();
			if (guess.length() == 1) {
				boolean success = hangman.MakeGuess(guess);
				if (success) {
					System.out.println("Correct!");
				} else {
					System.out.println("Sorry, try again...");
				}
			} else if (guess.equals("quit")) {
				// you can quit the game any time
				System.out.println("Sorry to see you go...");
				break; // exit the game
			} else {
				System.out.println("Invalid input, try again...");
			}
			
			// redraw screen
			System.out.println(hangman.displayMan());
			
		} while (hangman.gameIsActive());
		
		// print the result
		if (hangman.isFound()) {
			System.out.println("CONGRATS, YOU WIN!");
		} else {
			System.out.println("Game Over... Better luck next time.");
		}
	}
	
	private static String getUserInput() {
		// TODO add error handling?
		Scanner input = new Scanner(System.in);
		return input.next();
	}
	
	public Game() {
		displayableWord = secretWord = ""; // initialize String objects
	}

	public void setWord(String word) {
		secretWord = word;
		// create the displayable word (same length, obscured characters)
		displayableWord = secretWord.replaceAll("[a-zA-Z]", "-");
	}
	
	public boolean MakeGuess(String c) {
		// don't let them start playing until they've set the word
		if (secretWord == "")
			throw new RuntimeException("You must enter a secret word before playing");
		
		boolean result = false;
		
		// the game is case-insensitive
		c = c.toLowerCase();
		
		// check if the word contains the letter AND it hasn't be used yet
		if (secretWord.toLowerCase().contains(c) && !displayableWord.toLowerCase().contains(c)) {
			result = true;
			++rightGuesses;
			updateDisplayableWord(c);
		} else {
			++wrongGuesses;
		}
		
		return result;
	}

	private void updateDisplayableWord(String c) {
		String tmpWord = secretWord.toLowerCase(); // make local copy so we can alter it
		while (tmpWord.contains(c.toLowerCase())) {
			int pos = tmpWord.indexOf(c.toLowerCase()); // get position of character
			tmpWord = tmpWord.replaceFirst(c, "-");	// remove it so it's not used again
			// add correct character into displayable word based on position in hidden string
			displayableWord = new StringBuffer(displayableWord).replace(pos, pos+1, c).toString();
		}
	}
	
	String displayMan() {
		String output = "\n";
		output += " +------+\n";
		output += " |      |\n";
		output += " 1      |\n";
		output += "234     |\n";
		output += " 5      |\n";
		output += "6 7     |\n";
		output += "        |\n";
		output += "    ----+----\n\n";
		
		// for each bad guess, swap the placeholder with body part character
		for (int i = 1; i <= getBadGuessCount(); i++) {
			output = output.replace(Character.forDigit(i, 10), getBodyPart(i));
		}
		
		// remove the remaining placeholders
		output = output.replaceAll("[0-9]", " ");
		
		output += "Word ("+ getDisplayableWord().length() +" letters): "+ getDisplayableWord();
		
		return output;
	}
	
	private char getBodyPart(int guess) {
		char output = ' ';
		
		switch (guess) {
			case 1: output = 'O'; break;
			case 2: case 7: output = '\\'; break;
			case 3: case 5: output = '|'; break;
			case 4: case 6: output = '/'; break;
		}
		
		return output;
	}
	
	String getDisplayableWord() {
		return displayableWord;
	}
	
	String getHiddenWord() {
		return secretWord;
	}
	
	int getBadGuessCount() {
		return wrongGuesses;
	}
	
	int getGuessCount() {
		return wrongGuesses + rightGuesses;
	}
	
	public int guessesRemaining() {
		return (ALLOWED_GUESSES - wrongGuesses);
	}
	
	boolean isFound() {
		return secretWord.equalsIgnoreCase(displayableWord);
	}
	
	boolean gameIsActive() {
		return guessesRemaining() > 0 && !isFound();
	}
}
