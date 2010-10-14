import java.util.Scanner;

public class Game {
	public final int ALLOWED_GUESSES = 7;
	private String secretWord;
	private String displayableWord;
	private int wrongGuesses;
	private int rightGuesses;
	public boolean isActive = true;
	private String charactersUsed;
	
	public static void main(String[] args) {
		Game hangman = new Game();

		Scanner input = new Scanner(System.in);
		System.out.println("Enter a secret word:");
		String word = input.next();
		// TODO add error checking around input
		hangman.setWord(word);
		
		do {
			System.out.println("Your guess: ");
			String guess = input.next();
			if (guess.length() == 1) {
				boolean success = hangman.MakeGuess(guess);
				if (success) {
					System.out.println("Correct!");
				} else {
					System.out.println("Try again...");
				}
			} else if (guess.equals("quit")) {
				// you can quit the game any time
				System.out.println("Sorry to see you go...");
				break;
			} else {
				System.out.println("Invalid input, try again...");
			}
			
			// redraw screen
			hangman.displayMan();
			
		} while (hangman.guessesRemaining() > 0 && !hangman.isFound());
		
		if (hangman.isFound()) {
			System.out.println("Game Over...... YOU'RE BRILLIANT!!");
		} else {
			System.out.println("Game Over...... YOU SUCK!!");
		}
		
	}
	
	public Game() {
		// initialize fields
		displayableWord = secretWord = charactersUsed = "";
		wrongGuesses = rightGuesses = 0;
	}

	public void setWord(String word) {
		secretWord = word;
		
		// create the displayable word (same length, obscured characters)
		displayableWord = "";
		for (int i = 0; i < secretWord.length(); i++) {
			displayableWord += (secretWord.charAt(i) == ' ') 
									? " " 	// retain spaces for clarity
									: "-";	// otherwise obscure letter
		}
	}
	
	public boolean MakeGuess(String c) {
		// don't let them start playing until they've set the word
		if (secretWord == "")
			throw new RuntimeException("You must enter a secret word before playing");
		
		boolean result = false;
		// the game is case-insensitive
		c = c.toLowerCase();
		
		// check if the word contains the letter AND it hasn't be used yet
		if (secretWord.toLowerCase().contains(c) && !charactersUsed.contains(c)) {
			result = true;
			guessedRight(c);
		} else {
			guessedWrong(c);
		}
		
		// end the game if they're out of turns or guessed word
		if (guessesRemaining() == 0 || isFound() == true) {
			endGame();
		}
		
		return result;
	}
	
	public boolean MakeGuess(char c) {
		return MakeGuess(String.valueOf(c));
	}

	private void guessedRight(String character) {
		++rightGuesses;
		charactersUsed += character;
		updateDisplayableWord(character);
	}

	private void guessedWrong(String character) {
		++wrongGuesses;
	}

	private void updateDisplayableWord(String character) {
		String tmpWord = secretWord.toLowerCase();
		//character = character.toLowerCase();
		while (tmpWord.contains(character.toLowerCase())) {
			int pos = tmpWord.indexOf(character.toLowerCase());	// get position of character
			tmpWord = tmpWord.replaceFirst(character, "-");
			displayableWord = displayableWord.substring(0, pos) + character + displayableWord.substring(pos+1);
		}
	}
	
	void displayMan() {
		System.out.println("Current status: "+ getDisplayableWord());
	}
	
	private void endGame() {
		isActive = false;
		// print out mad shit... depending on outcome
	}
	
	String getDisplayableWord() {
		return displayableWord;
	}
	
	String getHiddenWord() {
		return secretWord;
	}
	
	int getBadGuessCount() {
		return this.wrongGuesses;
	}
	
	int getGuessCount() {
		return this.wrongGuesses + this.rightGuesses;
	}
	
	public int guessesRemaining() {
		return (ALLOWED_GUESSES - wrongGuesses);
	}
	
	boolean isFound() {
		return secretWord.equalsIgnoreCase(displayableWord);
	}
}
