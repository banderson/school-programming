import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.*;

public class ManualTransmissionTest {
	
	ManualTransmission tranny;
	ManualTransmission fiveSpeed;
	ManualTransmission sixSpeed;

	@Before
	public void setUp() throws Exception {
		tranny = new ManualTransmission();
		fiveSpeed = new ManualTransmission(5);
		sixSpeed = new ManualTransmission(6);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void canCreateTransmission() {
		Assert.assertNotNull(tranny);
	}
	
	@Test
	public void canPrintTransmissionInfo() {
		Assert.assertTrue(tranny.toString() != "");
	}
	
	@Test
	public void canCreateTransmissionWithDifferentSpeeds() {
		
	}
}
