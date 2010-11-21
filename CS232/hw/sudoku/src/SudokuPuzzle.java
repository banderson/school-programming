import java.util.Arrays;

import com.sun.tools.javac.code.Attribute.Array;

public class SudokuPuzzle {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SudokuPuzzle game = new SudokuPuzzle();
		
		System.out.println(game.toString());
	}
	
	public SudokuPuzzle() {
		
	}
	
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
	
	// If a location on the board is empty it gets this value
	private static int LOCATION_EMPTY = 0;

	// Assumptions:
	//  The board is integer in value.  It contains positive numbers for
	//  valid numbers and -1 for empty.

	// TODO:  Allocate memory properly for the board
	private int[][] board = new int[9][9];
	private boolean[][] board_init = new boolean[9][9];
	
	public boolean isAllowedValue(int row, int col, int value) {
		
		if (value > 0 && value <= 9) {
			return getAllowedValues(row, col)[value-1];
		}
		
		return false;
	}
	
	// Check for the valid options based on a row and column
	// No return type but as a side effect it fills in the validNumbers array
	private boolean[] getAllowedValues(int row, int col) {
		
		// allocate a valid numbers array
		boolean[] valNums = new boolean[9];
		
		// set the valNums
		// Set all to true, rest of code will clear as needed
		for (int i = 0; i < 9; i++)
			valNums[i] = true;
		
		// check row
		for (int i = 0; i < 9; i++) {
			if (board[row][i] != LOCATION_EMPTY)  {
				valNums[board[row][i]-1] = false;
			}
		}
		
		// check column
		for (int i = 0; i < 9; i++) {
			if (board[i][col] != LOCATION_EMPTY)  {
				valNums[board[i][col]-1] = false;
			}
		}
		
		int rl = (row / 3) * 3;
		int rh = (row / 3) * 3 + 3;
		int cl = (col / 3) * 3;
		int ch = (col / 3) * 3 + 3;

		// example row=4, col=5
		// rl = 4/3 = 1 * 3 = 3
		// rh = rl + 3 = 6
		// cl = 5/3 = 1 * 3 = 3
		// ch = cl + 3 = 6
		
		for (int r = rl; r < rh; r++) {
			for (int c = cl; c < ch; c++) {
				if (board[r][c] != LOCATION_EMPTY) 
					valNums[board[r][c]-1] = false;
			}
		}
		
		return valNums;
	}
	
	public String getAllowedValuesString(int row, int col) {
		boolean[] vals = getAllowedValues(row, col);
		String output = "";
		
		for (int i = 0; i < vals.length; i++) {
			if (vals[i]) {
				output += i+1 + ",";
			}
		}
		
		return output.substring(0, output.lastIndexOf(","));
	}
	
	public void addInitial(int row, int col, int value) {
		addGuess(row, col, value);
		board_init[row][col] = true;
	}
	
	public boolean addGuess(int row, int col, int value) {
		if (isFixedValue(row, col))
			throw new ImmutableValueException();
		
		if (value > 9 || value <= 0)
			throw new IllegalArgumentException("Value must be between 1 and 9");
		
		if (!isAllowedValue(row, col, value))
			throw new DuplicateValueException();
		
		board[row][col] = value;
		
		return true;
	}
	
	public void solveCell(int row, int col) {
		for (int i = 1; i <= 9; i++) {
			//if (row == 1 && col == 0)
				System.out.println("Trying "+ i + " for row "+ row +", col "+ col);
			if (isAllowedValue(row, col, i)) {
				addGuess(row, col, i);
				return;
			}
		}
		
		throw new IllegalStateException();
	}
	
	public boolean isFull() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (board[x][y] == LOCATION_EMPTY)
					return false;
			}
		}
		return true; 
	}
	
	public int getValueIn(int row, int col) {
		return board[row][col];
	}
	
	public boolean isFixedValue(int row, int col) {
		return board_init[row][col];
	}
	
	public String toString() {
		String output = "";
		
		output += "=====================================\n";
		for (int i = 0; i < 9; i++) {
			output += "| "; 
			for (int j = 0; j < 9; j++) {
				if (board[i][j] <= 0)
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
}
