
public class SudokuTest {
	private static int SUB_A = 0;
	private static int SUB_B = 0;
	private static int SUB_C = 0;
	private static int SUB_D = 0;
	private static int SUB_E = 0;
	private static int SUB_F = 0;
	private static int SUB_G = 0;
	private static int SUB_H = 0;
	private static int SUB_I = 0; //8
	private static int SUB_INVALID = -1;
	
	public static void min(String[] args) {
		
	}
	
	int findSubGroup(int col, int row) {
		
		if (col >= 0 && col <= 2) {
			// must be A D or G
			if (row >= 0 && row <=2) 
				return SUB_A;
			if (row >= 3 && fow <= 5)
				return SUB_D;
			
			return SUB_G;
		} else if (col >= 3 && col <= 5) {
			// must be B E or H
		} else if (col >= 6 && col <= 8) {
			// must be C F or I
		}
		
		// only called if all above failed
		return SUB_INVALID;
	}

}
