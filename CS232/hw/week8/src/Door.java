
public class Door {
	private boolean closed;
	
	boolean close() {
		closed = true;
		
		return true;
	}
	
	boolean open() {
		closed = false;
		
		return true;
	}
	
	boolean isClosed() {
		return closed;
	}
	
	/**
	 * This bullshit will show up in javadoc helpers
	 */
	public void printDoor() {
		System.out.println("Door is "+ ((this.isClosed()) ? "closed" : "open"));
	}
}
