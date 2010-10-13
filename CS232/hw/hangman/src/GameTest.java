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
	
	
	@Test(expected=RuntimeException.class) 
	public void cantDoAnythingUntilWordIsSet() {
		hangman.MakeGuess('a');
	}
	
	@Test public void canSetWord() {
		String word = "test";
		hangman.setWord(word);
		assertTrue(hangman.MakeGuess('t'));
		assertTrue(hangman.MakeGuess('e'));
		assertTrue(hangman.MakeGuess('s'));
		assertEquals("test", hangman.getDisplayableWord());
	}
	
	@Test public void spacesDontCount() {
		String word = "just a test";
		hangman.setWord(word);
		assertEquals("---- - ----", hangman.getDisplayableWord());
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
	
	@Test public void repeatedCharactersCountAsBadBuess() {
		hangman.setWord("word test");
		assertEquals(7, hangman.guessesRemaining());
		hangman.MakeGuess('a');	// decrements
		assertEquals(6, hangman.guessesRemaining());
		hangman.MakeGuess('a');	// decrements
		assertEquals(5, hangman.guessesRemaining());
		hangman.MakeGuess('w');	// doesn't decrement
		assertEquals(5, hangman.guessesRemaining());
		hangman.MakeGuess('w');	// decrements (dupe)
		assertEquals(4, hangman.guessesRemaining());
	}
	
	@Test public void rightGuessDoesNotDecrementRemainingGuesses() {
		hangman.setWord("word test");
		
		assertEquals(7, hangman.guessesRemaining());
		hangman.MakeGuess('w');
		assertEquals(7, hangman.guessesRemaining());
		hangman.MakeGuess('o');
		assertEquals(7, hangman.guessesRemaining());
	}
	
	@Test public void displayableWordStartsBlank() {
		assertEquals("", hangman.getDisplayableWord());
	}
	
	@Test public void displayableWordUpdatesWhenSecretWordIsInitialized() {
		hangman.setWord("test word");
		assertEquals("---- ----", hangman.getDisplayableWord());
		hangman.setWord("testinggggggg");
		assertEquals("-------------", hangman.getDisplayableWord());
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

	@Test public void displayableWordRemainsOnBadGuess() {
		hangman.setWord("testing");
		hangman.MakeGuess('z');
		assertEquals("-------", hangman.getDisplayableWord());
		hangman.MakeGuess('q');
		assertEquals("-------", hangman.getDisplayableWord());
		hangman.MakeGuess('f');
		assertEquals("-------", hangman.getDisplayableWord());
	}
	
	@Test public void gameIsOverWhenWordIsDiscovered() {
		hangman.setWord("aabb");
		assertFalse(hangman.isFound());
		hangman.MakeGuess('a');
		assertFalse(hangman.isFound());
		hangman.MakeGuess('b');
		assertTrue(hangman.isFound());
		assertFalse(hangman.isActive);
	}
	
	// TODO refactor this and prev method
	@Test public void gameIsOverWhenMaxGuessesExceeded() {
		assertTrue(hangman.isActive);
		hangman.setWord("aabb");
		assertTrue(hangman.isActive);
		hangman.MakeGuess('a');	// doesn't count against guesses since it's right
		hangman.MakeGuess('a');	// the rest of these decrement
		hangman.MakeGuess('a');
		hangman.MakeGuess('a');
		assertTrue(hangman.isActive);
		hangman.MakeGuess('a');
		hangman.MakeGuess('a');
		assertTrue(hangman.isActive);
		hangman.MakeGuess('a');
		assertTrue(hangman.isActive);
		hangman.MakeGuess('a');
		assertFalse(hangman.isActive);
	}
	
	// TODO refactor this and prev 2 method
	@Test public void gameIsOverWithMatchWhenWordGuesedOnLastGuess() {
		assertTrue(hangman.isActive);
		hangman.setWord("aabb");
		assertTrue(hangman.isActive);
		hangman.MakeGuess('a');	// doesn't count against guesses since it's right
		hangman.MakeGuess('a');	// the rest of these decrement
		hangman.MakeGuess('a');
		hangman.MakeGuess('a');
		assertTrue(hangman.isActive);
		hangman.MakeGuess('a');
		hangman.MakeGuess('a');
		assertTrue(hangman.isActive);
		hangman.MakeGuess('a');
		assertTrue(hangman.isActive);
		hangman.MakeGuess('b');
		assertTrue(hangman.isFound());
		assertFalse(hangman.isActive);
	}
}
