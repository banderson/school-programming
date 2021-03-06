import org.junit.*;

public class SudokuTest {

	private SudokuPuzzle game;
	
	@Before
	public void setUp() throws Exception {
		game = new SudokuPuzzle();
	}
	
	@Test public void canPrintEmptyBoard() {
		Assert.assertNotSame("", game.toString());
	}
	
	@Test public void canSetInitialValues() {
		game.addInitial(0, 0, 7);
		
		// set an initial value and make sure it's flagged correctly
		Assert.assertEquals(game.getValueIn(0,0), 7);
		Assert.assertTrue(game.isFixedValue(0, 0));
		
		// make sure we aren't getting false positives (check against uninitialized state)
		Assert.assertEquals(game.getValueIn(0, 1), 0);
		Assert.assertFalse(game.isFixedValue(0, 1));
	}
	
	@Test
	public void cantOverrideInitialValues() {
		game.addInitial(0, 0, 7);
		
		// shouldn't be allowed
		try {
			game.addGuess(0, 0, 9);
			Assert.fail("Should've thrown exception");
		} catch (ImmutableValueException e) {
			Assert.assertTrue("The exception was caught.", true);
			return;
		}
		
		Assert.fail("Should've thrown ImmutableValueException");
	}
	
	@Test public void canMakeGuess() {
		
		// make sure the cell is not set originally
		Assert.assertEquals(0, game.getValueIn(0, 0));
		
		try {
			game.addGuess(0, 0, 1);
		} catch (ImmutableValueException e) {
			Assert.fail("This should be a legal assignment");
		}
		
		// check for updated value
		Assert.assertEquals(1, game.getValueIn(0, 0));
	}
	
	@Test public void allowedValuesUpdatesDynamically() {
		String opts = "1,2,3,4,5,6,7,8,9";
		
		// establish baseline
		Assert.assertEquals(opts, game.getAllowedValuesString(0, 0));
		// make a guess
		game.addInitial(0, 0, 7);
		game.addInitial(0, 1, 6);
		// no longer equal!
		Assert.assertTrue(opts != game.getAllowedValuesString(0, 0));
		// remove the used items...
		opts = opts.replaceAll("[6|7],", "");
		// ... and make sure the string matches
		Assert.assertEquals(opts, game.getAllowedValuesString(0, 2));
	}
	
	@Test public void canResetCellValueToItself() {
		game.addGuess(0, 0, 7);
		Assert.assertTrue(game.isAllowedValue(0, 0, 7) == true);
	}
	
	@Test public void only1through9AreValid() {
		// make sure initial values are within range
		try {
			game.addInitial(0, 0, 11);
			Assert.fail("Initial values should be within 1-9");
		} catch (IllegalArgumentException e) {
			
		}
		
		try {
			game.addInitial(0, 0, -11);
			Assert.fail("Initial values should be within 1-9");
		} catch (IllegalArgumentException e) {
			
		}
		
		// make sure user-specified values are within range
		try {
			game.addGuess(0, 0, 12);
			Assert.fail("User assign values should be within 1-9");
		} catch (IllegalArgumentException e) {
			
		}
		
		// make sure user-specified values are within range
		try {
			game.addGuess(0, 0, -1);
			Assert.fail("User assign values should be within 1-9");
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test public void cantAddDuplicateValuesInQuadrant() {
		
		game.addInitial(0, 0, 7);
		game.addGuess(0, 1, 1);
		game.addGuess(0, 2, 2);
		game.addGuess(1, 0, 3);
		game.addGuess(1, 1, 4);
		game.addGuess(1, 2, 5);
		game.addGuess(2, 0, 6);
		game.addGuess(2, 1, 8);
		game.addGuess(2, 2, 9);
		
		try {
			game.addGuess(0, 1, 7);
			Assert.fail("Shouldn't be able to assign 7 in the first quadrant since it's already used");
		} catch (DuplicateValueException e) {
			Assert.assertTrue("This exception should've been thrown", true);
		} catch (Exception e) {
			Assert.fail("Dupe exception not caught. Instead got: "+ e);
		}
	}
	
	@Test public void cantAddDuplicateValuesInRow() {
		
		// all legal moves
		game.addInitial(0, 0, 7);
		game.addGuess(0, 1, 1);
		game.addGuess(0, 2, 2);
		game.addGuess(0, 3, 3);
		game.addGuess(0, 4, 4);
		game.addGuess(0, 5, 5);
		game.addGuess(0, 6, 6);
		game.addGuess(0, 7, 8);
		game.addGuess(0, 8, 9);
		
		try {
			game.addGuess(0, 8, 7);
			Assert.fail("Shouldn't be able to assign 7 in the first row since it's already used");
		} catch (DuplicateValueException e) {
			Assert.assertTrue("This exception should've been thrown", true);
		} catch (Exception e) {
			Assert.fail("Dupe exception not caught. Instead got: "+ e);
		}
	}
	
	@Test public void cantAddDuplicateValuesInColumn() {
		
		// all legal moves
		game.addInitial(0, 0, 7);
		game.addGuess(1, 0, 1);
		game.addGuess(2, 0, 2);
		game.addGuess(3, 0, 3);
		game.addGuess(4, 0, 4);
		game.addGuess(5, 0, 5);
		game.addGuess(6, 0, 6);
		game.addGuess(7, 0, 8);
		game.addGuess(8, 0, 9);
		
		try {
			game.addGuess(8, 0, 7);
			Assert.fail("Shouldn't be able to assign 7 in the first column since it's already used");
		} catch (DuplicateValueException e) {
			Assert.assertTrue("This exception should've been thrown", true);
		} catch (Exception e) {
			Assert.fail("Dupe exception not caught. Instead got: "+ e);
		}
	}
	
	@Test public void canCheckValidValues() {
		int[] opts = new int[9];
		for (int i = 0; i < opts.length; i++) {
			opts[i] = i+1;
		}
		
		Assert.assertTrue(game.isAllowedValue(0, 1, 7) == true);
		Assert.assertTrue(game.isAllowedValue(1, 0, 7) == true);
		Assert.assertTrue(game.isAllowedValue(1, 1, 7) == true);
		game.addInitial(0, 0, 7);
		Assert.assertTrue(game.isAllowedValue(0, 1, 7) == false);
		Assert.assertTrue(game.isAllowedValue(1, 0, 7) == false);
		Assert.assertTrue(game.isAllowedValue(1, 1, 7) == false);
	}
	
	@Test public void canCheckIfPuzzleIsSolved() {
		
		game.addInitial(0, 0, 5);
		game.addInitial(0, 1, 3);
		game.addInitial(0, 4, 7);
		
		game.addInitial(1, 0, 6);
		game.addInitial(1, 3, 1);
		game.addInitial(1, 4, 9);
		game.addInitial(1, 5, 5);
		
		game.addInitial(2, 1, 9);
		game.addInitial(2, 2, 8);
		game.addInitial(2, 7, 6);
		
		game.addInitial(3, 0, 8);
		game.addInitial(3, 4, 6);
		game.addInitial(3, 8, 3);
		
		game.addInitial(4, 0, 4);
		game.addInitial(4, 3, 8);
		game.addInitial(4, 5, 3);
		game.addInitial(4, 8, 1);
		
		game.addInitial(5, 0, 7);
		game.addInitial(5, 4, 2);
		game.addInitial(5, 8, 6);
		
		game.addInitial(6, 1, 6);
		game.addInitial(6, 6, 2);
		game.addInitial(6, 7, 8);
		
		game.addInitial(7, 3, 4);
		game.addInitial(7, 4, 1);
		game.addInitial(7, 5, 9);
		game.addInitial(7, 8, 5);
		
		game.addInitial(8, 4, 8);
		game.addInitial(8, 7, 7);
		game.addInitial(8, 8, 9);
		
		fillRow(0, "534678912", game);
		fillRow(1, "672195348", game);
		fillRow(2, "198342567", game);
		fillRow(3, "859761423", game);
		fillRow(4, "426853791", game);
		fillRow(5, "713924856", game);
		fillRow(6, "961537284", game);
		fillRow(7, "287419635", game);
		Assert.assertFalse(game.isFull());
		Assert.assertFalse(game.isSolved());
		fillRow(8, "345286179", game);
		
		Assert.assertTrue(game.isFull());
		Assert.assertTrue(game.isSolved());
		
		game.printArray();
	}
	
	private SudokuPuzzle fillRow(int rowNum, String vals, SudokuPuzzle game) {
		for(int i = 0; i < 9; i++) {
			char c = vals.charAt(i);
			try {
				game.addGuess(rowNum, i, Integer.parseInt(c + ""));
			} catch (ImmutableValueException ex) {
				// eat it
			}
		}
		return game;
	}
	
	@Test public void canResetPuzzle() {
		Assert.assertEquals(game.getValueIn(0, 0), 0);
		Assert.assertEquals(game.getValueIn(0, 1), 0);
		Assert.assertEquals(game.getValueIn(0, 2), 0);
		game.addGuess(0, 0, 1);
		game.addGuess(0, 1, 2);
		game.addInitial(0, 2, 7);
		Assert.assertEquals(game.getValueIn(0, 0), 1);
		Assert.assertEquals(game.getValueIn(0, 1), 2);
		Assert.assertEquals(game.getValueIn(0, 2), 7);
		
		game.reset();
		
		Assert.assertEquals(game.getValueIn(0, 0), 0);
		Assert.assertEquals(game.getValueIn(0, 1), 0);
		Assert.assertEquals(game.getValueIn(0, 2), 7);
	}

	@After
	public void tearDown() throws Exception {
	}

}
