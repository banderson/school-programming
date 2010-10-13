
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
		hangman.displayMan();
		hangman.setWord("testing");
		hangman.MakeGuess('t');
		hangman.MakeGuess('e');
		System.out.println(hangman.getDisplayableWord());
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
	
	public boolean MakeGuess(char c) {
		
		// don't let them start playing until they've set the word
		if (secretWord == "")
			throw new RuntimeException("You must enter a secret word before playing");
		
		boolean result = false;
		String strChar = String.valueOf(c);
		
		// check if the word contains the letter AND it hasn't be used yet
		if (secretWord.contains(strChar) && !charactersUsed.contains(strChar)) {
			++rightGuesses;
			result = true;
			charactersUsed += c;

			String tmpWord = secretWord;
			while (tmpWord.contains(strChar)) {
				int pos = tmpWord.indexOf(strChar);
				tmpWord = tmpWord.replaceFirst(strChar, "-");
				updateDisplayableWord(strChar, pos);
			}
		} else {
			++wrongGuesses;
		}
		
		System.out.println(guessesRemaining() + " guesses remaining");
		// end the game if they're out of turns or guessed word
		if (guessesRemaining() == 0 || isFound() == true) {
			endGame();
		}
		
		return result;
	}
	
	private void endGame() {
		isActive = false;
		// print out mad shit...
	}

	void updateDisplayableWord(String c, int position) {
		displayableWord = displayableWord.substring(0, position) + c + displayableWord.substring(position+1);
	}
	
	void displayMan() {
		System.out.println("blah blah blah");
	}
	
	String getDisplayableWord() {
		return displayableWord;
	}
	
	String getHiddenString() {
		return secretWord;
	}
	
	int getGuessCount() {
		return this.wrongGuesses + this.rightGuesses;
	}
	
	int getBadGuessCount() {
		return this.wrongGuesses;
	}
	
	boolean isFound() {
		return secretWord.equals(displayableWord);
	}
	
	public int guessesRemaining() {
		return (ALLOWED_GUESSES - wrongGuesses);
	}
}
