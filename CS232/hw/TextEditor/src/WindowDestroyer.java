import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowDestroyer extends WindowAdapter {
	
	Mediator med;
	
	public WindowDestroyer(Mediator m) {
		super();
		med = m;
		med.registerWindowListener(this);
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		med.quit();
	}
}
