
public class DoorKnob {

	public static void main(String[] args) {
		Door d = new LockableDoor();
		d.open();
		d.printDoor();
		d.close();
		d.printDoor();
	}
}
