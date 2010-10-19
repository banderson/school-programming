import java.util.Scanner;

public class Game {
	private final int ALLOWED_GUESSES = 7;
	private String secretWord;
	private String displayableWord;
	private int wrongGuesses;
	private int rightGuesses;
	
	public static void main(String[] args) {
		Game hangman = new Game();

		System.out.println("\nEnter a secret word:");

		// TODO add error checking around input
		Scanner input = new Scanner(System.in);
		String word = input.next();
		hangman.setWord(word);
		
		do {
			System.out.println("Guess a letter (or 'quit' to end): ");
			String guess = input.next();
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
				break;
			} else {
				System.out.println("Invalid input, try again...");
			}
			
			// redraw screen
			System.out.println(hangman.displayMan());
			
		} while (hangman.gameIsActive());
		
		if (hangman.isFound()) {
			System.out.println("CONGRATS, YOU WIN!");
		} else {
			System.out.println("Game Over... Better luck next time.");
		}
		
	}
	
	public Game() {
		// initialize String objects
		displayableWord = secretWord = "";
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
			guessedRight(c);
		} else {
			guessedWrong(c);
		}
		
		return result;
	}

	private void guessedRight(String character) {
		++rightGuesses;
		updateDisplayableWord(character);
	}

	private void guessedWrong(String character) {
		++wrongGuesses;
	}

	private void updateDisplayableWord(String character) {
		String tmpWord = secretWord.toLowerCase();
		while (tmpWord.contains(character.toLowerCase())) {
			int pos = tmpWord.indexOf(character.toLowerCase());	// get position of character
			tmpWord = tmpWord.replaceFirst(character, "-");		// make sure it's not used on next iteration
			// swap the correct character into the displayable word based on its position in hidden string
			displayableWord = displayableWord.substring(0, pos) + character + displayableWord.substring(pos+1);
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
		char output;
		
		switch (guess) {
		case 1:
			output = 'O';
			break;
		case 2:
		case 7:
			output = '\\';
			break;
		case 3:
		case 5:
			output = '|';
			break;
		case 4:
		case 6:
			output = '/';
			break;
		default:
			output = ' ';
			break;
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
