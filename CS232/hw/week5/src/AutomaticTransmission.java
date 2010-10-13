
public class AutomaticTransmission {

	private ManualTransmission tran;
	
	public AutomaticTransmission() {
		tran = new ManualTransmission(3);
	}
	
	public AutomaticTransmission(int numGears) {
		tran = new ManualTransmission(numGears);
	}
	
	// TODO: the rest of the class should delegate to manual transmission
}
