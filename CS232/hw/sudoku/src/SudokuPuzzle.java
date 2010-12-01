import java.util.Scanner;

/**
 * @author Ben Anderson
 * Class: SudokuPuzzle
 * Description: a console-based Sudoku game, allowing a user to solve predefined Sudoku puzzles interactively
 */
public class SudokuPuzzle {
	
	public static void main(String[] args) {
		SudokuPuzzle game = new SudokuPuzzle();
		game.initBoard();

		// print the initial board
		System.out.println("Here's your puzzle:");
		System.out.println(game.toString());
		
		System.out.println("Instructions: Each time you're prompted, enter a Row, Column, and Value combination. ");
		System.out.println("If you get stuck, enter a valid row and column, but set the value to '?' to get a list of acceptable values for that space.");
		System.out.println("Enter an 'r' to reset the game at any time.\n\nBegin Now! \n");
		
		String input = "";
		int row, column, value;
		row = column = value = 0;
		
		do {
			// perhaps a bit too Exception-focused?
			System.out.println("Enter a row, column, and a value \n =>");
			try {
				input = game.getInput();
				row = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				if (input.equalsIgnoreCase("r")) {
					game.reset();
					System.out.println("Board reset, starting over...");
					System.out.println(game.toString());
				} else {
					System.out.println("Invalid Row value, try again...");
				}
				continue;
			} 
			
			try {
				input = game.getInput();
				column = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid Column, try again...");
				continue;
			}
			
			try {
				input = game.getInput();
				value = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				if (input.equals("?")) {
					System.out.println("Acceptable values: "+ game.getAllowedValuesString(row, column));
				} else {
					System.out.println("Invalid value, try again...");
				}
				continue;
			} 
			
			try {
				game.addGuess(row, column, value);
			} catch (DuplicateValueException ex) {
				System.out.println("That's not a valid number for this square. Possible values are: "+ game.getAllowedValuesString(row, column));
			} catch (ImmutableValueException ex) {
				System.out.println("That's not an editable space. Please try again.");
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println("Location invalid. Both row and column must be between 0 and 8.");
			} catch (RuntimeException ex) {
				System.out.println("An unknow exception occurred, exiting program...");
				ex.printStackTrace();
			}
			
			if (game.isOver())
				System.out.println("Congratulations, you've won!\n\n Final Puzzle:");
			
			// print the board out
			System.out.println(game.toString());
			
		} while(!game.isOver());
	}
	
	private String getInput() {
		Scanner in = new Scanner(System.in);
		return in.next();
	}
	
	public SudokuPuzzle() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = new Cell();
			}
		}
	}

	private Cell[][] board = new Cell[9][9];
	
	// returns the allowed values for a cell in print-friendly form
	public String getAllowedValuesString(int row, int col) {
		return board[row][col].getAllowedValuesString();
	}
	
	public void addInitial(int row, int col, int value) {
		board[row][col].setValue(value);
		board[row][col].isFixed = true;	// lock it
	}
	
	public void reset() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// retain preset fields
				if (!isFixedValue(i, j)) {
					board[i][j].isEmpty();
				}
			}
		}
	}
	
	public Cell cell(int row, int col) {
		return board[row][col];
	}
	
	public boolean addGuess(int row, int col, int value) {
		return board[row][col].setValue(value);
	}
	
	public boolean isFull() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (board[x][y].isEmpty())
					return false;
			}
		}
		return true; 
	}
	
	public int getValueIn(int row, int col) {
		// returns the value stored in a space
		return board[row][col].value;
	}
	
	public boolean isFixedValue(int row, int col) {
		// returns true if the value in this space can't be changed
		return board[row][col].isFixed;
	}
	
	public String toString() {
		String output = "";
		
		output += "=====================================\n";
		for (int i = 0; i < 9; i++) {
			output += "| "; 
			for (int j = 0; j < 9; j++) {
				if (board[i][j].value <= 0)
					output += "  ";
				else
					output += board[i][j] + " ";
				
				if (j % 3 == 2)
					output += "| ";
				else
					output += ": "; 
			}
			if (i % 3 == 2)
				output +=  "\n=====================================\n";
			else
				output +=  "\n-------------------------------------\n";
		}
		
		return output;
	}
	
	public boolean isSolved() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				// if every cell has their final solution in place...
				if (board[x][y].isSolved()) {
					return false;
				}
			}
		}
		// no locations failed, so it's solved
		return true;
	}
	
	public boolean isOver() {
		// the game is done when every spot is filled in with a valid #
		return (isFull() && isSolved());
	}
	
	// initialize the board
	public void initBoard() {
		addInitial(0, 0, 1);
		addInitial(0, 1, 2);
		addInitial(0, 2, 3);
		addInitial(0, 3, 4);
		addInitial(0, 4, 9);
		addInitial(0, 5, 7);
		addInitial(0, 6, 8);
		addInitial(0, 7, 6);
		addInitial(0, 8, 5);
		
		addInitial(1, 0, 4);
		addInitial(1, 1, 5);
		addInitial(1, 2, 9);
		
		addInitial(2, 0, 6);
		addInitial(2, 1, 7);
		addInitial(2, 2, 8);
		
		addInitial(3, 0, 3);
		addInitial(3, 4, 1);
		
		addInitial(4, 0, 2);
		
		addInitial(5, 0, 9);
		addInitial(5, 5, 5);
		
		addInitial(6, 0, 8);
		
		addInitial(7, 0, 7);
		
		addInitial(8, 0, 5);
		addInitial(8, 3, 9);
	}

	private class Cell {
		private static final int LOCATION_EMPTY = 0;
		public int row, column, value;
		public boolean isFixed;
		
		public Cell() {
			value = LOCATION_EMPTY;
			column = row = 0;
		}
		
		public boolean isEmpty() {
			return value == LOCATION_EMPTY;
		}
		
		public boolean isAllowed(int value) {
			return getAllowedValues()[value-1];
		}
		
		public boolean setValue(int value) {
			if (isFixed)
				throw new ImmutableValueException();
			
			// make sure values are in the right range
			if (value > 9 || value <= 0)
				throw new IllegalArgumentException("Value must be between 1 and 9");
			
			// enforce game logic
			if (!isAllowed(value))
				throw new DuplicateValueException();
			
			this.value = value;
			
			return true;
		}
		
		public String getAllowedValuesString() {
			boolean[] vals = getAllowedValues();
			String output = "";
			
			for (int i = 0; i < vals.length; i++) {
				if (vals[i]) {
					output += i+1 + ",";
				}
			}
			
			return output.substring(0, output.lastIndexOf(","));
		}
		
		// Check for the valid options based on a row and column
		// No return type but as a side effect it fills in the validNumbers array
		// NOTE: copied from in-class code
		private boolean[] getAllowedValues() {
			
			// allocate a valid numbers array
			boolean[] valNums = new boolean[9];
			
			// Set all to true, rest of code will clear as needed
			for (int i = 0; i < 9; i++)
				valNums[i] = true;
			
			// check row
			for (int i = 0; i < 9; i++) {
				if (!board[row][i].isEmpty())  {
					valNums[board[row][i].value-1] = false;
				}
			}
			
			// check column
			for (int i = 0; i < 9; i++) {
				if (!board[i][column].isEmpty())  {
					valNums[board[i][column].value-1] = false;
				}
			}
			
			int rl = (row / 3) * 3;
			int rh = rl + 3;
			int cl = (column / 3) * 3;
			int ch = cl + 3;
			
			for (int r = rl; r < rh; r++) {
				for (int c = cl; c < ch; c++) {
					if (!board[r][c].isEmpty()) 
						valNums[board[r][c].value-1] = false;
				}
			}
			
			// if there's a value already, set that as allowable
			//	(it technically is, and it helps testing)
			int thisVal = getValueIn(row, column);
			if (thisVal != LOCATION_EMPTY)
				valNums[thisVal-1] = true;
			
			return valNums;
		}
		
		public boolean isSolved() {
			// if every entry has only 1 available option, and it's the value of the cell itself, the puzzle is solved
			// 	therefore, if that's not true at any point then it's not solved and we can bail
			return Integer.parseInt(getAllowedValuesString()) == value;
		}
	}
}
