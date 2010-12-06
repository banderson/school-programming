import java.awt.event.ActionListener;
import javax.swing.JButton;

class QuitButton extends JButton implements Command {
	 
    Mediator med;
    
	QuitButton(ActionListener al, Mediator m) {
		super("Quit");
		addActionListener(al);
        med = m;
        med.registerQuit(this);
	}
	
	public void execute() {
		med.quit();
	}
}