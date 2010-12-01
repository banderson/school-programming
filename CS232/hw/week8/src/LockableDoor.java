
public class LockableDoor extends Door {
	private boolean locked;
	
	public LockableDoor() {
		super();
		locked = false;
	}
	
	public boolean lock() {
		locked = true;
		return true;
	}
	
	public boolean unlock() {
		locked = false;
		return true;
	}
	
	public boolean open() {
		if (!this.locked) {
			System.out.println("Doors now opened. Pumped.");
			return super.open();
		} else {
			System.out.println("Sorry, you can't open the door because it's locked");
			return false;
		}
	}
}
