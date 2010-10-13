
public class Game {
	
	public final int ALLOWED_GUESSES = 7;
	
	private String secretWord;
	private String displayableWord;
	private int wrongGuesses;
	private int rightGuesses;
	
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
		displayableWord = secretWord = "";
		wrongGuesses = rightGuesses = 0;
	}
	
	public boolean MakeGuess(char c) {
		
		// don't let them start playing until they've set the word
		if (secretWord == "")
			throw new RuntimeException("You must enter a secret word before playing");
		
		boolean result = false;
		String strChar = String.valueOf(c);
		
		if (secretWord.contains(strChar)) {
			++rightGuesses;
			result = true;
		} else {
			++wrongGuesses;
		}
		
		String tmpWord = secretWord;
		while (tmpWord.contains(strChar)) {
			int pos = tmpWord.indexOf(strChar);
			tmpWord = tmpWord.replaceFirst(strChar, "-");
			updateDisplayableWord(strChar, pos);
		}
		
		return result;
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
		return secretWord == displayableWord;
	}

	public void setWord(String word) {
		secretWord = word;
		
		// create the displayable word (same length, obscured characters)
		for (int i = 0; i < secretWord.length(); i++) {
			displayableWord += "-";
		}
	}
	
	public int guessesRemaining() {
		return ALLOWED_GUESSES - wrongGuesses;
	}
}
