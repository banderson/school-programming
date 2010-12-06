import java.awt.event.ActionListener;
import javax.swing.JButton;

public class OpenButton extends JButton implements Command {
	 
    Mediator med;
    
	OpenButton(ActionListener al, Mediator m) {
		super("Open");
		addActionListener(al);
        med = m;
        med.registerOpen(this);
	}
	
	public void execute() {
		med.open();
	}
}