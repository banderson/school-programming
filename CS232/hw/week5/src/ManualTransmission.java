
public class ManualTransmission {

	private static final int DEFAULT_NUM_GEARS = 5;
	public static final int GEAR_NEUTRAL = 0;
	public static final int GEAR_REVERSE = -1;
	private int numberOfGears;
	private int currentGear;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManualTransmission t = new ManualTransmission(1);
		
		System.out.println(t.toString());
		t.shiftUp();
		System.out.println(t.toString());
		t.shiftDown();
		System.out.println(t.toString());
	}
	
	public int getCurrentGear() {
		return currentGear;
	}

	public int setCurrentGear(int currentGear) {
		if (currentGear > numberOfGears) {
			currentGear = numberOfGears;
		}
		
		if (currentGear < GEAR_REVERSE) {
			currentGear = GEAR_REVERSE;
		}
		
		this.currentGear = currentGear;
		
		return currentGear;
	}
	
	public int shiftUp() {
		return setCurrentGear(this.currentGear + 1);
	}
	
	public int shiftDown() {
		if (currentGear <= 1) {
			return currentGear;
		}
		
		return setCurrentGear(this.currentGear - 1);
	}
	
	public String toString() {
		String s = "";
		
		s = "Manual Transmission\n\n";
		s += this.numberOfGears + " gears\n";
		s += this.currentGear + " current gear";
		
		return s;
	}

	ManualTransmission(int numGears) {
		if (numGears < 3) {
			numGears = 3;
		}
		
		if (numGears > 6) {
			numGears = 6;
		}
		
		numberOfGears = numGears;
	}
	
	ManualTransmission() {
		numberOfGears = DEFAULT_NUM_GEARS;
	}
}
