import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameTest {

	Game hangman;
	
	@Before public void setUp() throws Exception {
		hangman = new Game();
	}
	
	@Test public void canCreateHangman() {
		assertNotNull(hangman);
	}
	
	@Test public void numberOfGuessesIs7() {
		assertEquals(7, hangman.guessesRemaining());
	}
	
	@Test public void badGuessDecrementsRemainingGuesses() {
		hangman.setWord("word test");
		
		assertEquals(7, hangman.guessesRemaining());
		hangman.MakeGuess('a');
		assertEquals(6, hangman.guessesRemaining());
		hangman.MakeGuess('z');
		assertEquals(5, hangman.guessesRemaining());
	} 
	
	@Test public void rightGuessDoesNotDecrementRemainingGuesses() {
		hangman.setWord("word test");
		
		assertEquals(7, hangman.guessesRemaining());
		hangman.MakeGuess('w');
		assertEquals(7, hangman.guessesRemaining());
		hangman.MakeGuess('o');
		assertEquals(7, hangman.guessesRemaining());
	} 
	
	@Test public void canSetWord() {
		String word = "secret";
		hangman.setWord(word);
		assertEquals(word, hangman.getHiddenString());
	}
	
	@Test public void displayableWordStartsBlank() {
		assertEquals("", hangman.getDisplayableWord());
	}
	
	@Test public void displayableWordUpdatesWhenSecretWordIsInitialized() {
		hangman.setWord("test word");
		assertEquals("---------", hangman.getDisplayableWord());
	}
	
	@Test public void displayableWordUpdatesOnRightGuess() {
		hangman.setWord("testing");
		hangman.MakeGuess('t');
		assertEquals("t--t---", hangman.getDisplayableWord());
		hangman.MakeGuess('g');
		assertEquals("t--t--g", hangman.getDisplayableWord());
		hangman.MakeGuess('e');
		assertEquals("te-t--g", hangman.getDisplayableWord());
	}
}
